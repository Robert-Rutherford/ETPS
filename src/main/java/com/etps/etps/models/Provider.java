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

//    @Column(unique = true)
    private String providerName;

    @Column(length = 1000)
    private String description;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
//    private List<User> users;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
//    private List<Submission> submissions;
    @OneToOne
    private Submission submission;

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

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(User user) {
//        this.users.add(user);
//    }

    public long getProvId() {
        return provId;
    }

    public void setProvId(long provId) {
        this.provId = provId;
    }

//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

//    public List<Submission> getSubmissions() {
//        return submissions;
//    }
//
//    public void setSubmissions(List<Submission> submissions) {
//        this.submissions = submissions;
//    }


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
