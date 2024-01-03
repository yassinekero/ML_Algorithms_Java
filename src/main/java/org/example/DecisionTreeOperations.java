package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Operations.calcAverage;
import static org.example.Operations.classifyAttribute;

public class DecisionTreeOperations {


    // Logarithm base 2 function
    private static double log2(double x) {
        if (x <= 0) {
            return 0; // Ou une valeur par défaut appropriée lorsque x est non positive
        }
        return Math.log(x) / Math.log(2);
    }

    private static double calculateEntropy(AttributesEnum attribute, List<DataEntry> dataEntries) {
        int countTrue = 0;
        int countFalse = 0;
        int countTrueJapanese = 0;
        int countFalseJapanese = 0;
        int countTrueEuropean = 0;
        int countFalseEuropean = 0;
        int countTrueAmerican = 0;
        int countFalseAmerican = 0;

        boolean dt; // Variable pour stocker la valeur retournée par classifyAttribute pour une entrée

        for (DataEntry entry : dataEntries) {
            dt = classifyAttribute(entry.enumToAttribute(attribute), calcAverage(attribute, dataEntries));

            if (dt) { // Si dt est vrai
                countTrue++;
                if (entry.getOrign() == OriginsEnum.JAPANESE) {
                    countTrueJapanese++;
                } else if (entry.getOrign() == OriginsEnum.EUROPEAN) {
                    countTrueEuropean++;
                } else if (entry.getOrign() == OriginsEnum.AMERICAN) {
                    countTrueAmerican++;
                }
            } else { // Si dt est faux
                countFalse++;
                if (entry.getOrign() == OriginsEnum.JAPANESE) {
                    countFalseJapanese++;
                } else if (entry.getOrign() == OriginsEnum.EUROPEAN) {
                    countFalseEuropean++;
                } else if (entry.getOrign() == OriginsEnum.AMERICAN) {
                    countFalseAmerican++;
                }
            }

        }

        // Calcul de l'entropie
        double probTrue = (double) countTrue / dataEntries.size();
        double probFalse = (double) countFalse / dataEntries.size();

        double iTrue = -((countTrueJapanese / (double) countTrue) * log2(countTrueJapanese / (double) countTrue)) - ((countTrueEuropean / (double) countTrue) * log2(countTrueEuropean / (double) countTrue)) - ((countTrueAmerican / (double) countTrue) * log2(countTrueAmerican / (double) countTrue));

        double iFalse = -((countFalseJapanese / (double) countFalse) * log2(countFalseJapanese / (double) countFalse)) - ((countFalseEuropean / (double) countFalse) * log2(countFalseEuropean / (double) countFalse)) - ((countFalseAmerican / (double) countFalse) * log2(countFalseAmerican / (double) countFalse));
        double H = (probTrue * iTrue) + (probFalse * iFalse);
        return H;
    }

    
    public static AttributesEnum racine(List<DataEntry> dataEntries) {
        double mpgEntropy = calculateEntropy(AttributesEnum.MPG, dataEntries);
        double displacementEntropy = calculateEntropy(AttributesEnum.DISPLACEMENT, dataEntries);
        double horsepowerEntropy = calculateEntropy(AttributesEnum.HORSEPOWER, dataEntries);
        double weightEntropy = calculateEntropy(AttributesEnum.WEIGHT, dataEntries);
        double accelerationEntropy = calculateEntropy(AttributesEnum.ACCELERATION, dataEntries);

        double min = Math.min(Math.min(Math.min(mpgEntropy, displacementEntropy), Math.min(horsepowerEntropy, weightEntropy)), accelerationEntropy);

        AttributesEnum racine = min == mpgEntropy ? AttributesEnum.MPG : min == displacementEntropy ? AttributesEnum.DISPLACEMENT : min == horsepowerEntropy ? AttributesEnum.HORSEPOWER : min == weightEntropy ? AttributesEnum.WEIGHT : AttributesEnum.ACCELERATION;

        return racine;

    }

    public static List<DataEntry> leaf(List<DataEntry> dataEntries, AttributesEnum attribute, boolean classification) {
        float avg = calcAverage(attribute, dataEntries);
        List<DataEntry> leafList = new ArrayList<>();
        if (classification) {
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.enumToAttribute(attribute) >= avg) {
                    leafList.add(dataEntry);
                }
            }
        } else {
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.enumToAttribute(attribute) < avg) {
                    leafList.add(dataEntry);
                }
            }
        }

        return leafList;
    }

    public static OriginsEnum originHomogene(List<DataEntry> dataEntries) {
        OriginsEnum origin = dataEntries.get(0).getOrign();
        for (int i = 1; i < dataEntries.size(); i++) {
            if (origin != dataEntries.get(i).getOrign()) {
                return null;
            }
        }
        return origin;
    }

    public static OriginsEnum predictEntry(DataEntry dataEntry, List<DataEntry> dataEntries) {
        AttributesEnum racine;
        OriginsEnum origin = null;
        do {
            racine = racine(dataEntries);
            dataEntries = leaf(dataEntries, racine, classifyAttribute(dataEntry.enumToAttribute(racine), calcAverage(racine, dataEntries)));
            origin = originHomogene(dataEntries);
        } while (origin == null);
        return origin;
    }
}