package Data.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class includes fields that are loaded from JSONFile. Object of class student, is being held in list.
 */
public class Student {

    /**
     * Id of student - number from 270 000 to 350 000
     */
    private long numberID;

    /**
     * First name of student
     */
    private String firstName;

    /**
     * Last name of student
     */
    private String lastName;

    /**
     * Available specs that student could have chosen. Number of elements is number of specs, student could have chosen.
     */
    private final List<String> availableSpecs = new ArrayList<>();

    /**
     * Students specialization picking-order. Number of elements is number of specs, student could have chosen.
     */
    private final List<String> specsChosenOrder = new ArrayList<>();

    /**
     * Specialization that student were assigned, according to logic of program.
     */
    private String specializationAssigned;

    /**
     * Value used when reditributing students to their highest priority picked specializations.
     */
    private double averageGrade;


    public List<String> getAvailableSpecs() {
        return availableSpecs;
    }

    public List<String> getSpecsChosenOrder() {
        return specsChosenOrder;
    }

    public long getNumberID() {
        return numberID;
    }

    public void setNumberID(long numberID) {
        this.numberID = numberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getSpecializationAssigned() {
        return specializationAssigned;
    }

    public void setSpecializationAssigned(String specializationAssigned) {
        this.specializationAssigned = specializationAssigned;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }


    @Override
    public String toString() {
        return "\n#Student#" +
                "\nnumberID=" + numberID +
                "\nfirstName=' " + firstName + '\'' +
                "\nlastName=' " + lastName + '\'' +
                "\nspecializationAssigned=' " + specializationAssigned + '\'' +
                "\naverageGrade= " + averageGrade;
    }
}
