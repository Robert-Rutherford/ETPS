package com.etps.etps.models;
//# table 11: program additional info
//# values: CSCPrgmID, ETPCodeID, CIPCode, PellEligble, PreRequisites, programURL, program outcome, Associate Credit Name,
//#           length contact hours, length weeks, program format, ONET1, ONET2, ONET3, student data,
//#           number of apprentices, program id (key)

import javax.persistence.*;

@Entity
@Table(name = "programAdditionalInfo")
public class ProgramAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Program program;

    private long cscProgramID;
    private long etpCodeID;
    private long cipCode;

    private String preRequisites;
    private String programURL;
    private String programOutcome;
    private String associateCreditName;

    private long lengthCreditHours;
    private long lengthWeeks;

    private String programFormat;

    private long onet1;
    private long onet2;
    private long onet3;

    private String studentData;
    private long numberOfApprentices;

    public ProgramAdditionalInfo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public long getCscProgramID() {
        return cscProgramID;
    }

    public void setCscProgramID(long cscProgramID) {
        this.cscProgramID = cscProgramID;
    }

    public long getEtpCodeID() {
        return etpCodeID;
    }

    public void setEtpCodeID(long etpCodeID) {
        this.etpCodeID = etpCodeID;
    }

    public long getCipCode() {
        return cipCode;
    }

    public void setCipCode(long cipCode) {
        this.cipCode = cipCode;
    }

    public String getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(String preRequisites) {
        this.preRequisites = preRequisites;
    }

    public String getProgramURL() {
        return programURL;
    }

    public void setProgramURL(String programURL) {
        this.programURL = programURL;
    }

    public String getProgramOutcome() {
        return programOutcome;
    }

    public void setProgramOutcome(String programOutcome) {
        this.programOutcome = programOutcome;
    }

    public String getAssociateCreditName() {
        return associateCreditName;
    }

    public void setAssociateCreditName(String associateCreditName) {
        this.associateCreditName = associateCreditName;
    }

    public long getLengthCreditHours() {
        return lengthCreditHours;
    }

    public void setLengthCreditHours(long lengthCreditHours) {
        this.lengthCreditHours = lengthCreditHours;
    }

    public long getLengthWeeks() {
        return lengthWeeks;
    }

    public void setLengthWeeks(long lengthWeeks) {
        this.lengthWeeks = lengthWeeks;
    }

    public String getProgramFormat() {
        return programFormat;
    }

    public void setProgramFormat(String programFormat) {
        this.programFormat = programFormat;
    }

    public long getOnet1() {
        return onet1;
    }

    public void setOnet1(long onet1) {
        this.onet1 = onet1;
    }

    public long getOnet2() {
        return onet2;
    }

    public void setOnet2(long onet2) {
        this.onet2 = onet2;
    }

    public long getOnet3() {
        return onet3;
    }

    public void setOnet3(long onet3) {
        this.onet3 = onet3;
    }

    public String getStudentData() {
        return studentData;
    }

    public void setStudentData(String studentData) {
        this.studentData = studentData;
    }

    public long getNumberOfApprentices() {
        return numberOfApprentices;
    }

    public void setNumberOfApprentices(long numberOfApprentices) {
        this.numberOfApprentices = numberOfApprentices;
    }
}
