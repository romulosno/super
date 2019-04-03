package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import modelo.Questao;
import modelo.Usuario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import persistencia.DAOGeneric;
import util.EnviadorDeEmail;
import util.GeradorDeSenhas;
import util.Retorno;
import util.ValidadorCPF;
import util.ValidadorDeEmail;
import visao.ControleVisao;

@Configuration
@ManagedBean(name = "serviceControle")
@RequestScoped
public class ServiceControle implements PhaseListener {

    protected final Log logger = LogFactory.getLog(getClass());
    private Usuario usuario;
    private String emailParaSenha;
    private DAOGeneric dao = DAOGeneric.getIntancia();
    private final ControleVisao ctrlVisao = new ControleVisao();
    public Integer pkQuestao;
    public String resposta;

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmailParaSenha() {
        return this.emailParaSenha;
    }

    public void setEmailParaSenha(String emailParaSenha) {
        this.emailParaSenha = emailParaSenha;
    }

    /**
     * Devolve o nome do usuário logado
     */
    public String getUsuarioPorPk() {
        usuario = new Usuario();
        usuario = (Usuario) dao.getObjetoPelaPk(this.getCpfUsuario(), usuario);
        return usuario.getNome();
    }

    public void buscarUsuarioPorEmail() {
        dao = DAOGeneric.getIntancia();
        usuario = new Usuario();
        if (emailParaSenha.equals("") || emailParaSenha == null) {
            ctrlVisao.informeEmail();
            return;
        }
        if (!ValidadorDeEmail.validacaoEmail(emailParaSenha)) {
            ctrlVisao.erroEmailInvalido();
            return;
        }
        usuario.setEmail(emailParaSenha);
        ArrayList<Usuario> lista = new ArrayList<>();

        if (dao.procurar(usuario).isEmpty()) {
            ctrlVisao.erroEmailNaoCadastrado();
            return;
        }
        usuario = dao.procurar(usuario).get(0);
        String senha = GeradorDeSenhas.gerarSenha();
        usuario.setSenha(codificarMd5(senha));
        usuario.setPk(usuario.getPk());
        Retorno alterar = dao.alterar(usuario);
        enviarEmail(usuario, senha);
        ctrlVisao.novaSenhaEnviada();
    }

    private void enviarEmail(Usuario usuario, String senha) {
        String assunto = "Nova Senha - Sistema S.U.P.E.R";
        String email = usuario.getEmail();
        String mensagem = "Olá " + usuario.getNome() + "\nSegue nova senha para acesso pelo email: " + email + "\n Senha nova: " + senha;
        EnviadorDeEmail.enviarEmail(assunto, mensagem, email, senha);
    }

    public String codificarMd5(String str) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(str, null);
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
                WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException) {
            logger.debug("Found exception in session map: " + e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", "Username or password not valid"));
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    /**
     * Retorna o usuário logado
     *
     * @return CPF do usuário
     */
    public String getCpfUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    /**
     * Redireciona diretamente ao spring checurity check do .xml
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String doLogin() throws ServletException, IOException {
        if (!ValidadorCPF.validaCPF(ctrlVisao.getCPF())) {
            ctrlVisao.erroCPFInvalido();
            return "";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        return null;

    }

    public List<Questao> buscarQuestoesNaoRespondidas() {
        ArrayList<Questao> lista = new ArrayList<>();
        List<Questao> buscaPadrao = dao.procurar(new Questao());
        for(Questao q :  buscaPadrao ){
            if(q.getResposta()==null || q.getResposta().equals(""))
                lista.add(q);
        }
        return lista;
    }

    public List<String> listaAssunto() {
        ArrayList<String> listaAssunto = new ArrayList<>();
        listaAssunto.add("Romulo");
        listaAssunto.add("Cinthia");
        return listaAssunto;
    }

    public void registrarResposta() {
        Questao q = new Questao();
        q.setId_questao(this.pkQuestao);
        q = dao.procurar(q).get(0);
        q.setResposta(resposta);
        Retorno ret = dao.alterar(q);
        System.out.print("Retorno resposta: "+ret.getMensagem());
    }

    public void setPkQuestao(String pk){
        this.pkQuestao = Integer.valueOf(pk);
    }
    
    public String getResposta(){
        return this.resposta;
    }
    public void setResposta(String resposta){
        this.resposta = resposta;
    }
}