package org.example;


import java.util.List;

public class Operations {

    public static int calcSumOrigin(OriginsEnum origin, List<DataEntry> dataEntries) {
        int sum = 0;
        for (DataEntry entry : dataEntries) {
            if (entry.getOrign() == origin) {
                sum++;
            }
        }
        return sum;
    }


    public static float calcAverage(AttributesEnum attribute, List<DataEntry> dataEntries) {
        float average = 0;
        for (DataEntry entry : dataEntries) {
            average += entry.enumToAttribute(attribute);
        }
        average = average / dataEntries.size();
        return average;
    }
    public static boolean classifyAttribute(float value, float avg) {
        if (value >= avg) {
            return true;
        } else {
            return false;
        }
    }


    private   static float calcCartesianCoord(DataEntry dataEntryInList, DataEntry dataEntry, AttributesEnum attribute )

    {
        float attributeEntry = dataEntry.enumToAttribute(attribute);
        return (float) Math.pow(dataEntryInList.enumToAttribute(attribute) - attributeEntry, 2);

    }


    public  static float calcEuclideanDistance(DataEntry dataEntryInList, DataEntry dataEntry)
    {
        return (float) Math.sqrt(calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.MPG) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.DISPLACEMENT) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.HORSEPOWER) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.WEIGHT) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.ACCELERATION));
    }


    public static List<Couplet> trimList(List<Couplet> list, int rank) {
        if (rank >= 0 && rank <= list.size()) {
            return list.subList(0, rank);
        } else {
            throw new IllegalArgumentException("Range Ivalide");
        }
    }



}
