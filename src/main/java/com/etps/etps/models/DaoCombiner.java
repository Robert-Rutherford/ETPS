package com.etps.etps.models;

import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Users;

public class DaoCombiner {
    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;

    public DaoCombiner() {

    }

    public DaoCombiner(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
    }

    public Users getUserDao() {
        return userDao;
    }

    public void setUserDao(Users userDao) {
        this.userDao = userDao;
    }

    public Providers getProviderDao() {
        return providerDao;
    }

    public void setProviderDao(Providers providerDao) {
        this.providerDao = providerDao;
    }

    public Campuses getCampusDao() {
        return campusDao;
    }

    public void setCampusDao(Campuses campusDao) {
        this.campusDao = campusDao;
    }

    public Programs getProgramDao() {
        return programDao;
    }

    public void setProgramDao(Programs programDao) {
        this.programDao = programDao;
    }
}
