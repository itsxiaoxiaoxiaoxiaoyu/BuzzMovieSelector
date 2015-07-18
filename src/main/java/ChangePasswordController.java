
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author huangdun
 */
/**
 * RegistrationController helps adding customer info.
 * into the system
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped
public class ChangePasswordController implements Serializable {
    /**
    * serialVersionUID.
    */
    private static final long serialVersionUID = 6106269076155338045L;

    /**
    * user who needs to change the password.
    */
    private User user;

    /**
    * user's password.
    */
    private String password;
    /**
    * user's confirmedPassword.
    */
    private String confirmedPassword;

    /**
     * init() method to read request map.
     */
    @PostConstruct
    public final void init() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> parameterMap = (Map<String, String>) context
                .getExternalContext().getRequestParameterMap();
        user = UserManager.find(parameterMap.get("username"));
    }

    /**
    * set the password of the current user.
    * to the given one
    * @param newPassword the given password in string
    */
    public final void setPassword(final String newPassword) {
        this.password = newPassword;
    }
     /**
    * set the confirmedPassword of the current user.
    * to the given one
    * @param newconfirmedPassword the given confirmedPassword in string
    */
    public final void setConfirmedPassword(final String newconfirmedPassword) {
        this.confirmedPassword = newconfirmedPassword;
    }

    /**
    * get the string of the password of.
    * the user
    * @return username of the user
    */
    public final String getPassword() {
        return password;
    }
 /**
    * get the string of the confirmedPassword of.
    * the user
    * @return confirmedPassword of the user
    */
    public final String getConfirmedPassword() {
        return confirmedPassword;
    }

    /**
     * Method to submit changed password.
     */
    public final void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        final int passwordLimit = 6;
        if (!password.contentEquals(confirmedPassword)) {
            context.addMessage(null, new
         FacesMessage(FacesMessage.SEVERITY_ERROR,
                 "Password and "
                         + "cofirmedPassword do not match", ""));
            password = "";
            confirmedPassword = "";
        } else if (password.length() < passwordLimit) {
            context.addMessage(null, new
         FacesMessage(FacesMessage.SEVERITY_ERROR,
                 "The minimum length for a password is 6", ""));
            password = "";
            confirmedPassword = "";
        } else {
            user.setPassword(password);
            UserManager.updateUser(user);
            RequestContext.getCurrentInstance().execute("PF('dlg').show()");
        }
    }
}
