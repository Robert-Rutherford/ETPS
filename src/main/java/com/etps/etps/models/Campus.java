package com.etps.etps.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "campuses")
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = ("INT UNSIGNED"))
    private long campId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campus")
    private List<Program> programs;


    public Campus() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCampusName() {
        return name;
    }

    public void setCampusName(String campusName) {
        this.name = campusName;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

}
