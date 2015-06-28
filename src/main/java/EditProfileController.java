import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * EditProfileController helps edit profile
 * information of user.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped

public class EditProfileController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * firstName.
     */
    private String firstName;
    /**
     * lastName.
     */
    private String lastName;
    /**
     * phoneNumber.
     */
    private String phoneNumber;
    /**
     * email.
     */
    private String email;
    /**
     * interest.
     */
    private String interest;
    /**
     * majors.
     */
    private List<SelectItem> majors;
    /**
     * selectedMajor.
     */
    private int selectedMajor;
    /**
     * genders.
     */
    private List<SelectItem> genders;
    /**
     * selectedGender.
     */
    private int selectedGender;
    /**
     * LoginBean.
     */
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;


    /**
     * Initialize the list of majors
     * and get the original profile information
     * of the user.
     */
    @PostConstruct
    public final void init() {
        majors = new ArrayList<>();
        List<Major> allMajors = UserManager.getAllMajors();
        for (Major major:allMajors) {
            if (major.getMajorId() > 0) {
                majors.add(new SelectItem(major.getMajorId(),
                        major.getLabel()));
            }
        }

        genders = new ArrayList<>();
        List<Gender> allGenders = UserManager.getAllGenders();
        for (Gender gender:allGenders) {
            if (gender.getGenderId() > 0) {
                genders.add(new SelectItem(gender.getGenderId(),
                        gender.getLabel()));
            }
        }
        setFirstName(loginBean.getFirstName());
        setLastName(loginBean.getLastName());
        setEmail(loginBean.getEmail());
        setPhoneNumber(loginBean.getPhone());
        setSelectedMajor(loginBean.getMajor());
        setSelectedGender(loginBean.getGender());
        setInterest(loginBean.getInterest());
    }

    /**
    * get the string of the firstname of
    * the user.
    * @return firstName of the user
    */
    public final String getFirstName() {
        return firstName;
    }
    /**
    * get the string of the lastname of
    * the user.
    * @return lastName of the user
    */
    public final String getLastName() {
        return lastName;
    }

    /**
    * get the string of the phone label of
    * the user.
    * @return phoneNumber the label or phone number
    */
     public final String getPhoneLabel() {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return "+Add your phone number";
        } else {
            return phoneNumber;
        }
    }

    /**
    * get the string of the email label of.
    * the user
    * @return email the label or email
    */
    public final String getEmailLabel() {
        if (email == null || email.isEmpty()) {
            return "+Add your email";
        } else {
            return email;
        }
    }

    /**
    * get the string of the email of.
    * the user
    * @return email of the user
    */
    public final String getEmail() {
            return email;
    }
    /**
    * get the string of the phone number of.
    * the user
    * @return phoneNumber of the user
    */
    public final String getPhoneNumber() {
            return phoneNumber;
    }

    /**
    * get the string of the interest.
    * the user
    * @return interest of the user
    */
    public final String getInterest() {
            return interest;
    }

    /**
    * get the list of the majors of.
    * the user
    * @return majors the list of the major
    */
    public final List<SelectItem> getMajors() {
        return majors;
    }
    /**
    * get the selected major of.
    * the user
    * @return selectedMajor in integer
    */
    public final int getSelectedMajor() {
        return selectedMajor;
    }
    /**
    * get the selected major in label of.
    * the user
    * @return the label of major in string
    */
    public final String getMajorLabel() {
        for (SelectItem si:majors) {
            if (si.getValue().equals(selectedMajor)) {
                return si.getLabel();
            }
        }
        return "";
    }
    /**
    * get the list of the genders of.
    * the user
    * @return genders the list of the major
    */
    public final List<SelectItem> getGenders() {
        return genders;
    }
    /**
    * get the selected gender of
    * the user.
    * @return selectedMajor in integer
    */
    public final int getSelectedGender() {
        return selectedGender;
    }
    /**
    * get the selected gender in label of.
    * the user
    * @return the label of major in string
    */
    public final String getGenderLabel() {
        for (SelectItem si:genders) {
            if (si.getValue().equals(selectedGender)) {
                return si.getLabel();
            }
        }
        return "+Add your gender";
    }
    /**
    * get the interest label of
    * the user.
    * @return interest label in string
    */
     public final String getInterestLabel() {
        final int param = 20;
        if (interest == null || interest.isEmpty()) {
            return "+Talk about your interests";
        } else {
            if (interest.length() < param) {
                return interest;
            } else {
                return interest.substring(0, param) + "...";
            }
        }
     }
    /**
    * set the login bean of the current user.
    * to the given one
    * @param newLoginBean the given login bean
    */
    public final void setLoginBean(final LoginBean newLoginBean) {
        loginBean = newLoginBean;
    }

    /**
    * set the first name of the current user.
    * to the given one
    * @param newFirstName the given first name in string
    */
    public final void setFirstName(final String newFirstName) {
        firstName = newFirstName;
    }

    /**
    * set the last name of the current user.
    * to the given one
    * @param newLastName the given last name in string
    */
    public final void setLastName(final String newLastName) {
        lastName = newLastName;
    }

    /**
    * set the email of the current user.
    * to the given one
    * @param newEmail the given email in string
    */
    public final void setEmail(final String newEmail) {
        email = newEmail;
    }
    /**
    * set the email of current user.
    * to the given one
    * @param newPhoneNumber the given phoneNumber in string
    */
    public final void setPhoneNumber(final String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }
    /**
    * set the interest of the current user.
    * to the given one
    * @param newInterest the given interest in string
    */
    public final void setInterest(final String newInterest) {
        interest = newInterest;
    }
    /**
    * set the major of the current user.
    * to the given one
    * @param newMajors the given major list
    */
    public final void  setMajors(final List<SelectItem> newMajors) {
        majors = newMajors;
    }

    /**
    * set the selected major of the current user.
    * to the given one
    * @param newSelectedMajor the given selected major in integer
    */
    public final void setSelectedMajor(final int newSelectedMajor) {
        selectedMajor = newSelectedMajor;
    }

    /**
    * set the selected gender of the current user.
    * to the given one
    * @param newSelectedGender the given selected gender in integer
    */
    public final void setSelectedGender(final int newSelectedGender) {
        selectedGender = newSelectedGender;
    }

    /**
    * save the current change being edited.
    */
    public final void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        final int param = 10;
        if (!phoneNumber.isEmpty() && phoneNumber.length() != param) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "You must enter a complete number", ""));
        } else if (selectedMajor < 0) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "You must select a major", ""));
        } else {
            RequestContext.getCurrentInstance().execute("PF('dlg').show()");
        }
    }
    /**
    * commit the change of user profile into the system.
    */
    public final void commit() {
        User updatedUser = loginBean.getCurrentUser();
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setPhone(phoneNumber);
        updatedUser.setEmail(email);
        updatedUser.setInterest(interest);
        updatedUser.setMajor(selectedMajor);
        updatedUser.setGender(selectedGender);
        UserManager.updateUser(updatedUser);
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("editprofile.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(EditProfileController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
