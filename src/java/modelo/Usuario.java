package modelo;

import conversores.StringConversor;
import interfaces.IConversor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import util.Retorno;

@Entity
@SequenceGenerator(name = "seq_tb_usuario", sequenceName = "seq_tb_usuario", allocationSize = 1)
@Table(name = "tb_usuario", catalog = "superdb", schema = "public")
public class Usuario extends Tabela<String> implements Serializable {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String acesso;
    private String checado;

    public static Usuario novaInstancia() {
        return new Usuario();
    }

    public Usuario() {
    }

    @Id
    @Column(name = "cpf", unique = true, nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "nome", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "senha", nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "acesso")
    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }
    
    @Column(name = "checado")
    public String getChecado() {
        return checado;
    }

    public void setChecado(String checado) {
        this.checado = checado;
    }
    
   
    @Override
    public String getTabelaNome() {
        return "tb_usuario";
    }

    @Override
    public String getTabelaPKNome() {
        return "cpf";
    }

    @Override
    public List<Object> getCamposValor() {
        ArrayList<Object> listaCampoValores = new ArrayList<>();
        listaCampoValores.add(getCpf());
        listaCampoValores.add(getNome());
        listaCampoValores.add(getEmail());
        listaCampoValores.add(getSenha());
        listaCampoValores.add(getAcesso());
        listaCampoValores.add(getChecado());
        return listaCampoValores;
    }

    @Override
    public List<IConversor> getCamposConversor() {
        ArrayList<IConversor> listaConversores = new ArrayList<>();
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        return listaConversores;
    }

    @Override
    public List<String> getCamposNome() {
        ArrayList<String> listaNomes = new ArrayList<>();
        listaNomes.add("cpf");
        listaNomes.add("nome");
        listaNomes.add("email");
        listaNomes.add("senha");
        listaNomes.add("acesso");
        listaNomes.add("checado");
        return listaNomes;
    }

    @Override
    protected List<String> getCamposObrigatorios() {
        ArrayList<String> listaCamposObrigatorios = new ArrayList<>();
        listaCamposObrigatorios.add("nome");
        listaCamposObrigatorios.add("email");
        listaCamposObrigatorios.add("senha");
        return listaCamposObrigatorios;
    }

    @Override
    protected Retorno setCamposValor(List<Object> list) {
        Retorno ret = new Retorno(true, null);
        try{
            this.setPk((String) list.get(0));
            this.setNome((String) list.get(1));
            this.setEmail((String) list.get(2));
            this.setSenha((String) list.get(3));
            this.setAcesso((String) list.get(4));
            this.setChecado((String) list.get(5));
	}catch(Exception e){
            ret.setSucesso(false);
            ret.addMensagem("Erro ao configura campos, ERROR:"+e.getMessage());
        }
	return ret;
    }

    @Override
    public Tabela<?> getNovoObjeto() {
        return new Usuario();
    }

}
