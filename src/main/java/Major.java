/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents an actual major.
 * @author huangdun
 */
public class Major {
    /**
     * majorId.
     */
    private int majorId;
    /**
     * label.
     */
    private String label;
    /**
     * Major constructor which takes a majorId and label.
     * @param newMajorId given majorId
     * @param newLabel given label
     */
    public Major(final int newMajorId, final String newLabel) {
        majorId = newMajorId;
        label = newLabel;
    }
    /**
     * MajorId getter.
     * @return majorId
     */
    public final int getMajorId() {
        return majorId;
    }
    /**
     * Label getter.
     * @return label
     */
    public final String getLabel() {
        return label;
    }
    /**
     * majorId setter.
     * @param newMajorId given majorId
     */
    public final void setMajorId(final int newMajorId) {
        majorId = newMajorId;
    }
    /**
     * label setter.
     * @param newLabel given label
     */
    public final void getLabel(final String newLabel) {
        label = newLabel;
    }
}