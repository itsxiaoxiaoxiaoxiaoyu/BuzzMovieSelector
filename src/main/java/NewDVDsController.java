import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * NewDVDsController which communicates between
 * movies model and newdvds page.
 * @author huangdun
 */
@ManagedBean(eager = true)
@ViewScoped
public class NewDVDsController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * movies.
     */
    private List<Movie> movies;
    /**
     * Initialize the list of movies.
     */
    @PostConstruct
    public final void init() {
        movies = new ArrayList<>();
        searchNewDVDs();
    }
    /**
     * get movies list.
     * @return list of movies
     */
    public final List<Movie> getMovies() {
        return movies;
    }
    /**
     * search movies using MovieSearch by new DVDs.
     * and set movies list to be returned list
     */
    public final void searchNewDVDs() {
        movies = MovieSearcher.searchNewDVDs();
    }
}