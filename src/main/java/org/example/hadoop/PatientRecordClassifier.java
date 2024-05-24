package org.example.hadoop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRecordClassifier {
    // Method to classify records by patient ID
    public Map<String, List<PatientRecord>> classifyByPatientId(List<PatientRecord> records) {
        return classify(records, PatientRecord::getPatientId);
    }

    // Method to classify records by symptom
    public Map<String, List<PatientRecord>> classifyByComplaints(List<PatientRecord> records) {
        return classify(records, PatientRecord::getHistoryOfParentIllness);
    }

    // Method to classify records by region
    public Map<String, List<PatientRecord>> classifyByRegion(List<PatientRecord> records) {
        return classify(records, PatientRecord::getNhsTrustRegion);
    }

    // Generic method to classify records based on a key extractor
    private Map<String, List<PatientRecord>> classify(List<PatientRecord> records, java.util.function.Function<PatientRecord, String> keyExtractor) {
        Map<String, List<PatientRecord>> classifiedRecords = new HashMap<>();
        for (PatientRecord record : records) {
            classifiedRecords.computeIfAbsent(keyExtractor.apply(record), k -> new java.util.ArrayList<>()).add(record);
        }
        return classifiedRecords;
    }
}
