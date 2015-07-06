import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the RatingHelper test
 * @author huangdun
 */
public class RatingHelperTest {

    /**
     * Test of findRatingsByMovieIds.
     * By Dun Huang
     */
    @Test
    public final void testFindRatingsByMovieId() {
        List<Rating> movieList1 = RatingHelper.findRatingsByMovieId(null);
        //When moveId passed in is null, return a an empty MovieList;
        assertEquals(0, movieList1.size());
        final Integer adminUser = 1;
        RatingHelper.insertOrUpdateRating(new Rating(-1, 7, ));
        
        
    }

}
