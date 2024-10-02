package com.example.demo.datafetchers;

import java.util.List;

public class ClaimObj {

    private String claimId;

    private String claimNumber;

    private String lineOfBusiness;

    private String state;

    List<VehicleObj> listVehicle;

    List<ParticipantObj> listParticipant;

    public List<ParticipantObj> getListParticipant() {
        return listParticipant;
    }

    public void setListParticipant(List<ParticipantObj> listParticipant) {
        this.listParticipant = listParticipant;
    }

    public List<VehicleObj> getListVehicle() {
        return listVehicle;
    }

    public void setListVehicle(List<VehicleObj> listVehicle) {
        this.listVehicle = listVehicle;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
