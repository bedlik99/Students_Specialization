package Data;

import Data.Models.Specialization;
import Data.Models.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class DataInfo is abstract class. Its fields contains of read-from-file containers(Lists) with specific objects
 * used in different parts of programme. All of its fields are static so we can get into them by name class 'DataInfo.*'
 */
public abstract class DataInfo {

    /**
     * Map that will contain names of specialization and numbers that says how many students
     * declared this specialization as 1st priority. - We need this information to make places for students
     * in specializations.
     */
    private static final Map<String, Integer> specChosenTimes = new LinkedHashMap<>();

    /**
     * Object mapper needed while mapping Json String to node
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Json Parser needed to parse json file to Json Array
     */
    private static final JSONParser jsonParser = new JSONParser();

    /**
     * List containing Students, whose data is read from json file
     */
    private static final ArrayList<Student> listOfStudents = new ArrayList<>();

    /**
     * List of names of specialization that students could sign to.
     */
    private static final ArrayList<Specialization> currentSpecs = new ArrayList<>();

    private static final List<String[]> studentsOutput = new ArrayList<>();

    /**
     * Method that reads json file, parsing it to JSONArray, mapping it to JsonNode and then to our objects class --> Student.class
     * Temporary variable tempStudent is being initialized with single element(object) of JSONArray list
     * and then added to list of students
     * In addidtion Student objects in list are being sorted descending with their Average Grade.(from max to min Av.Grade)
     *
     * @param fileName name of json file
     */
    public static int setup_LoadStudentsData(String fileName) throws FileNotFoundException {

        try {

            Path path = Paths.get("src/main/resources/InputFile/", fileName);

            String toLoadFile = path.toFile().getAbsolutePath(); // --> File file = path.toFile();

            FileReader jsonFile = new FileReader(toLoadFile);

            JSONArray jArray = (JSONArray) jsonParser.parse(jsonFile);

            Iterator<Student> iterator = jArray.iterator();

            String jsonString, firstSpecPriorPicked;
            JsonNode node;
            Student tempStudent;

            int i = 0;
            while (iterator.hasNext() && listOfStudents.size() != jArray.size()) {
                jsonString = jArray.get(i).toString();
                node = mapper.readTree(jsonString);
                tempStudent = mapper.treeToValue(node, Student.class);


                firstSpecPriorPicked = tempStudent.getSpecsChosenOrder().get(0);
                listOfStudents.add(tempStudent);

                if (specChosenTimes.containsKey(firstSpecPriorPicked)) {
                    specChosenTimes.put(firstSpecPriorPicked, (specChosenTimes.get(firstSpecPriorPicked) + 1));
                } else {
                    specChosenTimes.put(firstSpecPriorPicked, 1);
                }


                i++;
            }


            listOfStudents.sort(new Comparator<Student>() {
                @Override
                public int compare(Student stud1, Student stud2) {
                    return -(Double.compare(stud1.getAverageGrade(), stud2.getAverageGrade()));
                }
            });


        }catch (NullPointerException e){
            return -1;

        }catch (FileNotFoundException e){
            throw new FileNotFoundException();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

            if (!listOfStudents.isEmpty())
                for (String specName : listOfStudents.get(0).getAvailableSpecs()) {
                    currentSpecs.add(new Specialization(specName));

                }


        return 0;
        }



    /**
     * Another 'Setup' method, however called at the 'end' of program lifetime. It saves students to "*_Students.csv" files
     * Where '*' in the name of file is the name of specialization that student was assigned to.
     * The number of files is the number of specializations that were possible for students to sign to.
     */
    public static int setup_SaveStudentsToFiles() {
        String[] specNameFile = new String[currentSpecs.size()];

        for (Student student : listOfStudents) {
            String[] studentInfo = new String[4];
            studentInfo[0] = Long.toString(student.getNumberID());
            studentInfo[1] = student.getFirstName();
            studentInfo[2] = student.getLastName();
            studentInfo[3] = student.getSpecializationAssigned();

            studentsOutput.add(studentInfo);
        }

        for (int tmp = 0; tmp < currentSpecs.size(); tmp++) {
            specNameFile[tmp] = currentSpecs.get(tmp).getSpecializationName().concat("_Students.csv");

            try (CSVWriter writer1 = new CSVWriter(new FileWriter("src/main/resources/OutputFiles/" + specNameFile[tmp]))) {
                writer1.writeNext(new String[]{"albumID", "First Name", "Last Name", "Specialization"});

                for (int i = 0; i < listOfStudents.size(); i++) {

                    if (listOfStudents.get(i).getSpecializationAssigned().equals(currentSpecs.get(tmp).getSpecializationName())) {
                        writer1.writeNext(studentsOutput.get(i));

                    }


                }

            } catch (IOException e) {
                System.out.println("\nNie udalo sie utworzyc pliku nr " + (tmp+1) + ". w podanej lokalizacji: ");
                System.out.println(e.getMessage());
            }

        }

        return 0;
    }


    public static ArrayList<Specialization> getCurrentSpecs() {
        return currentSpecs;
    }

    public static Map<String, Integer> getSpecChosenTimes() {
        return specChosenTimes;
    }

    public static ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

}
