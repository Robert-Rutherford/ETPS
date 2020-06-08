package com.etps.etps.models;
//# need to move: etp_code_id to table 11

import javax.persistence.*;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = ("INT UNSIGNED"))
    private long progId;

    private String name;

    private String description;

    private String etpCodeId;


    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;


    public Program() {
    }


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

    public String getEtpCodeId() {
        return etpCodeId;
    }

    public void setEtpCodeId(String etpCodeId) {
        this.etpCodeId = etpCodeId;
    }


    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public long getProgId() {
        return progId;
    }

    public void setProgId(long progId) {
        this.progId = progId;
    }
}
