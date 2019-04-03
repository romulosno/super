package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author edercs
 */
public class ValidadorCPF implements Validator {
    

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
//        InscricaoService service = service();
        String replace = valorTela.toString().replace(".", "");
        String strReplace = replace.replace("-", "");

        System.out.println("************** Iniciando validação do cpf...");
        if (!validaCPF(String.valueOf(strReplace))) {
            System.out.println("************** Capturando valor do campo cpf...");
            FacesMessage message = new FacesMessage(null, "CPF inválido");
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            System.out.println("************** valor capturado do campo cpf: " + strReplace);
            throw new ValidatorException(message);
        }
//        if (service.consultarInscricaoPorCPF(String.valueOf(valorTela)).getId() != null) {
//            FacesMessage message = new FacesMessage(null, "Já consta inscrição");
//            message.setSeverity(FacesMessage.SEVERITY_WARN);
//            throw new ValidatorException(message);
//        }
    }

    /**
     * Valida CPF do usuário. Não aceita CPF's padrões como 11111111111 ou
     * 22222222222
     *
     * @param cpf String valor com 11 dígitos
     */
    public static boolean validaCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
    private static boolean isCPFPadrao(String cpf) {
        return cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999");
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = 0;
        } else {
            primDig = 11 - (soma % 11);
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = 0;
        } else {
            segDig = 11 - (soma % 11);
        }

        return primDig.toString() + segDig.toString();
    }

//    private InscricaoService service() {
//        try {
//            Context c = new InitialContext();
//            return (InscricaoService) c.lookup("java:global/InscricaoPaccExt/InscricaoService!br.com.unuead.extensao.dominio.service.InscricaoService");
//        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }
//    }
}
