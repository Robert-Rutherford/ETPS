package com.etps.etps.models;


//# table 3: admin contact info (provider)
//# values: Admin Contact Person, Admin Job Title,  Admin Phone, Admin Phone Extension,
//#           provider id (key)

import javax.persistence.*;

@Entity
@Table(name = "providerAdminContact")
public class ProviderAdminContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private String adminName;
    private String adminJobTitle;

    private String adminPhone;
    private String adminPhoneExtension;

    public ProviderAdminContact() {

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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminJobTitle() {
        return adminJobTitle;
    }

    public void setAdminJobTitle(String adminJobTitle) {
        this.adminJobTitle = adminJobTitle;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminPhoneExtension() {
        return adminPhoneExtension;
    }

    public void setAdminPhoneExtension(String adminPhoneExtension) {
        this.adminPhoneExtension = adminPhoneExtension;
    }
}
