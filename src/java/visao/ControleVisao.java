package visao;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "controleVisao")
@ViewScoped
public class ControleVisao implements Serializable {

    private static final String R001 = "retornarAoLogin";
    private static final String R002 = "retornarAoIndex";

    private static final String M001 = "Usuário logado com sucesso.";
    private static final String M002 = "Informe o endereço de email se deseja recuperar sua senha.";
    private static final String M003 = "Usuário e/ou senha inválidos.";
    private static final String M004 = "Nova senha enviada. Verifique seu e-mail.";
    private static final String M005 = "E-mail não cadastrado. Verifique se esse está correto.";
    

    public ControleVisao() {
    }

    @PostConstruct
    public void postConstruct() {
    }

    public String getCPF() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        return ec.getRequestParameterMap().get("j_username");
    }

    public String retornarAoLogin() {
        return R001;
    }

    public String retornarAoIndex() {
        return R002;
    }

    public void loginRealizado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, M001, ""));
    }

    public void informeEmail() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, M002, ""));
    }

    public void erroDeLogin() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, M003, ""));
    }

    public void novaSenhaEnviada() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, M004, ""));
    }

    public void erroEmailNaoCadastrado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, M005, ""));
    }

    public void erroEmailInvalido() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, M003, ""));
    }

    public void erroCPFInvalido() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, M003, ""));
    }
}
