package com.etps.etps.models;

//# table 4: financial contact info (provider)
//# values: Financial Aid Name, Financial aid phone, Financial Aid Email, provider id (key)

import javax.persistence.*;

@Entity
@Table(name = "providerFinancialContact")
public class ProviderFinancialContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private String finAidName;
    private String finAidJobTitle;

    private String finAidPhone;
    private String finAidPhoneExtension;
    private String finAidEmail;

    public ProviderFinancialContact() {

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

    public String getFinAidName() {
        return finAidName;
    }

    public void setFinAidName(String finAidName) {
        this.finAidName = finAidName;
    }

    public String getFinAidJobTitle() {
        return finAidJobTitle;
    }

    public void setFinAidJobTitle(String finAidJobTitle) {
        this.finAidJobTitle = finAidJobTitle;
    }

    public String getFinAidPhone() {
        return finAidPhone;
    }

    public void setFinAidPhone(String finAidPhone) {
        this.finAidPhone = finAidPhone;
    }

    public String getFinAidPhoneExtension() {
        return finAidPhoneExtension;
    }

    public void setFinAidPhoneExtension(String finAidPhoneExtension) {
        this.finAidPhoneExtension = finAidPhoneExtension;
    }

    public String getFinAidEmail() {
        return finAidEmail;
    }

    public void setFinAidEmail(String finAidEmail) {
        this.finAidEmail = finAidEmail;
    }
}
