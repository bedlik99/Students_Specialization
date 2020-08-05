package MainClasses;

import Data.DataInfo;

import java.util.Map;

/**
 * Main class. Includes input file name. Project is taking student request to get to certain Specialization
 * on their studies. Each student need to choose 3 specializations and prioritize it with 1st,2nd or 3rd power index.
 * The number of places in each specialization is the same: (num_of_students)/(num_of_specializations)
 * (+1 place to the specializations that are the chosen as a 1st and 2nd priority specialization the most)
 *
 * Students could sign one specialization only to every priority index.(or different combination)
 * That means, they want to join that specific specialization and if they did not succeed(missing points),
 * they will be redistributed freely after other students will be signed to their specializations.
 *
 */
public class Main {

    /**
     * Name of a file containing Data of students
     */
    private static final String fileName = "studentsData.json";

    /**
     * Static initialization block in which data of all students is being loaded.
     */
    static {
        DataInfo.loadStudentsData(fileName);
    }

    public static void main(String[] args) {


        for(Map.Entry<String,Integer> entry: DataInfo.getSpecChosenTimes().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue() + " signed students");

        }
        System.out.println("_____________________________________");
        Redistributor.fillSpecsPlaces();

    }



}
