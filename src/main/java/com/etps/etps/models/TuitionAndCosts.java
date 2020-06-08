package com.etps.etps.models;
//# table 12: Tuition and costs
//# values: cost required Tuition fee description, costReq booksSupplies, book supplies description, CostOptOther,
//#           costOptOtherDescription, ETP Report type, Out of District Cost Req tuition fee,
//#           Out of District Cost Req tuition fee description, program id (key)

import javax.persistence.*;

@Entity
@Table(name = "tuitionAndCosts")
public class TuitionAndCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Program program;

    private long requiredTuitionFee;
    private String requiredTuitionFeeDisc;

    private long costReqBookSupplies;
    private String bookSuppliesDesc;

    private long costOptOther;
    private String costOptOtherDesc;

    private String etpReportType;

    private long outOfDistrictCostReqTuitionFee;
    private String outOfDistrictCostReqTuitionFeeDisc;

    public TuitionAndCosts() {
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

    public long getRequiredTuitionFee() {
        return requiredTuitionFee;
    }

    public void setRequiredTuitionFee(long requiredTuitionFee) {
        this.requiredTuitionFee = requiredTuitionFee;
    }

    public String getRequiredTuitionFeeDisc() {
        return requiredTuitionFeeDisc;
    }

    public void setRequiredTuitionFeeDisc(String requiredTuitionFeeDisc) {
        this.requiredTuitionFeeDisc = requiredTuitionFeeDisc;
    }

    public long getCostReqBookSupplies() {
        return costReqBookSupplies;
    }

    public void setCostReqBookSupplies(long costReqBookSupplies) {
        this.costReqBookSupplies = costReqBookSupplies;
    }

    public String getBookSuppliesDesc() {
        return bookSuppliesDesc;
    }

    public void setBookSuppliesDesc(String bookSuppliesDesc) {
        this.bookSuppliesDesc = bookSuppliesDesc;
    }

    public long getCostOptOther() {
        return costOptOther;
    }

    public void setCostOptOther(long costOptOther) {
        this.costOptOther = costOptOther;
    }

    public String getCostOptOtherDesc() {
        return costOptOtherDesc;
    }

    public void setCostOptOtherDesc(String costOptOtherDesc) {
        this.costOptOtherDesc = costOptOtherDesc;
    }

    public String getEtpReportType() {
        return etpReportType;
    }

    public void setEtpReportType(String etpReportType) {
        this.etpReportType = etpReportType;
    }

    public long getOutOfDistrictCostReqTuitionFee() {
        return outOfDistrictCostReqTuitionFee;
    }

    public void setOutOfDistrictCostReqTuitionFee(long outOfDistrictCostReqTuitionFee) {
        this.outOfDistrictCostReqTuitionFee = outOfDistrictCostReqTuitionFee;
    }

    public String getOutOfDistrictCostReqTuitionFeeDisc() {
        return outOfDistrictCostReqTuitionFeeDisc;
    }

    public void setOutOfDistrictCostReqTuitionFeeDisc(String outOfDistrictCostReqTuitionFeeDisc) {
        this.outOfDistrictCostReqTuitionFeeDisc = outOfDistrictCostReqTuitionFeeDisc;
    }
}
