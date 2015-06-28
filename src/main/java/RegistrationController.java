import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * RegistrationController helps adding customer info.
 * into the system
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped
public class RegistrationController implements Serializable {
/**
 * serialVersionUID.
 */
    private static final long serialVersionUID = 6106269076155338045L;

/**
 * user's name.
 */
    private String username;
/**
 * user's password.
 */
    private String password;
    /**
 * user's firstName.
 */
    private String firstName;
    /**
 * user's lastName.
 */
    private String lastName;
    /**
 * The list of user's majors.
 */
    private List<SelectItem> majors;
    /**
 * user's confirmedPassword.
 */
    private String confirmedPassword;
/**
 * user's selected major number.
 */
    private int selectedMajor;
    /**
     * Initialize the list of majors.
     * and get the original profile information
     * of the user
     */
  @PostConstruct
    public final void init() {
        majors = new ArrayList<>();
        List<Major> allMajors = UserManager.getAllMajors();
        for (Major major:allMajors) {
            if (major.getMajorId() > 0) {
               majors.add(new SelectItem(major.getMajorId(), major.getLabel()));
            }
        }
    }
     /**
    * get the string of the username of.
    * the user
    * @return firstName of the user
    */
    public final String getUsername() {
        return username;
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
    * get the string of the firstname of.
    * the user
    * @return firstName of the user
    */
    public final String getFirstName() {
        return firstName;
    }
     /**
    * get the string of the lastname of.
    * the user
    * @return lastName of the user
    */
    public final String getLastName() {
        return lastName;
    }
    /**
    * get the string of the selectedMajor of.
    * the user
    * @return selectedMajor of the user
    */
    public final int getSelectedMajor() {
        return selectedMajor;
    }
     /**
    * get the list of the selectedMajor of.
    * the user
    * @return the list of the selectedMajor of the user
    */
    public final List<SelectItem> getMajors() {
        return majors;
    }
    /**
    * set the username of the current user.
    * to the given one
    * @param newUsername the given username in string
    */
    public final void setUsername(final String newUsername) {
        this.username = newUsername;
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
    * set the firstName of the current user.
    * to the given one
    * @param newfirstName the given firstName in string
    */
    public final void setFirstName(final String newfirstName) {
        this.firstName = newfirstName;
    }
   /**
    * set the lastName of the current user.
    * to the given one
    * @param newlastName the given lastName in string
    */
    public final void setLastName(final String newlastName) {
        this.lastName = newlastName;
    }
   /**
    * set selectedMajor of the current user.
    * to the given one
    * @param newselectedMajor the given selectedMajor in int
    */
    public final void setSelectedMajor(final int newselectedMajor) {
        this.selectedMajor = newselectedMajor;
    }
      /**
    * set List<SelectItem> majors of the current user.
    * to the given one
    * @param newmajors the given selectedMajor in List
    */
    public final void  setMajors(final List<SelectItem> newmajors) {
        this.majors = newmajors;
    }
     /**
    * submit the new created user info into the system.
    */
     public final void submit() {
        User user = UserManager.find(username);
        FacesContext context = FacesContext.getCurrentInstance();
        final int passwordLimit = 6;

        if (user.getPassword() != null) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Username already exist", ""));
            username = "";
            password = "";
            confirmedPassword = "";
        } else if (!password.contentEquals(confirmedPassword)) {
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
        } else if (selectedMajor < 0) {
            password = "";
            confirmedPassword = "";
            context.addMessage(null, new
        FacesMessage(FacesMessage.SEVERITY_ERROR,
                "You must select a major", ""));
        } else {
            UserManager.registerUser(new
        User(username, password, firstName, lastName, selectedMajor, 1, 1));
            RequestContext.getCurrentInstance().execute("PF('dlg').show()");
        }

     }
    /**
    * save the new created user info into the system.
    * @return the address of the redirect page
    */
    public final String cancel() {
        return "index?faces-redirect=true";
    }

}
