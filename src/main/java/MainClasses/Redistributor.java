package MainClasses;

import Data.DataInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Redistributor {


    /**
     * Map that holds name of specialization and number of places that students can occupy
     */
    private static Map<String, Integer> specsWithPlaces = new HashMap<>(DataInfo.getSpecChosenTimes());


    /**
     * Method that allows me to get map Key by knowing its value.
     * @param map A Map
     * @param value A value that is linked with key
     * @return Key linked with given value as paremeter
     */
    public static String getKey(Map<String, Integer> map, Integer value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Method that fills new Map that contains, a specialization name and places available for students to occupy.
     */
    public static void fillSpecsPlaces() {

        Map<String, Integer> tempMap = new HashMap<>(DataInfo.getSpecChosenTimes());
        int[] highestValues = new int[DataInfo.getSpecNames().size() - 1]; // we need highest values

        for (int i = 0; i < DataInfo.getSpecNames().size() - 1; i++) {
            Map.Entry<String, Integer> maxEntry =
                    Collections.max(tempMap.entrySet(), Map.Entry.comparingByValue());

            highestValues[i] = maxEntry.getValue();
            tempMap.remove(getKey(tempMap, maxEntry.getValue()));

        }


        for (Map.Entry<String, Integer> entry : specsWithPlaces.entrySet()) {
            entry.setValue(DataInfo.getListOfStudents().size() / DataInfo.getSpecNames().size());

            for (int j = 0; j < highestValues.length; j++) {
                if (entry.getKey().equals(getKey(DataInfo.getSpecChosenTimes(), highestValues[j]))) {
                    entry.setValue(entry.getValue() + 1);
                }

            }

            System.out.println(entry.getKey() + " : " + entry.getValue() + " available places FOR students");
        }


    }


    //Ok. Znam liczbe miejsc przyjmujaca studentow w kazdej specjalizacji.
    // Nalezy ich teraz rozdzielic. W dany sposob:
    //1. Zapisywanie studentow do specjalizacji az skonczy sie w niej miejsce,
    // przy czym studenci sa szeregowani od max sredniej do min sredniej i brane sa ich priorytety specek.
    //2. Jezeli student skonczylo sie miejsce w ktorejs ze specek, a student na druga opcje wzial te sama specke,
    // to sprawdzamy jego trzeci wybor, jezeli wybral inna to go tam wysylamy, jezeli jest miejsce, jezeli nie ma miejsce
    // lub jezeli wzial po raz 3 te sama specke to nalezy go wrzucic do kolejki oczekujacych, az wszyscy inni wybora swoje specki
    
}
