package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;

import java.util.List;

public class PolicyObj {

    private String policyId;

    private String policyNumber;

    private String lineOfBusiness;

    List<ClaimObj> list;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public List<ClaimObj> getList() {
        return list;
    }

    public void setList(List<ClaimObj> list) {
        this.list = list;
    }

    public PolicyObj() {
    }

}
