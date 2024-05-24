package org.example.hadoop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PatientRecordGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private CSVReaderNew csvReader;
    private List<PatientRecord> records;
    private JTextField searchField;
    private JComboBox<String> searchComboBox;

    public PatientRecordGUI() {
        csvReader = new CSVReaderNew();

        setTitle("Patient Records");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Patient ID", "Name", "Age", "Gender", "Date_of_Admission", "Medical_Record_Number", "Medical_History", "Chief_Complaint", "History_of_Present_Illness", "Physical Examination", "Assessment and Plan", "NHS Trust Region"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        String[] searchOptions = {"Patient ID", "Symptoms", "Region"};
        searchComboBox = new JComboBox<>(searchOptions);
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchComboBox);
        panel.add(searchButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String searchBy = (String) searchComboBox.getSelectedItem();
            String query = searchField.getText();
            if (!query.isEmpty()) {

                List<PatientRecord> filteredRecords = searchRecords(searchBy, query);
                updateTable(filteredRecords);
            }
        });

        loadData();
    }

    private void loadData() {
        records = csvReader.readCSV("/Users/demo/IdeaProjects/demo/src/resources/MedicalFiles.csv");
        updateTable(records);
    }

    private List<PatientRecord> searchRecords(String searchBy, String query) {
        List<PatientRecord> filteredRecords;

        switch (searchBy) {
            case "Patient ID":
                filteredRecords = records.stream().filter(record -> record.getPatientId().equalsIgnoreCase(query)).collect(Collectors.toList());
                break;
            case "Symptoms":
                filteredRecords = records.stream().filter(record -> Arrays.stream(record.getHistoryOfParentIllness().split(",")).anyMatch(complaint -> complaint.trim().equalsIgnoreCase(query))).collect(Collectors.toList());
                break;
            case "Region":
                filteredRecords = records.stream().filter(record -> record.getNhsTrustRegion().equalsIgnoreCase(query)).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid search type");
        }

        return filteredRecords;
    }

    private void updateTable(List<PatientRecord> records) {
        tableModel.setRowCount(0);
        for (PatientRecord record : records) {
            Object[] rowData = {record.getPatientId(), record.getName(), record.getAge(), record.getGender(), record.getDateOfAdmission(), record.getMedicalRecord(), record.getMedicalHistory(), record.getChiefComplaint(), record.getHistoryOfParentIllness(), record.getPhysicalExamination(), record.getAssessmentAndPlan(), record.getNhsTrustRegion()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientRecordGUI gui = new PatientRecordGUI();
            gui.setVisible(true);
        });
    }
}
