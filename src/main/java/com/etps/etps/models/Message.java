package com.etps.etps.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    private Date date_sent;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000, nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "message_to_id")
    private User receivedUser;

    @ManyToOne
    @JoinColumn(name = "message_from_id")
    private User sentUser;

    private boolean beenRead;

    public Message(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(Date date_sent) {
        this.date_sent = date_sent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getReceivedUser() {
        return receivedUser;
    }

    public void setReceivedUser(User receivedUser) {
        this.receivedUser = receivedUser;
    }

    public User getSentUser() {
        return sentUser;
    }

    public void setSentUser(User sentUser) {
        this.sentUser = sentUser;
    }

    public boolean isBeenRead() {
        return beenRead;
    }

    public void setBeenRead(boolean beenRead) {
        this.beenRead = beenRead;
    }
}
