package modelo;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-02T19:31:26")
@StaticMetamodel(Questao.class)
public class Questao_ { 

    public static volatile SingularAttribute<Questao, String> resposta;
    public static volatile SingularAttribute<Questao, String> titulo;
    public static volatile SingularAttribute<Questao, List> camposValor;
    public static volatile SingularAttribute<Questao, Integer> id_questao;
    public static volatile SingularAttribute<Questao, String> descricao;

}