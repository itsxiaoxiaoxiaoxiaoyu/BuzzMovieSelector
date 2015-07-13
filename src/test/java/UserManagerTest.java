import javax.faces.application.FacesMessage;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test for find() in user manager.
 * @author Xiaoyu Yuan
 */
public class UserManagerTest {
    /**
     * Tests the find() method for user that does not exist.
     */
    @Test
    public final void testUserNull() {
        assertEquals(UserManager.find("should not be found").getFirstName(),
                                null);
        assertEquals(UserManager.find("should not be found").getLastName(),
                                null);
        assertEquals(UserManager.find("should not be found").getUsername(),
                                "should not be found");
        assertEquals(UserManager.find("should not be found").getStatus(),
                                0);
        assertEquals(UserManager.find("should not be found").getRole(),
                                0);
        assertEquals(UserManager.find("should not be found").getMajor(),
                                0);
    }
    /**
     * Tests the find() method for user exists.
     */
    @Test
    public final void testExistingUser() {
        User newuser = new User("newuser", "123456", "Shen", "Yang", 1, 1, 1);
        assertEquals(UserManager.find("newuser").getFirstName(),
                                newuser.getFirstName());
        assertEquals(UserManager.find("newuser").getLastName(),
                                newuser.getLastName());
        assertEquals(UserManager.find("newuser").getUsername(),
                                newuser.getUsername());
        assertEquals(UserManager.find("newuser").getStatus(),
                                newuser.getStatus());
        assertEquals(UserManager.find("newuser").getRole(),
                                newuser.getRole());
        assertEquals(UserManager.find("newuser").getMajor(),
                                newuser.getMajor());
    }
}
