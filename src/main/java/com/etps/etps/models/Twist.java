package com.etps.etps.models;

//table 1: TWIST information
//values: TWIST provider ID, TWIST Program ID, Action, program id (key)

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "twist")
public class Twist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider;

    private long twistProviderID;

    private long twistProgramID;

    private String actionTaken;

    public Twist() {

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

    public long getTwistProviderID() {
        return twistProviderID;
    }

    public void setTwistProviderID(long twistProviderID) {
        this.twistProviderID = twistProviderID;
    }

    public long getTwistProgramID() {
        return twistProgramID;
    }

    public void setTwistProgramID(long twistProgramID) {
        this.twistProgramID = twistProgramID;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
