package com.etps.etps.models;

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


}
