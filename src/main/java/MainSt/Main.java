package MainSt;

import Data.DataInfo;
import Data.Models.Student;

/**
 * Main class. Includes variables immutable variables. Project is taking student request to get to certain Specialization
 * on their studies. Each student need to choose 3 specializations and prioritize it with 1st,2nd or 3rd power index.
 * The number of places in each specialization is the same, so it is like: num_of_students/3
 * (+1 place to the specialization that is the most times chosen as a 1st priority specialization)
 * Students could sign one specialization only to every priority index.(or different combination)
 * That means, they want to join that specific specialization and if they did not success(missing points),
 * they will be redistributed freely after other students will be signed to their specializations.
 *
 */
public class Main {

    /**
     * Name of a file containing Data of students
     */
    private static final String fileName = "studentsData.json";

    /**
     * Static initialization of static function that is loading data of all students.
     */
    static {
        DataInfo.loadStudentsData(fileName);
    }

    public static void main(String[] args) {

        for (Student student: DataInfo.getListOfStudents()) {
            System.out.println(student.getSpecialization());
        }

    }



}
