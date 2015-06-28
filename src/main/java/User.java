
import java.io.Serializable;

/**
 * User Class which represents a user entity model.
 * @author huangdun
 */

public class User implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * user.
     */
    private int user;
    /**
     * username.
     */
    private String username;
    /**
     * password.
     */
    private String password;
    /**
     * firstName.
     */
    private String firstName;
    /**
     * lastname.
     */
    private String lastName;
    /**
     * email.
     */
    private String email;
    /**
     * phone.
     */
    private String phone;
    /**
     * interest.
     */
    private String interest;
    /**
     * major.
     */
    private int major;

    /**
     * gender.
     */
    private int gender;

    /**
     * role.
     */
    private int role;
    /**
     * status.
     */
    private int status;

    /**
     *Basic Constructor.
     */
    public User() {
    }

    /**
     * a Constructor which takes username as parameter.
     * @param newUsername a constructor which only takes user name as parameter
     */
    public User(final String newUsername) {
        username = newUsername;
    }
    /**
     * A Constructor which takes a lot of parameters.
     * @param newUsername input username
     * @param newPassword input password
     * @param newFirstName input firstname
     * @param newLastName input lastname
     * @param newMajor input majorId
     * @param newRole input roleId
     * @param newStatus input statusId
     */
    public User(final String newUsername, final String newPassword,
            final String newFirstName, final String newLastName,
            final int newMajor, final int newRole, final int newStatus) {
        username = newUsername;
        password = newPassword;
        firstName = newFirstName;
        lastName = newLastName;
        role = newRole;
        status = newStatus;
        major = newMajor;
    }

    /**
     * User setter.
     * @param newUser input user
     */
    public final void setUser(final int newUser) {
        user = newUser;
    }

    /**
     * username setter.
     * @param newUsername input username
     */
    public final void setUsername(final String newUsername) {
        username = newUsername;
    }

    /**
     * lastName setter.
     * @param newLastName input lastName
     */
    public final void setLastName(final String newLastName) {
        lastName = newLastName;
    }

    /**
     * firstName setter.
     * @param newFirstName input firstName
     */
    public final void setFirstName(final String newFirstName) {
        firstName = newFirstName;
    }

    /**
     * password setter.
     * @param newPassword input password
     */
    public final void setPassword(final String newPassword) {
        password = newPassword;
    }

    /**
     * email setter.
     * @param newEmail input email
     */
    public final void setEmail(final String newEmail) {
        email = newEmail;
    }

    /**
     * phone setter.
     * @param newPhone input phone
     */
    public final void setPhone(final String newPhone) {
        phone = newPhone;
    }

    /**
     * major setter.
     * @param newMajor input major
     */
    public final void setMajor(final int newMajor) {
        major = newMajor;
    }

    /**
     * role setter.
     * @param newRole input role
     */
    public final void setRole(final int newRole) {
        role = newRole;
    }

    /**
     * gender setter.
     * @param newGender input gender
     */
    public final void setGender(final int newGender) {
        gender = newGender;
    }

    /**
     * status setter.
     * @param newStatus input status
     */
    public final void setStatus(final int newStatus) {
        status = newStatus;
    }

    /**
     * interest setter.
     * @param newInterest input interest
     */
     public final void setInterest(final String newInterest) {
        interest = newInterest;
     }
   /**
    * User getter.
    * @return user id
    */
    public final int getUser() {
        return user;
    }
    /**
     * username getter.
     * @return username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * lastName getter.
     * @return lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * firstName getter.
     * @return firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * password getter.
     * @return password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * email getter.
     * @return email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * phone getter.
     * @return phone
     */
     public final String getPhone() {
        return phone;
    }

     /**
      * major getter.
      * @return majorId
      */
    public final int getMajor() {
        return major;
    }

    /**
      * gender getter.
      * @return genderId
      */
    public final int getGender() {
        return gender;
    }

    /**
     * role getter.
     * @return role id
     */
    public final int getRole() {
        return role;
    }

    /**
     * status getter.
     * @return status id
     */
    public final int getStatus() {
        return status;
    }

    /**
     * Interest getter.
     * @return interest
     */
     public final String getInterest() {
        return interest;
    }

    /**
     * checkLogin method which check if.
     * the given password matches the username
     * @param p given password
     * @return the boolean value of whether the
     * login is valid
     */
    final boolean checkLogin(final String p) {
        if (p == null) {
            return false;
        }

        return Encryptor.encrypt(p).equals(password);
    }

}
