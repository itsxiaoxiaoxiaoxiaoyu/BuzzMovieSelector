import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * MovieSearcher class which helps search for movies with different
 * criterion and returns lists of movies.
 * @author huangdun
 */
public final class MovieSearcher {
    /**
     * private Constructor.
     */
    private MovieSearcher() { };

    /**
     * searchFor method which takes a string as criteria and sends it
     * to Rotten Tomatoes API and translate it back to a list of
     * Movies.
     * @param criteria a String which contains search criteria
     * @return a list of movies which satisfies the criteria
     */
    public static List<Movie> searchFor(final String criteria) {
        List<Movie> movies = new ArrayList<>();

        try {
            String requestType = "/movies.json";
            Map<String, String> requestMap = new TreeMap<>();
            requestMap.put("q", criteria);
            requestMap.put("page_limit", "24");
            String searchResult = APIHelper.apiRequest(requestMap, requestType);
            if (searchResult != null) {
                JSONObject resultJSON = new JSONObject(searchResult);
                JSONArray movieArray = resultJSON.getJSONArray("movies");
                List<String> idList = new ArrayList<>();
                for (int i = 0; i < movieArray.length(); i++) {
                    JSONObject movieObject = movieArray.getJSONObject(i);
                    idList.add(movieObject.getString("id"));
                }
                movies = addMoviesFromList(idList);
            }
        } catch (JSONException ex) {
            Logger.getLogger(MovieSearcher.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return movies;
    }

    /**
     * searchForNewDvDs method which translate new DVDs request
     * to Rotten Tomatoes API and translate it back to a list of Movies.
     * @return a list of movies which are new DVDs releases
     */
    public static List<Movie> searchNewDVDs() {
        List<Movie> movies = new ArrayList<>();

        try {
            String requestType = "/lists/dvds/new_releases.json";
            Map<String, String> requestMap = new TreeMap<>();
            requestMap.put("page_limit", "24");
            String searchResult = APIHelper.apiRequest(requestMap, requestType);
            if (searchResult != null) {
                JSONObject resultJSON = new JSONObject(searchResult);
                JSONArray movieArray = resultJSON.getJSONArray("movies");
                List<String> idList = new ArrayList<>();
                for (int i = 0; i < movieArray.length(); i++) {
                    JSONObject movieObject = movieArray.getJSONObject(i);
                    idList.add(movieObject.getString("id"));
                }
                movies = addMoviesFromList(idList);
            }
        } catch (JSONException ex) {
            Logger.getLogger(MovieSearcher.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return movies;
    }

    /**
     * searchForNewDvDs method which translate new theater releases request
     * to Rotten Tomatoes API and translate it back to a list of Movies.
     * @return a list of movies which are new theater releases
     */
    public static List<Movie> searchOpeningMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            String requestType = "/lists/movies/opening.json";
            Map<String, String> requestMap = new TreeMap<>();
            requestMap.put("page_limit", "24");
            String searchResult = APIHelper.apiRequest(requestMap, requestType);
            if (searchResult != null) {
                JSONObject resultJSON = new JSONObject(searchResult);
                JSONArray movieArray = resultJSON.getJSONArray("movies");
                List<String> idList = new ArrayList<>();
                for (int i = 0; i < movieArray.length(); i++) {
                    JSONObject movieObject = movieArray.getJSONObject(i);
                    idList.add(movieObject.getString("id"));
                }
                movies = addMoviesFromList(idList);
            }
        } catch (JSONException ex) {
            Logger.getLogger(MovieSearcher.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return movies;
    }

    /**
     * A Helper function which takes a list of MovieIds from different
     * search functions and return a list of Movies.
     * @param idList a list of movieIds
     * @return a list of Movies from movieArray
     */

    public static List<Movie> addMoviesFromList(final List<String> idList) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < idList.size(); i++) {
            try {
                String movieId = idList.get(i);
                String requestType = "/movies/" + movieId + ".json";
                String movieResult = APIHelper.apiRequest(null, requestType);
                if (movieResult != null) {
                    JSONObject resultJSON = new JSONObject(movieResult);

                    String title = "";
                    String year = "";
                    String poster = "";
                    String synopsis = "";
                    String theaterRelease = "";
                    String dvdRelease = "";
                    String runtime = "";
                    List<String> genres = new ArrayList<>();
                    List<String> cast = new ArrayList<>();
                    List<String> directors = new ArrayList<>();
                    List<Rating> ratings;

                    if (resultJSON.has("title")) {
                        title = resultJSON.getString("title");
                    }

                    if (resultJSON.has("year")) {
                        year = resultJSON.getString("year");
                    }

                    if (resultJSON.has("posters")
                            && resultJSON.getJSONObject("posters")
                                    .has("original")) {
                        poster = resultJSON.getJSONObject("posters")
                                .getString("original");
                    }
                    if (resultJSON.has("synopsis")) {
                        synopsis = resultJSON.getString("synopsis");
                    }

                    if (resultJSON.has("release_dates")
                            && resultJSON.getJSONObject("release_dates")
                                    .has("theater")) {
                        theaterRelease = resultJSON
                                .getJSONObject("release_dates")
                                .getString("theater");
                    }

                    if (resultJSON.has("release_dates")
                            && resultJSON.getJSONObject("release_dates")
                                    .has("dvd")) {
                        dvdRelease = resultJSON
                                .getJSONObject("release_dates")
                                .getString("dvd");
                    }

                    if (resultJSON.has("runtime")) {
                        runtime = resultJSON.getString("runtime");
                    }

                    if (resultJSON.has("genres")) {
                        JSONArray genresJSON = resultJSON
                                .getJSONArray("genres");

                        for (int j = 0; j < genresJSON.length(); j++) {
                            genres.add(genresJSON.getString(j));
                        }
                    }

                    if (resultJSON.has("abridged_cast")) {
                        JSONArray castsJSON = resultJSON
                                .getJSONArray("abridged_cast");

                        for (int j = 0; j < castsJSON.length(); j++) {
                            cast.add(castsJSON.getJSONObject(j)
                                    .getString("name"));
                        }
                    }

                    if (resultJSON.has("abridged_directors")) {
                        JSONArray directorsJSON = resultJSON
                                .getJSONArray("abridged_directors");

                        for (int j = 0; j < directorsJSON.length(); j++) {
                            directors.add(directorsJSON.getJSONObject(j)
                                    .getString("name"));
                        }
                    }

                    ratings = RatingHelper.findRatingsByMovieId(movieId);

                    Movie movie = new Movie();
                    movie.setMovieId(movieId);
                    movie.setTitle(title);
                    movie.setYear(year);
                    movie.setRuntime(runtime);
                    movie.setCast(cast);
                    movie.setTheaterReleaseDate(theaterRelease);
                    movie.setDVDRelease(dvdRelease);
                    movie.setDirectors(directors);
                    movie.setGenres(genres);
                    movie.setPoster(poster);
                    movie.setRatings(ratings);
                    movie.setSynopsis(synopsis);
                    movies.add(movie);
                }
            } catch (JSONException ex) {
                Logger.getLogger(MovieSearcher.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        }

        return movies;
    }

}
