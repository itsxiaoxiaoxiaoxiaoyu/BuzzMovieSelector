
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is used by the Admin User Management to make changes to user's
 * statuses.
 * @author zachjustice
 */

@ManagedBean(eager = true)
@ViewScoped

public class UserListController implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6106269076155338045L;
    /**
     * List of users currently in the system.
    */
    private List<User> users;
    /**
     * Statuses available for a user: active, banned, locked.
     */
    private List<SelectItem> statuses;

    /**
     * Initialize the list of users and statuses.
     */
    @PostConstruct
    public final void init() {
        users = UserManager.getAllUsers();
        statuses = new ArrayList<>();
        List<Status> allStatuses = UserManager.getStatuses();

        for (Status status:allStatuses) {
            if (status.getId() > 0) {
                statuses.add(new SelectItem(status.getId(), status.getLabel()));
            }
        }
    }

    /**
     * Get all users in the system.
     * @return List of users
     */
    public final List<User> getUsers() {
        return users;
    }

    /**
     * Get all statuses in the system (locked, active, banned).
     * @return List of statuses
     */
    public final List<SelectItem> getStatuses() {
        return statuses;
    }

    /**
     * Commit changes to the user list to the db.
     */
    public final void commit() {
        System.out.println(users.get(0).getStatus());

        for (User user : users) {
            UserManager.updateUser(user);
        }

        FacesMessage message = new FacesMessage(
            FacesMessage.SEVERITY_INFO,
            "User data updated",
            null
        );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}



