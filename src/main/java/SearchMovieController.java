
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * SearchMovieControlelr Class which communicates with
 * search movie page and Movie data models.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped

public class SearchMovieController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * List of movies returned by rotten tomatoes.
     */
    private List<Movie> movies;
    /**
     * criteria used to search for movies.
     */
    private String criteria;

    /**
     * Initialize the list of movies.
     */
    @PostConstruct
    public final void init() {
        movies = new ArrayList<>();
    }

    /**
     * get movies list.
     * @return list of movies
     */
    public final List<Movie> getMovies() {
        return movies;
    }

    /**
     * get input movie search criteria.
     * @return movie search criteria
     */
    public final String getCriteria() {
        return criteria;
    }

    /**
     * set movie search criteria.
     * @param input given criteria String
     */
     public final void setCriteria(final String input) {
        this.criteria =  input;
    }

    /**
     * search movies using MovieSearch by criteria
     * and set movies list to be returned list.
     */
    public final void searchMovie() {
        movies = MovieSearcher.searchFor(criteria);
    }
}
