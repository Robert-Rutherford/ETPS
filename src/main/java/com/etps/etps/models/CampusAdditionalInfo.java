package com.etps.etps.models;
//# table 10: campus additional info
//# values: info public transit, on sit child care, flexible hours,

import javax.persistence.*;

@Entity
@Table(name = "campusAdditionalInfo")
public class CampusAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Campus campus;

    private String publicTransit;
    private String onSiteChildCare;
    private String flexibleHours;

    public CampusAdditionalInfo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getPublicTransit() {
        return publicTransit;
    }

    public void setPublicTransit(String publicTransit) {
        this.publicTransit = publicTransit;
    }

    public String getOnSiteChildCare() {
        return onSiteChildCare;
    }

    public void setOnSiteChildCare(String onSiteChildCare) {
        this.onSiteChildCare = onSiteChildCare;
    }

    public String getFlexibleHours() {
        return flexibleHours;
    }

    public void setFlexibleHours(String flexibleHours) {
        this.flexibleHours = flexibleHours;
    }
}
