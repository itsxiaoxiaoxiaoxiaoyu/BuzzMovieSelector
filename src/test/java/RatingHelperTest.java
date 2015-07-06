import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test for the RatingHelper test.
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
        //since movieId cannot contain an letter in Rotten Tomatoes API, we
        // can use it as a test case which doesn't affect our system at all
        final String testMovieId = "9999999a";

        final Integer testUser1 = 7;
        final Integer testUser2 = 8;

        // Delete test cases that might exist in the database.
        RatingHelper.deleteRatingByMovieIdAndUser(testMovieId, testUser1);
        RatingHelper.deleteRatingByMovieIdAndUser(testMovieId, testUser2);

        // Insert a new Rating.
        RatingHelper.insertOrUpdateRating(
                new Rating(-1, testUser1, testMovieId, null, 0));
        assertEquals(1, RatingHelper.findRatingsByMovieId(testMovieId).size());

        // Insert another new Rating.
        RatingHelper.insertOrUpdateRating(
                new Rating(-1, testUser2, testMovieId, null, 0));
        assertEquals(2, RatingHelper.findRatingsByMovieId(testMovieId).size());

        List<Rating> ratingList = RatingHelper
                .findRatingsByMovieId(testMovieId);

        for (Rating rating:ratingList) {
            /*test that the information in the database is correct and update
            infomation for further test.*/
            assertEquals(0, rating.getScore());
            rating.setScore(1);
            RatingHelper.insertOrUpdateRating(rating);
        }


        ratingList = RatingHelper
                .findRatingsByMovieId(testMovieId);

        for (Rating rating:ratingList) {
            /*test that the information in the database
            is updated successfully*/
            assertEquals(1, rating.getScore());
        }

        //ensure that no new Rating is created by the method
        assertEquals(2, RatingHelper.findRatingsByMovieId(testMovieId).size());

        // Delete all test data
        RatingHelper.deleteRatingByMovieIdAndUser(testMovieId, testUser1);
        RatingHelper.deleteRatingByMovieIdAndUser(testMovieId, testUser2);
    }

}
