/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conversores.IntegerConversor;
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

/**
 *
 * @author romulo
 */
@Entity
@SequenceGenerator(name = "seq_questoes", sequenceName = "seq_questoes", allocationSize = 1)
@Table(name = "questoes", catalog = "superdb", schema = "public")
public class Questao extends Tabela<Integer> implements Serializable {

    private Integer id_questao;
    private String titulo;
    private String descricao;
    private String resposta;


    @Column(name = "id_questao", unique = true, nullable = false)
    @Id
    public Integer getId_questao() {
        return id_questao;
    }

    @Override
    public Integer getPk() {
        return id_questao;
    }
    
    public void setId_questao(Integer id_questao) {
        this.id_questao = id_questao;
    }

    @Column(name = "titulo", nullable = false)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "descricao", nullable = true)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "resposta", nullable = true)
    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String getTabelaNome() {
        return "questoes";
    }

    @Override
    public String getTabelaPKNome() {
        return "id_questao";
    }

    @Override
    public List<Object> getCamposValor() {
        ArrayList<Object> listaCampos = new ArrayList<>();
        listaCampos.add(this.getPk());
        listaCampos.add(this.getTitulo());
        listaCampos.add(this.getDescricao());
        listaCampos.add(this.getResposta());
        return listaCampos;
    }

    @Override
    public List<IConversor> getCamposConversor() {
        ArrayList<IConversor> listaConversores = new ArrayList<>();
        listaConversores.add(new IntegerConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        listaConversores.add(new StringConversor());
        return listaConversores;
    }

    @Override
    public List<String> getCamposNome() {
        ArrayList<String> listaNomes = new ArrayList<>();
        listaNomes.add("id_questao");
        listaNomes.add("titulo");
        listaNomes.add("descricao");
        listaNomes.add("resposta");
        return listaNomes;
    }

    @Override
    protected List<String> getCamposObrigatorios() {
        ArrayList<String> listaObrigatorios = new ArrayList<>();
        listaObrigatorios.add("id_questao");
        listaObrigatorios.add("titulo");
        return listaObrigatorios;
    }

    @Override
    protected Retorno setCamposValor(List<Object> list) {
        Retorno ret = new Retorno(true, null);
        try {
            this.setId_questao((Integer) list.get(0));
            this.setTitulo((String) list.get(1));
            this.setDescricao((String) list.get(2));
            this.setResposta((String) list.get(3));
        } catch (Exception e) {
            ret.setSucesso(false);
            ret.addMensagem("Erro ao configura campos, ERROR:" + e.getMessage());
        }
        return ret;
    }

    @Override
    public Tabela<?> getNovoObjeto() {
        return new Questao();
    }

}
