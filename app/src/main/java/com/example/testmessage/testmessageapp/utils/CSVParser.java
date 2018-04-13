package com.example.testmessage.testmessageapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    InputStream inputStream;

    public CSVParser(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<String[]> read()throws IOException {
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");

                if(row.length>0)
                resultList.add(row);
            }
        inputStream.close();
        return resultList;
    }
}
