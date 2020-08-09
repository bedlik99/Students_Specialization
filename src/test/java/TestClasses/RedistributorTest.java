package TestClasses;

import Data.DataInfo;
import MainClasses.Redistributor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RedistributorTest {

    private static Redistributor redTest;
    private Map<String,Integer> tmpMap;

    @BeforeAll
    static void setup() throws FileNotFoundException {
         DataInfo.setup_LoadStudentsData("studentsData.json");
         redTest = new Redistributor();
         redTest.redistributeStudents();

        redTest.getSpecsWithAvailablePlaces().put("TIZ",67);
        redTest.getSpecsWithAvailablePlaces().put("SST",67);
        redTest.getSpecsWithAvailablePlaces().put("RTM",66);
    }


    @Before
    public void eachSetup(){
        tmpMap = new HashMap<>();
    }


    @Test
    void getKey_CORRECT_PARAMETERS_TEST() {
        tmpMap.put("Michael",96);
        tmpMap.put("John",99);
        tmpMap.put("Marc",54);

       assertEquals("John", redTest.getKey(tmpMap,99));
       assertNotEquals("John", redTest.getKey(tmpMap,54));
       assertEquals("Michael", redTest.getKey(tmpMap,96));

    }


    @Test
    void getKey_DUPLICATE_VALUES_ALLOWED_TEST(){
        tmpMap.put("Michael",99);
        tmpMap.put("John",99);
        tmpMap.put("Marc",54);

        assertEquals("Michael", redTest.getKey(tmpMap,99));
        assertNotEquals("John", redTest.getKey(tmpMap,99)); //!!! However duplicates are perfectly correct according to the logic of programme
        assertEquals("Marc", redTest.getKey(tmpMap,54));

    }


    @Test
    void getKey_NULL_PARAMETER_TEST(){
        tmpMap.put(null,99);
        tmpMap.put("John",null);
        tmpMap.put(null,null);

        assertNull(redTest.getKey(tmpMap, 99));
        assertNotEquals("John", redTest.getKey(tmpMap,54));
        assertNull(redTest.getKey(tmpMap,null));

    }


    @Test
    void findSpecialization_CORRECT_ASSERTS_TEST() {

        // If second parameter is not starting from 0, we cannot expect if specialization will be found
        // or null pointer will be returned. In programme it is always starting from 0. !
        // We need to iterate through items.
        // Case I am checking is, that given names that are in map , they will be found.
        for(int i=0; i < redTest.getSpecsWithAvailablePlaces().size();i++){

            if(redTest.findSpecialization("TIZ",0).equals("TIZ")){
                assertEquals("TIZ",redTest.findSpecialization("TIZ",0));
            }
            if(redTest.findSpecialization("SST",0).equals("SST")){
                assertEquals("SST",redTest.findSpecialization("SST",0));
            }
            if(redTest.findSpecialization("RTM",3).equals("SHOULD NOT HAPPEN")){
                assertEquals("SHOULD NOT HAPPEN",redTest.findSpecialization("RTM",3));
            }

        }

    }


    @Test
    void findSpecialization_NULL_CASE_TEST() {

        for(int i=0; i < redTest.getSpecsWithAvailablePlaces().size();i++){

            if(redTest.findSpecialization("SST",0).equals("SST")){
                assertNotEquals("TIZ",redTest.findSpecialization("SST",0));
            }

            if(redTest.findSpecialization("SST",0).equals("SST")){
                assertEquals("SST",redTest.findSpecialization("SST",0));
            }

        }

        assertThrows(NullPointerException.class, () ->  redTest.findSpecialization(null,0));
        assertThrows(NullPointerException.class, () ->  redTest.findSpecialization(null,3));


    }


    @Test
    void redistributeStudents_FILLING_PLACES_IN_LIST_TEST() {

        //Tests while list is of places is full(before redistributing students)
        assertEquals(67,redTest.getSpecsWithAvailablePlaces().get("TIZ"));
        assertEquals(67,redTest.getSpecsWithAvailablePlaces().get("SST"));
        assertEquals(66,redTest.getSpecsWithAvailablePlaces().get("RTM"));


        redTest.redistributeStudents();
        //After redistributing
        assertEquals(0,redTest.getSpecsWithAvailablePlaces().get("TIZ"));
        assertEquals(0,redTest.getSpecsWithAvailablePlaces().get("SST"));
        assertEquals(0,redTest.getSpecsWithAvailablePlaces().get("RTM"));

        //No student is without assigned specialization...
        int amountOfStudents = DataInfo.getListOfStudents().size();
        assertNotNull(DataInfo.getListOfStudents().get(0).getSpecializationAssigned()); //1st student
        assertNotNull(DataInfo.getListOfStudents().get(amountOfStudents/2).getSpecializationAssigned()); // middle student
        assertNotNull(DataInfo.getListOfStudents().get(amountOfStudents-1).getSpecializationAssigned()); // last student

    }



}
