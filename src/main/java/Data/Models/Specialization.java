package Data.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains information about name of specializations and
 * has their own list of students that were assigned to.
 */
public class Specialization {

    private String specializationName;
    private final List<Student> newStudentsList = new ArrayList<>();

    public Specialization(String specializationName){
        this.specializationName = specializationName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public List<Student> getNewStudentsList() {
        return newStudentsList;
    }

    @Override
    public String toString() {
        return "Specialization = " + specializationName;
    }
}
