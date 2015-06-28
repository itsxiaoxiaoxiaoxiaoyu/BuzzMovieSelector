import java.io.Serializable;
/**
 * Rating Class which represents the model.
 * rating entity
 * @author huangdun
 */
public final class Rating implements Serializable {
    /**
     * ID of the rating.
     */
    private int ratingId;
    /**
     * User associated with the rating.
     */
    private int user;
    /**
     * ID of the movie.
     */
    private String movieId;
    /**
     * The comment.
     */
    private String comment;
    /**
     * The score.
     */
    private int score;
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 61062690761538045L;
    /**
     * Basic constructor.
     */
    public Rating() { }

    /**
     * a Constructor which takes a lot of parameters.
     * @param newRatingId given ratingId
     * @param newUser given user id
     * @param newMovieId given movie id
     * @param newComment given comment
     * @param newScore given score
     */
    public Rating(final int newRatingId, final int newUser,
            final String newMovieId,
            final String newComment, final int newScore) {
        this.ratingId = newRatingId;
        this.user = newUser;
        this.movieId = newMovieId;
        this.comment = newComment;
        this.score = newScore;
    }

    /**
     * ratingId getter.
     * @return ratingId
     */
    public int getRatingId() {
        return ratingId;
    }

    /**
     * User getter.
     * @return user id
     */
    public int getUser() {
        return user;
    }

    /**
     * MovieId getter.
     * @return movieId
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * Comment getter.
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Score getter.
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * ratingId setter.
     * @param newRatingId given ratingId
     */
    public void setRatingId(final int newRatingId) {
        this.ratingId = newRatingId;
    }

    /**
     * user id setter.
     * @param newUser given user id
     */
    public void setUser(final int newUser) {
        this.user = newUser;
    }

    /**
     * movieId setter.
     * @param newMovieId given movieId
     */
    public void setMovieId(final String newMovieId) {
        this.movieId =  newMovieId;
    }

    /**
     * comment setter.
     * @param newComment given comment
     */
    public void setComment(final String newComment) {
        this.comment = newComment;
    }

    /**
     * Score setter.
     * @param newScore given score
     */
    public void setScore(final int newScore) {
        this.score = newScore;
    }
}
