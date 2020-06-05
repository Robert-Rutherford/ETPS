package com.etps.etps.models;
//# table 8: campus contact info
//# values: campus contact Name, campus contact job title, campus phone, campus phone extension, campus email,
//#           campus id (key)

import javax.persistence.*;

@Entity
@Table(name = "campusContact")
public class CampusContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private String name;
    private String jobTitle;

    private String phone;
    private String phoneExtension;
    private String email;

    public CampusContact() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
