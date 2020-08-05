package Data;

import Data.Models.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class DataInfo {


    /**
     * Map that will contain names of specialization and numbers that says how many students
     * declared this specialization as 1st priority. - We need this information to make places for students
     * in specializations.
     */
    private static final Map<String,Integer> specChosenTimes = new LinkedHashMap<>();

    /**
     * Object mapper needed while mapping Json String to node
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Json Parser needed to parse json file to Json Array
     */
    private static JSONParser jsonParser = new JSONParser();

    /**
     * List containing Students, whose data is read from json file
     */
    private static ArrayList<Student> listOfStudents = new ArrayList<>();

    /**
     * List of names of specialization that students could sign to.
     */
    private static ArrayList<String> specNames = new ArrayList<>();

    /**
     * Method that reads json file, parsing it to JSONArray, mapping it to JsonNode and then to our objects class --> Student.class
     * Temporary variable tempStudent is being initialized with single element(object) of JSONArray list
     * and then added to list of students
     * @param fileName name of json file
     */
    public static void loadStudentsData(String fileName){

        try {

            Path path = Paths.get("src/main/resources/", fileName);

            String toLoadFile = path.toFile().getAbsolutePath(); // --> File file = path.toFile();

            FileReader jsonFile = new FileReader(toLoadFile);

            JSONArray jArray = (JSONArray) jsonParser.parse(jsonFile);

            Iterator<Student> iterator = jArray.iterator();

            String jsonString,firstSpecPriorPicked;
            JsonNode node;
            Student tempStudent;

            int i = 0;
            while (iterator.hasNext() && listOfStudents.size()!=jArray.size()) {
                jsonString = jArray.get(i).toString();
                node = mapper.readTree(jsonString);
                tempStudent = mapper.treeToValue(node, Student.class);

                firstSpecPriorPicked = tempStudent.getSpecialization().getPick1();
                listOfStudents.add(tempStudent);

                if(specChosenTimes.containsKey(firstSpecPriorPicked)){
                    specChosenTimes.put(firstSpecPriorPicked, (specChosenTimes.get(firstSpecPriorPicked)+1) );
                }else{
                    specNames.add(firstSpecPriorPicked);
                    specChosenTimes.put(firstSpecPriorPicked,1);
                }


                i++;
            }


        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static ArrayList<String> getSpecNames() {
        return specNames;
    }

    public static Map<String, Integer> getSpecChosenTimes() {
        return specChosenTimes;
    }

    public static ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

}
