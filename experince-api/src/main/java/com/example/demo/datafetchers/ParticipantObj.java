package com.example.demo.datafetchers;

public class ParticipantObj {

    private String claimId;

    private String participantIdentifier;

    private String customerId;

    private String participantType;

    private String participantRole;

    private ParticipantNameObj participantName;

    public ParticipantObj() {
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getParticipantIdentifier() {
        return participantIdentifier;
    }

    public void setParticipantIdentifier(String participantIdentifier) {
        this.participantIdentifier = participantIdentifier;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public String getParticipantRole() {
        return participantRole;
    }

    public void setParticipantRole(String participantRole) {
        this.participantRole = participantRole;
    }

    public ParticipantNameObj getParticipantName() {
        return participantName;
    }

    public void setParticipantName(ParticipantNameObj participantName) {
        this.participantName = participantName;
    }
}
