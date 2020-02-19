package com.etps.etps.models;


import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    @Column(columnDefinition = ("INT UNSIGNED"))
    private long userProviderId;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "provId")
//    private Provider provider;

    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentUser")
    private List<Message> sent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receivedUser")
    private List<Message> received;

    public User(){};

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
//        provider = copy.provider;
        isAdmin = copy.isAdmin;
        sent = copy.sent;
        received = copy.received;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Provider getProvider() {
//        return provider;
//    }
//
//    public void setProvider(Provider provider) {
//        this.provider = provider;
//    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Message> getSent() {
        Collections.reverse(sent);
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }

    public List<Message> getReceived() {
        Collections.reverse(received);
        return this.received;

    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public long getUserProviderId() {
        return userProviderId;
    }

    public void setUserProviderId(long userProviderId) {
        this.userProviderId = userProviderId;
    }
}
