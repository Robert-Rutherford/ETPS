package com.etps.etps.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    private String status;

    private Date deadline;

//    @ManyToOne
//    @OneToOne
//    @JoinColumn(name = "provider_id")
//    private Provider provider;

//    @ManyToOne
//    @JoinColumn(name = "campus_id")
//    private Campus campus;

//    @ManyToOne
//    @JoinColumn(name = "program_id")
//    private Program program;


    public Submission() {
    }

    ;

//    public Provider getProvider() {
//        return provider;
//    }
//
//    public void setProvider(Provider provider) {
//        this.provider = provider;
//    }

//    public Campus getCampus() {
//        return campus;
//    }
//
//    public void setCampus(Campus campus) {
//        this.campus = campus;
//    }
//
//    public Program getProgram() {
//        return program;
//    }
//
//    public void setProgram(Program program) {
//        this.program = program;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


}
