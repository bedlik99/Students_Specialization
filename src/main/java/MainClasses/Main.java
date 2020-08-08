package MainClasses;

import Data.DataInfo;

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
        DataInfo.setup_LoadStudentsData(fileName);
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

/*        for (Map.Entry<String, Integer> entry : DataInfo.getSpecChosenTimes().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " signed students");

        }
        System.out.println("_____________________________________");*/

        redistributor.redistributeStudents();

        DataInfo.setup_SaveStudentsToFiles();


/*        for(int i=0; i<DataInfo.getCurrentSpecs().size();i++){

            System.out.println("\n\n\n\n##################################" +
                    "\n*******" + DataInfo.getCurrentSpecs().get(i) + "*******");
            for(Student student: DataInfo.getCurrentSpecs().get(i).getNewStudentsList()){
                System.out.println("\nID: " + student.getNumberID() + "\nSpec . Assigned: " + student.getSpecializationAssigned()
                + "\n____________________________________");
            }


        }*/




/*        System.out.println("\n\n\n ALL ^^_STUDENTS_^^ ");
        for (Student st : DataInfo.getListOfStudents()) {
            System.out.println("\n@STUDENT@\nId." + st.getNumberID() + ".  Av. Grade: " + st.getAverageGrade() + " Spec. prior. chosen: " +
                    "\n1st. " + st.getSpecsChosenOrder().get(0) +
                    "\n2nd. " + st.getSpecsChosenOrder().get(1) +
                    "\n3rd. " + st.getSpecsChosenOrder().get(2) +
                    "\n!. Specialization assigned: " + st.getSpecializationAssigned() + "\n");
        }*/


    }


}
