package org.example;

import java.util.Objects;

import static java.lang.StringTemplate.STR;


public class PatientRecord {
    private String patientId;
    private String name;
    private String age;
    private String gender;
    private String dateOfAdmission;
    private String medicalRecord;
    private String medicalHistory;
    private String chiefComplaint;
    private String historyOfParentIllness;
    private String physicalExamination;
    private String assessmentAndPlan;
    private String nhsTrustRegion;
    private String region;

    // Constructor to initialize a patient record
    public PatientRecord(String patientId, String name, String age, String gender, String dateOfAdmission, String medicalRecord, String medicalHistory, String chiefComplaint, String historyOfParentIllness, String physicalExamination, String assessmentAndPlan, String nhsTrustRegion, String region) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dateOfAdmission = dateOfAdmission;
        this.medicalRecord = medicalRecord;
        this.medicalHistory = medicalHistory;
        this.chiefComplaint = chiefComplaint;
        this.historyOfParentIllness = historyOfParentIllness;
        this.physicalExamination = physicalExamination;
        this.assessmentAndPlan = assessmentAndPlan;
        this.nhsTrustRegion = nhsTrustRegion;
        this.region = region;

    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getHistoryOfParentIllness() {
        return historyOfParentIllness;
    }

    public void setHistoryOfParentIllness(String historyOfParentIllness) {
        this.historyOfParentIllness = historyOfParentIllness;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getAssessmentAndPlan() {
        return assessmentAndPlan;
    }

    public void setAssessmentAndPlan(String assessmentAndPlan) {
        this.assessmentAndPlan = assessmentAndPlan;
    }

    public String getNhsTrustRegion() {
        return nhsTrustRegion;
    }

    public void setNhsTrustRegion(String nhsTrustRegion) {
        this.nhsTrustRegion = nhsTrustRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // Override toString method for displaying the patient record
    @Override
    public String toString() {
        return STR."Patient ID: \{patientId}, Name: \{name}, Age: \{age}, Gender: \{gender}, Medical Record Number: \{medicalRecord}, Medical History: \{medicalHistory} ,Chief Complaint: \{chiefComplaint}, History_of_Present_Illness:\{historyOfParentIllness}, Physical Examination: \{physicalExamination}, Assesment and Plan: \{assessmentAndPlan}, NHS Trust Region: \{nhsTrustRegion} Region: \{region}";
    }

    // Override equals and hashCode for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientRecord that = (PatientRecord) o;
        return Objects.equals(patientId, that.patientId) && Objects.equals(chiefComplaint, that.chiefComplaint) && Objects.equals(region, that.region) && Objects.equals(physicalExamination, that.physicalExamination) && Objects.equals(assessmentAndPlan, that.assessmentAndPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, chiefComplaint, region, physicalExamination, assessmentAndPlan);
    }
}
