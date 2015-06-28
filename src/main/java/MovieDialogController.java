import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * MovieDialogController class which communicate with.
 * moviedialog page and Movie information
 * @author huangdun
 */

@ManagedBean(eager = true)
@SessionScoped

public class MovieDialogController implements Serializable  {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;

    /**
     * movie which is selected.
     */
    private Movie selectedMovie;

    /**
     * rating object of the movie.
     */
    private Rating rating;

    /**
     * loginBean info.
     */
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    /**
    * set the login bean of the current user
    * to the given one.
    * @param inputLoginBean the given login bean
    */
    public final void setLoginBean(final LoginBean inputLoginBean) {
        this.loginBean = inputLoginBean;
    }

    /**
     * Set the selectedMovie to be given movie.
     * @param inputSelectedMovie a movie to be set to selectedMovie
     */
    public final void setSelectedMovie(final Movie inputSelectedMovie) {
        this.selectedMovie = inputSelectedMovie;
        if (loginBean != null) {
            rating = selectedMovie.getRatingByUser(loginBean.getCurrentUser());
        }
    }

    /**
     * Score setter.
     * @param score the given score of the rating
     */
    public final void setScore(final int score) {
        if (rating != null) {
            rating.setScore(score);
        }
    }

    /**
     * Comment setter.
     * @param comment the given comment of the rating
     */
     public final void setComment(final String comment) {
        if (rating != null) {
            rating.setComment(comment);
        }
    }

    /**
     * Score getter.
     * @return score if it exists 0 if not
     */
    public final int getScore() {
        if (rating != null) {
            return rating.getScore();
        } else {
            return 0;
        }
    }

    /**
     * Comment getter.
     * @return comment if it exists empty string if not
     */
    public final String getComment() {
        if (rating != null) {
            return rating.getComment();
        } else {
            return "";
        }
    }

    /**
     * Comment Label.
     * @return add comment suggestion if no comment exists,
     * cut the comment if it is too long
     */
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
    }

    /**
     * Get the selectedMovie.
     * @return a Movie object which is selected
     */
    public final Movie getSelectedMovie() {
        return selectedMovie;
    }

    /**
     * Update the database according to the current rating.
     */
    public final void submitRating() {
        if ((rating != null && selectedMovie != null)
                && (rating.getScore() > 0
                || !rating.getComment().isEmpty())) {
            RatingHelper.insertOrUpdateRating(rating);
            selectedMovie.setRatings(
                    RatingHelper.findRatingsByMovieId(selectedMovie
                            .getMovieId()
                    ));
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful",
                    "You have submitted your rating."));
        }

        RequestContext.getCurrentInstance().execute("PF('movieDialog').hide()");
    }
}
