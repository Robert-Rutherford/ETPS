package com.etps.etps.models;
//# table 6: ids and acks
//# values: THECBPrgmID, TEAPrgmID, otherPrgmID, FERPAAck, WIOAAck, OtherAck1, OtherAck2, OtherAck3, OtherAck4,
//#           provider id (key)

import javax.persistence.*;

@Entity
@Table(name = "additionalIDsAndAcks")
public class AdditionalIDsAndAcks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private long THECBPrgmID;
    private long TEAPrgmID;
    private long otherPrgmID;
    private String fERPAAck;
    private String wIOAAck;
    private String otherAck1;
    private String otherAck2;
    private String otherAck3;
    private String otherAck4;

    public AdditionalIDsAndAcks() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public long getTHECBPrgmID() {
        return THECBPrgmID;
    }

    public void setTHECBPrgmID(long THECBPrgmID) {
        this.THECBPrgmID = THECBPrgmID;
    }

    public long getTEAPrgmID() {
        return TEAPrgmID;
    }

    public void setTEAPrgmID(long TEAPrgmID) {
        this.TEAPrgmID = TEAPrgmID;
    }

    public long getOtherPrgmID() {
        return otherPrgmID;
    }

    public void setOtherPrgmID(long otherPrgmID) {
        this.otherPrgmID = otherPrgmID;
    }

    public String getfERPAAck() {
        return fERPAAck;
    }

    public void setfERPAAck(String fERPAAck) {
        this.fERPAAck = fERPAAck;
    }

    public String getwIOAAck() {
        return wIOAAck;
    }

    public void setwIOAAck(String wIOAAck) {
        this.wIOAAck = wIOAAck;
    }

    public String getOtherAck1() {
        return otherAck1;
    }

    public void setOtherAck1(String otherAck1) {
        this.otherAck1 = otherAck1;
    }

    public String getOtherAck2() {
        return otherAck2;
    }

    public void setOtherAck2(String otherAck2) {
        this.otherAck2 = otherAck2;
    }

    public String getOtherAck3() {
        return otherAck3;
    }

    public void setOtherAck3(String otherAck3) {
        this.otherAck3 = otherAck3;
    }

    public String getOtherAck4() {
        return otherAck4;
    }

    public void setOtherAck4(String otherAck4) {
        this.otherAck4 = otherAck4;
    }
}
