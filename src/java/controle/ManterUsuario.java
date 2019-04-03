package controle;

import interfaces.IReferenciaAcaoNegocio;
import java.util.HashMap;
import java.util.List;
import modelo.Usuario;
import util.NegocioEntryPoint;

public class ManterUsuario extends CrudControle<Usuario, String>{
    
    @Override
    public String getCasoDeUso() {
        return "manterUsuario";
    }
    
    @Override
    public Usuario getNovoObjeto() {
        return new Usuario();
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
