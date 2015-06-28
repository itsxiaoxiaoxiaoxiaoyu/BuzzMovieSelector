/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents an actual gender.
 * @author huangdun
 */
public class Gender {
    /**
     * majorId.
     */
    private int genderId;
    /**
     * label.
     */
    private String label;
    /**
     * Gender constructor which takes a genderId and label.
     * @param newGenderId given majorId
     * @param newLabel given label
     */
    public Gender(final int newGenderId, final String newLabel) {
        genderId = newGenderId;
        label = newLabel;
    }
    /**
     * GenderId getter.
     * @return majorId
     */
    public final int getGenderId() {
        return genderId;
    }
    /**
     * Label getter.
     * @return label
     */
    public final String getLabel() {
        return label;
    }
    /**
     * genderId setter.
     * @param newGenderId given majorId
     */
    public final void setGenderId(final int newGenderId) {
        genderId = newGenderId;
    }
    /**
     * label setter.
     * @param newLabel given label
     */
    public final void getLabel(final String newLabel) {
        label = newLabel;
    }
}