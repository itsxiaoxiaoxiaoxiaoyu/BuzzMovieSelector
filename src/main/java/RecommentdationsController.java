import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * RecommendationsController which communicates between.
 * movies model and recommendations page
 * @author huangdun
 */

@ManagedBean(eager = true)
@ViewScoped
public class RecommentdationsController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * filters.
     */
    private Map<String, Integer> filters;
    /**
     * majors.
     */
    private List<SelectItem> majors;
    /**
     * selectedMajors.
     */
    private int selectedMajor;
    /**
     * movies.
     */
    /**
     * genders.
     */
    private List<SelectItem> genders;
    /**
     * selectedGender.
     */
    private int selectedGender;
    /**
     * movies.
     */
    private List<Movie> movies;
    /**
     * Initialize the list of majors.
     * and get a movie list without any filter
     */
    @PostConstruct
    public final void init() {
        filters = new HashMap<>();
        majors = new ArrayList<>();
        List<Major> allMajors = UserManager.getAllMajors();
        for (Major major:allMajors) {
            if (major.getMajorId() > 0) {
                majors.add(new SelectItem(major.getMajorId(),
                        major.getLabel()));
            }
        }
        genders = new ArrayList<>();
        List<Gender> allGenders = UserManager.getAllGenders();
        for (Gender gender:allGenders) {
            if (gender.getGenderId() > 0) {
                genders.add(new SelectItem(gender.getGenderId(),
                        gender.getLabel()));
            }
        }
        updateMovies();
    }
    /**
    * get the string of the selectedMajor of.
    * the user
    * @return selectedMajor of the user
    */
    public final int getSelectedMajor() {
        return selectedMajor;
    }
     /**
    * get the list of the selectedMajor of.
    * the user
    * @return the list of the selectedMajor of the user
    */
    public final List<SelectItem> getMajors() {
        return majors;
    }
    /**
    * get the list of the genders of.
    * the user
    * @return genders the list of the major
    */
    public final List<SelectItem> getGenders() {
        return genders;
    }
    /**
    * get the selected gender of
    * the user.
    * @return selectedMajor in integer
    */
    public final int getSelectedGender() {
        return selectedGender;
    }
    /**
    * set selectedMajor of the current user.
    * to the given one
    * @param newSelectedMajor the given selectedMajor in int
    */
    public final void setSelectedMajor(final int newSelectedMajor) {
        selectedMajor = newSelectedMajor;
    }
    /**
    * set List<SelectItem> majors of the current user.
    * to the given one
    * @param newMajors List<SelectItem> majors the given selectedMajor in List
    */
    public final void setMajors(final List<SelectItem> newMajors) {
        majors = newMajors;
    }
    /**
    * set the selected gender of the current user.
    * to the given one
    * @param newSelectedGender the given selected gender in integer
    */
    public final void setSelectedGender(final int newSelectedGender) {
        selectedGender = newSelectedGender;
    }
    /**
     * get movies list.
     * @return list of movies
     */
    public final List<Movie> getMovies() {
        return movies;
    }
    /**
     * Ajax Helper method, once the filter info changes,
     * the movie list would be changed too.
     */
    public final void onFilterChange() {
        if (selectedMajor > 0) {
            filters.put("major", selectedMajor);
        } else {
            filters.remove("major");
        }

        if (selectedGender > 0) {
            filters.put("gender", selectedGender);
        } else {
            filters.remove("gender");
        }
        updateMovies();
    }
    /**
     * Update the movie list based on filters.
     */
    private void updateMovies() {
        List<String> movieIds = RatingHelper.findFilteredTopMovies(filters);
        movies = MovieSearcher.addMoviesFromList(movieIds);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",
                "Recommendations Updated."));
    }
}
