import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test for login in login controller.
 * @author Xiaoyu Yuan
 */
public class LoginControllerTest {
    /**
     * Tests the login()) method for null comment.
     */
    @Test
    public final void testPasswordNull() {
        LoginController controller = new LoginController();
        assertEquals(controller.login(),
                                "Username or Password incorrect");
    }
    /**
     * Tests the login() method for wrong password.
     */
    @Test
    public final void testLoginwithWrongPassword() {
        LoginController controller = new LoginController();
        controller.setUsername("newuser");
        controller.setPassword("12345");
        assertEquals(controller.login(),
                                "Username or Password incorrect");
    }
    /**
     * Tests the login() method for failcount >= 3
     */
    @Test
    public final void checkLocked() {
        LoginController controller = new LoginController();
        controller.setUsername("newuser");
        controller.setPassword("12345");
        controller.login();
        controller.login();
        assertEquals(controller.login(),
                                "This account is locked.");
    }
    /**
     * Tests the login() method for locked
     */
    @Test
    public final void testLoginLocked() {
        LoginController controller = new LoginController();
        controller.setUsername("newuser");
        controller.setPassword("12345");
        controller.login();
        controller.login();
        controller.login();
        assertEquals(controller.login(),
                                "Your account is locked."
                        + " Please contact an administrator to unlock.");
    }



    /**
     * Tests the login() method for banned
     */
    @Test
    public final void testLoginBanned() {
        LoginController controller = new LoginController();
        User user = UserManager.find("newuser");
        user.setStatus(3);
        controller.setUsername("newuser");
        assertEquals(controller.login(),
                                "This account is banned.");
    }


    /**
     * Tests the login() method for successfully login as user
     */
    @Test
    public final void testLoginSuccessUser() {
        LoginController controller = new LoginController();
        User user = UserManager.find("newuser");
        user.setRole(1);
        controller.setUsername("newuser");
        assertEquals(controller.login(),
                                "openingmovies?faces-redirect=true");
    }


    /**
     * Tests the login() method for successfully login as admin
     */
    @Test
    public final void testLoginSuccessAdmin() {
        LoginController controller = new LoginController();
        User user = UserManager.find("newuser");
        user.setRole(2);
        controller.setUsername("newuser");
        assertEquals(controller.login(),
                                "usermanagement?faces-redirect=true");
    }
}
