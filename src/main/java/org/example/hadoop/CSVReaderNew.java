package org.example.hadoop;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReaderNew {

    // Method to read data from CSV file
    public List<PatientRecord> readCSV(String filePath) {
        List<PatientRecord> records = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            // Skip the header
            csvReader.readNext();

            String[] data;
            while ((data = csvReader.readNext()) != null) {
                PatientRecord patientRecord = new PatientRecord(data[0], data[1], data[2], data[3], data[4] + data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12]);
                records.add(patientRecord);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return records;
    }
}

