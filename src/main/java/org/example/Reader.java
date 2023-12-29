package org.example;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader
{


    private CSVReader reader;

    public Reader(String file) throws FileNotFoundException {
        reader = new CSVReader(new FileReader( file));
    }
    public CSVReader getReader() {
        return reader;
    }
}
