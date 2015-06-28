import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Login Bean Class which holds login information.
 * @author huangdun
 */

@ManagedBean(eager = true)
@SessionScoped
public class LoginBean implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * currentUser.
     */
    private User currentUser;

    /**
     * Set currentUser to be the given user to hold its
     * information.
     * @param user user to be logged in
     */
    public final void login(final User user) {
        currentUser = user;
    }

    /**
     * get currentUser.
     * @return currentUser object
     */
    public final User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check if there is user already logged in. Help
     * to navigate to user main pages.
     * based on role of the current user.
     * @return page to be navigated to
     */
    public final String checkLogin() {
        if (currentUser != null) {
            if (!isAdmin()) {
                return "openingmovies.xhtml?faces-redirect=true";
            } else {
                return "usermanagement.xhtml?faces-redirect=true";
            }
        }

        return null;
    }

    /**
     * Check if user already logged out. Help
     * navigate to welcome screen if the user is logged
     * out
     * @return page to be navigated to
     */
    public final String checkLogout() {
        if (currentUser == null) {
            return "index.xhtml?faces-redirect=true";
        }

        return null;
    }

    /**
    * get currentUser's lastName.
    * @return lastName of currentUser
    */
    public final String getLastName() {
        if (currentUser != null) {
            return currentUser.getLastName();
        }

        return null;
    }

    /**
    * get currentUser's firstName.
    * @return firstName of currentUser
    */
    public final  String getFirstName() {
        if (currentUser != null) {
            return currentUser.getFirstName();
        }

        return null;
    }

    /**
    * get currentUser's email.
    * @return email of currentUser
    */
    public final String getEmail() {
        if (currentUser != null) {
            return currentUser.getEmail();
        }

        return null;
    }

    /**
    * get currentUser's phone number.
    * @return phone number of currentUser
    */
    public final String getPhone() {
        if (currentUser != null) {
            return currentUser.getPhone();
        }

        return null;
    }

    /**
    * get currentUser's major.
    * @return major of currentUser
    */
    public final int getMajor() {
        if (currentUser != null) {
            return currentUser.getMajor();
        }

        return -1;
    }

    /**
    * get currentUser's major.
    * @return gender of currentUser
    */
    public final int getGender() {
        if (currentUser != null) {
            return currentUser.getGender();
        }

        return -1;
    }

    /**
    * get currentUser's interest.
    * @return interest of currentUser
    */
    public final String getInterest() {
        if (currentUser != null) {
            return currentUser.getInterest();
        }

        return null;
    }

    /**
    * Destroy the current session and
    * navigate back to welcome screen.
    * @return welcome screen link
    */
    public final String logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return "index?faces-redirect=true";
    }

    /**
     * Check if the current user is admin.
     * @return boolean value which represents if
     * the currentUser is an admin;
     */
    public final boolean isAdmin() {
        return !(currentUser == null || currentUser.getRole() == 1);
    }
}
