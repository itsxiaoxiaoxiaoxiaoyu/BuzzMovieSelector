import java.util.List;
import java.io.Serializable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a movie class.
 * @author huangdun
 */
public class Movie implements Serializable {
    /**
     * movieId.
     */
    private String movieId;

    /**
     * movie title.
     */
    private String title;

    /**
     * release year.
     */
    private String year;

    /**
     * poster url.
     */
    private String poster;

    /**
     * movie synopsis.
     */
    private String synopsis;

    /**
     * Movie theater release data.
     */
    private String theaterRelease;

    /**
     * Movie DVD release data.
     */
    private String dvdRelease;

    /**
     * movie runtime.
     */
    private String runtime;

    /**
     * movie genres.
     */
    private List<String> genres;

    /**
     * movie cast.
     */
    private List<String> cast;

    /**
     * directors of the movie.
     */
    private List<String> directors;

    /**
     * ratings of the movie.
     */
    private List<Rating> ratings;
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 61062690761538045L;

    /**
     * gets the title of the movie.
     * @return title
     */
    public final String getTitle() {
        return title;
    }
    /**
     * gets the id of the movie.
     * @return movie id
     */
    public final String getMovieId() {
        return movieId;
    }
    /**
     * gets the year of the movie.
     * @return year
     */
    public final String getYear() {
        return year;
    }
    /**
     * gets the poster of the movie.
     * @return poster
     */
    public final String getPoster() {
        return poster;
    }
    /**
     * gets the synopsis of the movie.
     * @return synopsis
     */
    public final String getSynopsis() {
        return synopsis;
    }
    /**
     * gets the theater release time of the movie.
     * @return theater release time
     */
    public final String getTheaterRelease() {
        return theaterRelease;
    }
    /**
     * gets the DVD release time of the movie.
     * @return DVD release time
     */
    public final String getDVDRelease() {
        return dvdRelease;
    }
    /**
     * gets the runtime of the movie.
     * @return runtime
     */
    public final String getRuntime() {
        return runtime;
    }
    /**
     * gets the genres of the movie.
     * @return genres
     */
    public final String getGenres() {
        return String.join(", ", genres);
    }
    /**
     * gets the casts of the movie.
     * @return casts
     */
    public final String getCast() {
        return String.join(", ", cast);
    }
    /**
     * gets the directors of the movie.
     * @return directors
     */
    public final String getDirectors() {
        return String.join(", ", directors);
    }
    /**
     * gets the average score of the movie
     * @return average score
     */
    public double getAvgScore() {
        if (ratings != null) {
            if (ratings.isEmpty()) {
                return 0;
            } else {
                return ratings.stream()
                        .mapToInt(Rating::getScore).average().getAsDouble();
            }
        }
        /*
        checkstyle doesn't support java8 yet, so this excetion is
        unavoidable
        */
        return 0;
    }

    /**
     * gets the ratings of the movie.
     * @return ratings
     */
    public final List<Rating> getRatings() {
        return ratings;
    }
    /**
     * sets the movie id of the movie.
     * @param inputMovieId movie id of the movie
     */
    public final void setMovieId(final String inputMovieId) {
        this.movieId = inputMovieId;
    }
    /**
     * sets the cast of the movie.
     * @param inputCast list of the cast
     */
    public final void setCast(final List<String> inputCast) {
        this.cast = inputCast;
    }
    /**
     * sets the genres of the movie.
     * @param inputGenres genres of the movie
     */
    public final void setGenres(final List<String> inputGenres) {
        this.genres = inputGenres;
    }
    /**
     * sets the directors of the movie.
     * @param inputDirectors directors of the movie
     */
    public final void setDirectors(final List<String> inputDirectors) {
        this.directors = inputDirectors;
    }
    /**
     * sets the title of the movie.
     * @param inputTitle title of the movie
     */
     public final void setTitle(final String inputTitle) {
        this.title = inputTitle;
    }
     /**
     * sets the year of the movie.
     * @param inputYear year of the movie
     */
    public final void setYear(final String inputYear) {
        this.year = inputYear;
    }
    /**
     * sets the poster of the movie.
     * @param inputPoster poster of the movie
     */
    public final void setPoster(final String inputPoster) {
        this.poster = inputPoster;
    }
    /**
     * sets the synopsis of the movie.
     * @param inputSynopsis synopsis of the movie
     */
    public final void setSynopsis(final String inputSynopsis) {
        this.synopsis = inputSynopsis;
    }
    /**
     * sets the theater release date of the movie.
     * @param inputTheaterRelease theater release date of the movie
     */
    public final void setTheaterReleaseDate(final String inputTheaterRelease) {
        this.theaterRelease = inputTheaterRelease;
    }
    /**
     * sets the DVD release time of the movie.
     * @param inputDVDRelease DVD release time of the movie
     */
    public final void setDVDRelease(final String inputDVDRelease) {
        this.dvdRelease = inputDVDRelease;
    }
    /**
     * sets the runtime of the movie.
     * @param inputRuntime runtime of the movie
     */
    public final void setRuntime(final String inputRuntime) {
        this.runtime = inputRuntime;
    }
    /**
     * sets the ratings of the movie.
     * @param inputRatings ratings of the movie
     */
    public final void setRatings(final List<Rating> inputRatings) {
        this.ratings = inputRatings;
    }
    /**
     * gets the rating of the movie by a particular user.
     * @param user the user that the rating is from
     * @return rating by the user
     */
    public final Rating getRatingByUser(final User user) {
        if (ratings != null) {
            for (Rating rating:ratings) {
                if (rating.getUser() == user.getUser()) {
                    return rating;
                }
            }
        }
        return new Rating(-1, user.getUser(), movieId, "", 0);
    }
}
