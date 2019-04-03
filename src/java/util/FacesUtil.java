package util;

import javax.faces.context.FacesContext;

/**
 *
 * @author unueadlab11
 */
public class FacesUtil {
    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get(name);
    }
}
