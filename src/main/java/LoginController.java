import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * LoginController class which communicates with login page
 * and model.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped

public class LoginController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * User name of the login attempt.
     */
    private String username;
    /**
     * Password of the login session.
     */
    private String password;
    /**
     * fail count.
     */
    private final Map<String, Integer> failCount = new HashMap<>();
    
    /**
     * forgot password email
     */
    private String email;

    /**
     * the login bean.
     */
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    /**
     * get string of current username.
     * @return username in string
     */
    public final String getUsername() {
        return username;
    }

    /**
     * get string of password.
     * @return password in string
     */
    public final String getPassword() {
        return password;
    }

    /**
     * get string of email.
     * @return password in string
     */
    public final String getEmail() {
        return email;
    }

    /**
     * get the login bean which holds
     * login information of the current
     * user.
     * @return loginBean of current user
     */
    public final LoginBean getLoginBean() {
        return loginBean;
    }

    /**
     * set current user name to be the given
     * string name.
     * @param newUsername the name been giving to the user
     */
    public final void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    /**
     * set the current password to be the given
     * password.
     * @param newPassword the password been giving to the user
     */
    public final void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * set email
     * @param newEmail the password been giving to the user
     */
    public final void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * set the current login bean to be the given
     * login bean.
     * @param newLoginBean the loginBean been giving to the user
     */
    public final void setLoginBean(final LoginBean newLoginBean) {
        this.loginBean = newLoginBean;
    }

    /**
     * try to login into the system with given username,
     * password and loginBean.
     * @return string stating the login result
     */
    public final String login() {
        User user = UserManager.find(username);
        FacesContext context = FacesContext.getCurrentInstance();
        final int three = 3;
        if (user.getPassword() == null) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Username or Password incorrect", ""));
            username = "";
            return null;
        } else if (!user.checkLogin(password)) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Username or Password incorrect", ""));
            if (!failCount.containsKey(username)) {
                failCount.put(username, 1);
            } else {
                failCount.put(username, failCount.get(username) + 1);
            }

            if (failCount.get(username) >= three) {
                UserManager.lockAccount(user.getUser());
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "This account is locked.", ""));
            }
            username = "";
            return null;
        }
        //2:banned 3:locked
        if (user.getStatus() == 2) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Your account is banned.", ""));
            username = "";
            return null;
        } else if (user.getStatus() == three) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Your account is locked."
                        + " Please contact an administrator to unlock.", ""));
            username = "";
            return null;
        }
        if (loginBean != null) {
            loginBean.login(user);
        }
        if (user.getRole() == 1) {
            return "openingmovies?faces-redirect=true";
        } else {
            return "usermanagement?faces-redirect=true";
        }
    }

    /**
     * the action when the user cancels login.
     * @return string stating the cancel information
     */
    public final String cancel() {
        return "index?faces-redirect=true";
    }

    /**
     * the action when the user clicks Forgot Password.
     */
    public final void forgetPassword() {
        UserManager.find("aaa");
    }
}
