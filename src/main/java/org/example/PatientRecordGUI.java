package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientRecordGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private CSVReaderNew csvReader;
    private PatientRecordClassifier classifier;
    private List<PatientRecord> records;
    private JTextField searchField;
    private JComboBox<String> searchComboBox;

    public PatientRecordGUI() {
        csvReader = new CSVReaderNew();
        classifier = new PatientRecordClassifier();

        setTitle("Patient Records");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the table model with column names
        String[] columnNames = {"Patient ID", "Name", "Age", "Gender", "Medical_Record_Number", "Medical_History", "Chief_Complaint", "History_of_Present_Illness" , "Physical Examination", "Assessment and Plan", "NHS Trust Region", "Region"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Set up buttons and combo box for classification options
        String[] classifyOptions = {"Patient ID", "Complaints", "Region"};
        JComboBox<String> classifyComboBox = new JComboBox<>(classifyOptions);
        JButton classifyButton = new JButton("Classify");

        // Set up search components
        searchField = new JTextField(15);
        searchComboBox = new JComboBox<>(classifyOptions);
        JButton searchButton = new JButton("Search");

        // Set up panel for buttons and combo box
        JPanel panel = new JPanel();
        panel.add(classifyComboBox);
        panel.add(classifyButton);
        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchComboBox);
        panel.add(searchButton);

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Action listener for Classify button
        classifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classifyBy = (String) classifyComboBox.getSelectedItem();
                classifyData(classifyBy);
            }
        });

        // Action listener for Search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchBy = (String) searchComboBox.getSelectedItem();
                String query = searchField.getText();
                searchRecords(searchBy, query);
            }
        });

        // Automatically load data when the application starts
        loadData();
    }

    // Method to load data from CSV and populate the table
    private void loadData() {
        records = csvReader.readCSV("/Users/demo/IdeaProjects/demo/src/resources/MedicalFiles.csv");
        updateTable(records);
    }

    // Method to classify data and update the table
    private void classifyData(String classifyBy) {
        Map<String, List<PatientRecord>> classifiedRecords;

        switch (classifyBy) {
            case "Patient ID":
                classifiedRecords = classifier.classifyByPatientId(records);
                break;
            case "Complaints":
                classifiedRecords = classifier.classifyByComplaints(records);
                break;
            case "Region":
                classifiedRecords = classifier.classifyByRegion(records);
                break;
            default:
                throw new IllegalArgumentException("Invalid classification type");
        }

        // Flatten classified records for displaying in table
        List<PatientRecord> flattenedRecords = classifiedRecords.values().stream().flatMap(List::stream).collect(Collectors.toList());

        updateTable(flattenedRecords);
    }

    // Method to search records and update the table
    private void searchRecords(String searchBy, String query) {
        List<PatientRecord> filteredRecords;

        switch (searchBy) {
            case "Patient ID":
                filteredRecords = records.stream().filter(record -> record.getPatientId().equalsIgnoreCase(query)).collect(Collectors.toList());
                break;
            case "Complaints":
                filteredRecords = records.stream().filter(record -> record.getChiefComplaint().equalsIgnoreCase(query)).collect(Collectors.toList());
                break;
            case "Region":
                filteredRecords = records.stream().filter(record -> record.getRegion().equalsIgnoreCase(query)).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid search type");
        }

        updateTable(filteredRecords);
    }

    // Method to update the table with a list of records
    private void updateTable(List<PatientRecord> records) {
        tableModel.setRowCount(0); // Clear existing data
        for (PatientRecord record : records) {
            Object[] rowData = {record.getPatientId(), record.getName(), record.getAge(), record.getGender(), record.getMedicalRecord(), record.getMedicalHistory(), record.getChiefComplaint(), record.getHistoryOfParentIllness(), record.getPhysicalExamination(), record.getAssessmentAndPlan(), record.getNhsTrustRegion(), record.getRegion()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PatientRecordGUI().setVisible(true);
            }
        });
    }
}

