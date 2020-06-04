package com.etps.etps.models;

//# table 5: additional contact info (provider)
//# values: additonal contact person, additional contact job title, additional contact phone,
//#           additional contact phone extension, additional contact email, provider id (key)

import javax.persistence.*;

@Entity
@Table(name = "providerAdditionalContact")
public class ProviderAdditionalContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private String addContactName;
    private String addContactJobTitle;

    private String addContactPhone;
    private String addContactPhoneExtension;
    private String addContactEmail;

    public ProviderAdditionalContact() {

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

    public String getAddContactName() {
        return addContactName;
    }

    public void setAddContactName(String addContactName) {
        this.addContactName = addContactName;
    }

    public String getAddContactJobTitle() {
        return addContactJobTitle;
    }

    public void setAddContactJobTitle(String addContactJobTitle) {
        this.addContactJobTitle = addContactJobTitle;
    }

    public String getAddContactPhone() {
        return addContactPhone;
    }

    public void setAddContactPhone(String addContactPhone) {
        this.addContactPhone = addContactPhone;
    }

    public String getAddContactPhoneExtension() {
        return addContactPhoneExtension;
    }

    public void setAddContactPhoneExtension(String addContactPhoneExtension) {
        this.addContactPhoneExtension = addContactPhoneExtension;
    }

    public String getAddContactEmail() {
        return addContactEmail;
    }

    public void setAddContactEmail(String addContactEmail) {
        this.addContactEmail = addContactEmail;
    }
}
