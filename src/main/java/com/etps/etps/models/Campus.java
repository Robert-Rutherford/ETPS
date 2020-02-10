package com.etps.etps.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "campuses")
public class Campus {

    @Id
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campus")
    private List<Program> programs;

    public Campus(){};

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
}
