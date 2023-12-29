package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {


        String file = "C:\\Users\\Yassine\\IdeaProjects\\Bayes\\src\\main\\java\\org\\example\\DB.csv";
        Reader reader = new Reader(file);
        String data[];
        List<DataEntry> entries = new ArrayList<>();

        reader.getReader().readNext();


        while ((data = reader.getReader().readNext()) != null) {
         List<Integer> dataInt = new ArrayList<Integer>();
            for (int i = 0; i < data.length; i++) {
                 if(i == 5)
                 {
                     dataInt.add( OriginsEnum.valueOf(data[i].toUpperCase().trim()).ordinal());
                 }
                 else {
                     dataInt.add(Integer.parseInt(data[i]));
                 }
            }
            entries.add(new DataEntry(dataInt.get(0), dataInt.get(1), dataInt.get(2), dataInt.get(3), dataInt.get(4), OriginsEnum.values()[dataInt.get(5)]));

        }

        DataEntry entry = new DataEntry(28, 84, 66, 1850, 16);



        System.out.println(KNNOperations.predictEntry(entry, 1, entries ));

    }
}