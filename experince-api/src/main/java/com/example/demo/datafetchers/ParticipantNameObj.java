package com.example.demo.datafetchers;

public class ParticipantNameObj {

    private String participantNameIdentifier;

    private String firstName;

    private String LastName;

    private String MiddleName;

    private String fullName;

    public ParticipantNameObj() {
    }

    public String getParticipantNameIdentifier() {
        return participantNameIdentifier;
    }

    public void setParticipantNameIdentifier(String participantNameIdentifier) {
        this.participantNameIdentifier = participantNameIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
