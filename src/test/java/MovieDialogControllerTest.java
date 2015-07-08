import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test for Movie Dialog Controller.
 * @author Ang Deng
 */
public class MovieDialogControllerTest {
    /**
     * Tests the getCommentLabel() method for null comment.
     */
    @Test
    public final void testGetCommentLabelNull() {
        MovieDialogController controller = new MovieDialogController();
        assertEquals(controller.getCommentLabel(),
                                "+Add your comment to this movie");
    }
    /**
     * Tests the getCommentLabel() method for empty comment.
     */
    @Test
    public final void testGetCommentLabelEmpty() {
        MovieDialogController controller = new MovieDialogController();
        controller.setRating(new Rating());
        controller.setComment("");
        assertEquals(controller.getCommentLabel(),
                                "+Add your comment to this movie");
    }
    /**
     * Tests the getCommentLabel() method for long comment.
     */
    @Test
    public final void testGetCommentLabelLong() {
        MovieDialogController controller = new MovieDialogController();
        controller.setRating(new Rating());
        controller.setComment("12345678901234567890123");
        assertEquals(controller.getCommentLabel(),
                                "12345678901234567890...");
    }
    /**
     * Tests the getCommentLabel() method for normal comment.
     */
    @Test
    public final void testGetCommentLabelNormal() {
        MovieDialogController controller = new MovieDialogController();
        controller.setRating(new Rating());
        controller.setComment("1234567890123");
        assertEquals(controller.getCommentLabel(),
                                "1234567890123");
    }
    /**
     * Comment Label.
     * @return add comment suggestion if no comment exists,
     * cut the comment if it is too long
     */ /*
    public final String getCommentLabel() {
        final int limitation = 20;
        if (rating != null) {
            if (rating.getComment().isEmpty()) {
                return "+Add your comment to this movie";
            } else if (rating.getComment().length() > limitation) {
               return rating.getComment().substring(0, limitation) + "...";
            } else {
                return rating.getComment();
            }
        } else {
            return "+Add your comment to this movie";
        }
    }*/
}
