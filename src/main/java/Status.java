/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class representing a status. A status can be locked, banned or active.
 * This refers to the status of the user
 * @author zachjustice
 */
public class Status {
    /**
     * user id.
     */
    private int id;
       /**
     * user label.
     */
    private String label;

    /**
     * Status constructor takes in.
     * a status pk and the label associated with the pk
     * @param newid int
     * @param newlabel String
     */
    public Status(final int newid, final String newlabel) {
        this.id = newid;
        this.label = newlabel;
    }

    /**
     * Get the status pk.
     * @return int pk of status
     */
    public final int getId() {
        return id;
    }

    /**
     * Get the status's label.
     * @return string status label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Set the id of the status.
     * @param newid pk of the status
     */
    public final void setId(final int newid) {
        this.id = newid;
    }

    /**
     * Set the label of the status.
     * @param newlabel of the status
     */
    public final void setLabel(final String newlabel) {
        this.label = newlabel;
    }
}
