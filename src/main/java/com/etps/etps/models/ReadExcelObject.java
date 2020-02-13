package com.etps.etps.models;

public class ReadExcelObject {

    private Provider newProvider;
    private Campus newCampus;
    private Program newProgram;

    public ReadExcelObject() {}

    public ReadExcelObject(Provider provider, Campus campus, Program program) {
        this.newProvider = provider;
        this.newCampus = campus;
        this.newProgram = program;
    }


    public Provider getNewProvider() {
        return newProvider;
    }

    public void setNewProvider(Provider newProvider) {
        this.newProvider = newProvider;
    }

    public Campus getNewCampus() {
        return newCampus;
    }

    public void setNewCampus(Campus newCampus) {
        this.newCampus = newCampus;
    }

    public Program getNewProgram() {
        return newProgram;
    }

    public void setNewProgram(Program newProgram) {
        this.newProgram = newProgram;
    }
}
