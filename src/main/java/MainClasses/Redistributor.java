package MainClasses;

import Data.DataInfo;
import Data.Models.Specialization;
import Data.Models.Student;

import java.util.*;

public class Redistributor {

    /**
     * Map that holds name of specialization and number of places that students can occupy
     */
    private final Map<String, Integer> specsWithPlaces;

    /**
     * Map that show how many places are available in specific specializations
     */
    private Map<String, Integer> specsWithAvailablePlaces;


    public Redistributor(){
        specsWithPlaces = new HashMap<>(DataInfo.getSpecChosenTimes());
        fillSpecsPlaces();
    }

    /**
     * Method that allows me to get map Key by knowing its value.
     * @param map   A Map
     * @param value A value that is linked with key
     * @return Key linked with given value as paremeter
     */
    public String getKey(Map<String, Integer> map, Integer value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    /**
     * Method filling a Map that contains specialization name and places available for students to occupy.
     */
    public void fillSpecsPlaces() {

        Map<String, Integer> tempMap = new HashMap<>(DataInfo.getSpecChosenTimes());

        int[] highestValues = new int[DataInfo.getCurrentSpecs().size() - 1]; // we need highest values

        for (int i = 0; i < DataInfo.getCurrentSpecs().size() - 1; i++) {
            Map.Entry<String, Integer> maxEntry = Collections.max(tempMap.entrySet(), Map.Entry.comparingByValue());

            highestValues[i] = maxEntry.getValue();
            tempMap.remove(getKey(tempMap, maxEntry.getValue()));

        }

        for (Map.Entry<String, Integer> entry : specsWithPlaces.entrySet()) {
            entry.setValue(DataInfo.getListOfStudents().size() / DataInfo.getCurrentSpecs().size());

            for (int j = 0; j < highestValues.length; j++) {
                if (entry.getKey().equals(getKey(DataInfo.getSpecChosenTimes(), highestValues[j]))) {
                    entry.setValue(entry.getValue() + 1);
                }

            }

           // System.out.println(entry.getKey() + " : " + entry.getValue() + " available places FOR students"); --> places for students
        }

    }


    /**
     * Method that iterates through all available specialization, and finds the same as given parameter
     * @param specName Name of specialization picked by student
     * @param nr nr of spec picked next-in-priority-order
     * @return name of specialization
     */
    private String findSpecialization(String specName, int nr) {

        for (int i = 0; i < DataInfo.getCurrentSpecs().size(); i++) {

            if (specName.equals(DataInfo.getCurrentSpecs().get(i).getSpecializationName())) {
                if (specsWithAvailablePlaces.get(specName) != 0) {
                    return specName;
                }else if(nr == DataInfo.getCurrentSpecs().size()-1){
                    return "NO_AVAILABLE_PLACES";
                }
            }

        }

       return null;
    }

    /**
     * Students are sorted from max average grade to minimum average grade.
     */
    public void redistributeStudents() {
        specsWithAvailablePlaces = new LinkedHashMap<>(specsWithPlaces);


        for (Student student : DataInfo.getListOfStudents()) {

            String[] studSpec = new String[DataInfo.getCurrentSpecs().size()];

            for(int u = 0; u < DataInfo.getCurrentSpecs().size(); u++){
                studSpec[u] = student.getSpecsChosenOrder().get(u);
            }



            for (int s = 0; s < DataInfo.getCurrentSpecs().size(); s++) {

                if (findSpecialization(studSpec[s],s) != null) {

                    if (findSpecialization(studSpec[s],s).equals("NO_AVAILABLE_PLACES")) {
                        System.out.println("NO_AVAILABLE_PLACES");

                    } else {
                        student.setSpecializationAssigned(studSpec[s]);

                        for(Specialization specialization: DataInfo.getCurrentSpecs()){
                            if(specialization.getSpecializationName().equals(student.getSpecializationAssigned())){
                                specialization.getNewStudentsList().add(student);
                                break;
                            }

                        }

                        specsWithAvailablePlaces.put(studSpec[s], (specsWithAvailablePlaces.get(studSpec[s]) - 1));

/*                        System.out.println("Available places in specs:" +
                                "\n1. " + DataInfo.getCurrentSpecs().get(0).getSpecializationName() + " : " +
                                specsWithAvailablePlaces.get(DataInfo.getCurrentSpecs().get(0).getSpecializationName()) +
                                "\n2. " + DataInfo.getCurrentSpecs().get(1).getSpecializationName() + " : " +
                                specsWithAvailablePlaces.get(DataInfo.getCurrentSpecs().get(1).getSpecializationName()) +
                                "\n3. " + DataInfo.getCurrentSpecs().get(2).getSpecializationName() + " : " +
                                specsWithAvailablePlaces.get(DataInfo.getCurrentSpecs().get(2).getSpecializationName()));*/

                        break;
                    }


                }

            }


        }


    }


}
