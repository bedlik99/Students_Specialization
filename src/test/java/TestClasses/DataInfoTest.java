package TestClasses;

import Data.DataInfo;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


class DataInfoTest {


    @Test
    void setup_LoadStudentsData_INCORRECT_FILENAME_TEST() throws FileNotFoundException {

        // Wrong version of use (wrong fileName)
        String noSuchFileName = "noFileData.json";
       assertThrows(FileNotFoundException.class, () ->  DataInfo.setup_LoadStudentsData(noSuchFileName));


        // Correct version of use (correct fileName)
        String correctFileName = "studentsData.json";
            assertEquals(0,DataInfo.setup_LoadStudentsData(correctFileName));


    }

    @Test
    void setup_LoadStudentsData_NULL_PARAMETER_TEST() throws FileNotFoundException {

        assertEquals(-1,DataInfo.setup_LoadStudentsData(null));
    }


    @Test
    void setup_SaveStudentsToFiles_CORRECT_VERSION_TEST() {
        //If function returned 0 that means all students were written to specific files
        // No exception was thrown

        //Checked correction
       assertEquals(0,DataInfo.setup_SaveStudentsToFiles());
       assertEquals(Integer.parseInt("0"),DataInfo.setup_SaveStudentsToFiles());

       // Checked reversed correction
       assertNotEquals(-1,DataInfo.setup_SaveStudentsToFiles());
       assertNotEquals((Integer)null,DataInfo.setup_SaveStudentsToFiles());
       assertNotEquals("Totally incorrect",DataInfo.setup_SaveStudentsToFiles());

    }


}