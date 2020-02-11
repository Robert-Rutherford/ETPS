package com.etps.etps.models;

public class ReadExcelObject {
    //                new Object[] {provider.getId(),provider.getProviderName(), provider.getDescription(),
//                        campus.getId(), campus.getCampusName(),program.getId(),program.getName(),
//                        program.getDescription(),program.getEtpCodeId()});
//    private long providerId;
//    private String providerName;
//    private String providerDescription;
//    private long campusID;
//    private String campusName

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
