package com.etps.etps.models;

//# need to add: provider url, address 1, addres 2, institution type, WDA

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = ("INT UNSIGNED"))
    private long provId;


    private String providerName;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String providerURL;

    private String institutionType;
    private long wda;

    @OneToOne
    private Submission submission;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Campus> campuses;

    public Provider() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProvId() {
        return provId;
    }

    public void setProvId(long provId) {
        this.provId = provId;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public List<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(List<Campus> campuses) {
        this.campuses = campuses;
    }

    public String getProviderURL() {
        return providerURL;
    }

    public void setProviderURL(String providerURL) {
        this.providerURL = providerURL;
    }


    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public long getWda() {
        return wda;
    }

    public void setWda(long wda) {
        this.wda = wda;
    }
}
