import java.io.Serializable;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * LoginController class which communicates with login page
 * and model.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped

public class ForgotpwController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;

    /**
     * forgot password email.
     */
    private String email;


    /**
     * get string of email.
     * @return password in string
     */
    public final String getEmail() {
        return email;
    }

    /**
     * set email.
     * @param newEmail the password been giving to the user
     */
    public final void setEmail(final String newEmail) {
        this.email = newEmail;
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
        FacesContext context = FacesContext.getCurrentInstance();
        if (email == null || email.isEmpty()) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Please enter an email address.", ""));
            return;
        }
        User user = UserManager.findUserByEmail(email);

        if (user.getUsername() == null) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email Address doesn't exist.", ""));
        } else {
            sendEmail(user);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "An email has been sent to your email address."
                            , ""));
            email = "";
        }

    }

    /**
     * sendEmail method which send the link to password change page.
     * @param user user who wants to retrieve password
     */
    public final void sendEmail(final User user) {
        final String username = "502badgateway2340@gmail.com";
        final String password = "getextras";

        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        
        final String link = "http://localhost:8080/BuzzMoviesSelector/faces"+
                "/changepassword.xhtml?username=" + user.getUsername();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("502badgateway2340@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Change your password");
            message.setText("Dear " + firstName + " " + lastName + ","
                    + "\n\n Click this link to change your password:"
                    + "\n " + link);

            Transport.send(message);

            System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
    }
}

