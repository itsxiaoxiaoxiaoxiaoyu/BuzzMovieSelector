import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Opening Movies Controller which communicates between
 * movies model and openingmovie page.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped

public class OpeningMoviesController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * List of movies.
     */
    private List<Movie> movies;

    /**
     * Initialize the list of movies.
     */
    @PostConstruct
    public final void init() {
        movies = new ArrayList<>();
        searchOpeningMovies();
    }

    /**
     * get movies list.
     * @return list of movies
     */
    public final List<Movie> getMovies() {
        return movies;
    }

    /**
     * search movies using MovieSearch by opening movies
     * and set movies list to be returned list.
     */
    public final void searchOpeningMovies() {
        movies = MovieSearcher.searchOpeningMovies();
    }
}
