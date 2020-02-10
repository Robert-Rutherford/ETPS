package com.etps.etps.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    private String providerName;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Submission> submissions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Campus> campuses;

    public Provider(){};

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
