package MainClasses;

import Data.DataInfo;

import java.io.FileNotFoundException;

/**
 * Main class. Includes input file name. Project is taking student request to get to certain Specialization
 * on their studies. Each student need to choose 3 specializations and prioritize it with 1st,2nd or 3rd power index.
 * The number of places in each specialization is the same: (num_of_students)/(num_of_specializations)
 * (+1 place to the specializations that are the chosen as a 1st and 2nd priority specialization the most)
 * <p>
 * Students could sign one specialization only to every priority index.(or different combination)
 * That means, they want to join that specific specialization and if they did not succeed(missing points),
 * they will be redistributed freely after other students will be signed to their specializations.
 */
public class Main {

    /**
     * Name of a file containing Data of students
     */
    private static final String fileName = "studentsData.json";

    /**
     * Redistributor class that allows us to redistribute students among chosen specializations
     */
    private static final Redistributor redistributor;


    static {

        try {
            DataInfo.setup_LoadStudentsData(fileName);

        } catch (FileNotFoundException e) {
            System.out.println("\nCouldn't load the file!\n " + e.getMessage());
        }

        redistributor = new Redistributor();
    }


    /**
     * Main function to call function that marks students to chosen specializations and
     * save results to "*_Students.csv" files. (* is the name of specialization)
     * Before calling main method , static initialization block is called. - It is the first function ever to be called
     * in the runtime of programme. The static initialization block acts as a input file loader and creates
     * instance of Redistributor class, which is responsible for logic of the programme(redistributing students)
     * @param args str
     */
    public static void main(String[] args) {

        redistributor.redistributeStudents();
        DataInfo.setup_SaveStudentsToFiles();


    }


}
