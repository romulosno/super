package controle;

import interfaces.IReferenciaAcaoNegocio;
import java.util.HashMap;
import java.util.List;
import modelo.Questao;
import util.NegocioEntryPoint;

public class ManterQuestao extends CrudControle<Questao, Integer>{
    
    @Override
    public String getCasoDeUso() {
        return "manterQuestao";
    }
    
    @Override
    public Questao getNovoObjeto() {
        return new Questao();
    }

    @Override
    public HashMap<String, IReferenciaAcaoNegocio> getAcaoMetodos() {
        return null;
    }
                

    @Override
    public List<NegocioEntryPoint> getEntryPoints() {
        return null;
    }
}
