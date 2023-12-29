package org.example;

import java.util.List;

import static org.example.Operations.*;

public class BayesOperations {


    public static float calcClassProb(OriginsEnum origin, List<DataEntry> dataEntries) {

        return calcSumOrigin(origin, dataEntries) / (float) dataEntries.size();
    }


    public static float calcProbCondit(AttributesEnum attribute, OriginsEnum origin, boolean classification, List<DataEntry> dataEntries) {
        float avg = calcAverage(attribute, dataEntries);


        if (classification) {
            int occurences = 0;
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.getOrign() == origin && classifyAttribute(dataEntry.enumToAttribute(attribute), avg)) {
                    occurences++;
                }
            }
            return occurences / (float) calcSumOrigin(origin, dataEntries);
        } else {
            int occurences = 0;
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.getOrign() == origin && dataEntry.enumToAttribute(attribute) < avg) {
                    occurences++;
                }
            }
            return occurences / (float) calcSumOrigin(origin, dataEntries);
        }


    }


    public static float calcOrginProbabilityOfEntry(OriginsEnum origin, DataEntry dataEntry, List<DataEntry> dataEntries) {
        float pMpj = calcProbCondit(AttributesEnum.MPG, origin, classifyAttribute(dataEntry.getMpg(), calcAverage(AttributesEnum.MPG, dataEntries)), dataEntries);
        float pDisplacement = calcProbCondit(AttributesEnum.DISPLACEMENT, origin, classifyAttribute(dataEntry.getDisplacement(), calcAverage(AttributesEnum.DISPLACEMENT, dataEntries)), dataEntries);
        float pHorsepower = calcProbCondit(AttributesEnum.HORSEPOWER, origin, classifyAttribute(dataEntry.getHorsepower(), calcAverage(AttributesEnum.HORSEPOWER, dataEntries)), dataEntries);
        float pWeight = calcProbCondit(AttributesEnum.WEIGHT, origin, classifyAttribute(dataEntry.getWeight(), calcAverage(AttributesEnum.WEIGHT, dataEntries)), dataEntries);
        float pAcceleration = calcProbCondit(AttributesEnum.ACCELERATION, origin, classifyAttribute(dataEntry.getAcceleration(), calcAverage(AttributesEnum.ACCELERATION, dataEntries)), dataEntries);

        return pMpj * pDisplacement * pHorsepower * pWeight * pAcceleration;
    }


    public static OriginsEnum predictEntry(DataEntry dataEntry, List<DataEntry> dataEntries) {


        float pJapanese = calcOrginProbabilityOfEntry(OriginsEnum.JAPANESE, dataEntry, dataEntries) * calcClassProb(OriginsEnum.JAPANESE, dataEntries);
        float pAmerican = calcOrginProbabilityOfEntry(OriginsEnum.AMERICAN, dataEntry, dataEntries) * calcClassProb(OriginsEnum.AMERICAN, dataEntries);
        float pEuropean = calcOrginProbabilityOfEntry(OriginsEnum.EUROPEAN, dataEntry, dataEntries) * calcClassProb(OriginsEnum.EUROPEAN, dataEntries);
        float biggestProbability = Math.max(pJapanese, Math.max(pAmerican, pEuropean));
        OriginsEnum finalPrediction = (biggestProbability == pJapanese) ? OriginsEnum.JAPANESE : (biggestProbability == pAmerican) ? OriginsEnum.AMERICAN : OriginsEnum.EUROPEAN;
        return finalPrediction;
    }
}
