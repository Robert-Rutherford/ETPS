package com.etps.etps.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = ("INT UNSIGNED"))
    private long id;

    @Column(nullable = false)
    private Date dateSent;

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

    private boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {

        this.deleted = deleted;
    }

    public Message(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
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

    public void setReceivedUser() {
        this.receivedUser = null;
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
