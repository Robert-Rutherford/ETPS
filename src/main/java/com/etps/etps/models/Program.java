package com.etps.etps.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    private String name;

    private String description;

    private long etpCodeId;

    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private List<Submission> submissions;

    public Program(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEtpCodeId() {
        return etpCodeId;
    }

    public void setEtpCodeId(long etpCodeId) {
        this.etpCodeId = etpCodeId;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
