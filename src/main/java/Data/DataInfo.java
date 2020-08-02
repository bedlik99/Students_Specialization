package Data;

import Data.Models.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class DataInfo {

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
     * Method that reads json file, parsing it to JSONArray, mapping it to JsonNode and then to our objects class --> Student.class
     * Temporary variable tempStudent is being initialized with single element(object) of JSONArray list
     * and then added to list of students
     * @param fileName name of json file
     */
    public static void loadStudentsData(String fileName){

        try {

            Path path = Paths.get("src/main/resources/", fileName);

            File file = path.toFile();

            String toLoadFile = file.getAbsolutePath();

            FileReader jsonFile = new FileReader(toLoadFile);

            JSONArray jArray = (JSONArray) jsonParser.parse(jsonFile);

            Iterator<Student> iterator = jArray.iterator();

            String jsonString;
            JsonNode node;
            Student tempStudent;

            int i = 0;
            while (iterator.hasNext() && listOfStudents.size()!=jArray.size()) {
                jsonString = jArray.get(i).toString();
                node = mapper.readTree(jsonString);
                tempStudent = mapper.treeToValue(node, Student.class);
                listOfStudents.add(tempStudent);

                i++;
            }


        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }


    }



    public static ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

}
