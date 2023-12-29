package org.example;

import java.util.*;

import static org.example.Operations.calcEuclideanDistance;
import static org.example.Operations.trimList;

public class KNNOperations {



    public static List<Couplet> createEuclidDistList(DataEntry dataEntry, List<DataEntry> dataEntries) {

        List<Couplet> euclDistList = new ArrayList<>();

        for (DataEntry entry : dataEntries) {

            euclDistList.add(new Couplet(entry.getOrign(), calcEuclideanDistance(entry,dataEntry)));

        }

        euclDistList.sort(Comparator.comparingDouble(Couplet::getEuclDist));

        return euclDistList;
    }




    public static OriginsEnum predictEntry(DataEntry dataEntry ,int k , List<DataEntry> dataEntries)
    {
        List<Couplet> trimmedList = trimList(createEuclidDistList(dataEntry, dataEntries), k);
        int japCount = 0;
        int amerCount = 0;
        int europCount = 0;
        for(Couplet couplet : trimmedList)
        {
            if(couplet.getOrigin() == OriginsEnum.JAPANESE)
            {
              japCount++;
            }
            else if (couplet.getOrigin() == OriginsEnum.AMERICAN)
            {
               amerCount++;
            }
            else if (couplet.getOrigin() == OriginsEnum.EUROPEAN)
            {
             europCount++;
            }
        }
        int max = Math.max(japCount, Math.max(amerCount, europCount));

        OriginsEnum result = max == japCount ? OriginsEnum.JAPANESE : max == amerCount ? OriginsEnum.AMERICAN : OriginsEnum.EUROPEAN;
        return result;
    }


}
