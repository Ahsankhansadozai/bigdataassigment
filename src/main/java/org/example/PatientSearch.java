package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class PatientSearch {
    private List<PatientRecord> records;

    public PatientSearch(List<PatientRecord> records) {
        this.records = records;
    }

    public List<PatientRecord> searchById(String id) {
        return records.stream()
                .filter(record -> record.getPatientId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    public List<PatientRecord> searchByChiefComplaint(String chiefComplaint) {
        return records.stream()
                .filter(record -> record.getChiefComplaint().equalsIgnoreCase(chiefComplaint))
                .collect(Collectors.toList());
    }

    public List<PatientRecord> searchByRegion(String region) {
        return records.stream()
                .filter(record -> record.getRegion().equalsIgnoreCase(region))
                .collect(Collectors.toList());
    }
}
