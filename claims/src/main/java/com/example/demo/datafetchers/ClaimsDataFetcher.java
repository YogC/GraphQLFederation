package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.Policy;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@DgsComponent
public class ClaimsDataFetcher {

    public Map<String, Claim> claims = new HashMap<>();

    public ClaimsDataFetcher() {
       claims.put("1",new Claim("1","2B784921", "Auto", "IL"));
       claims.put("2",new Claim("2","2B784922", "Auto", "IL"));
    }

    @DgsEntityFetcher(name = "Policy")
    public Policy movie(Map<String, Object> values) {
        return new Policy((String) values.get("policyId"), null,null);
    }

    @DgsData(parentType = "Policy", field = "retrieveClaims")
    public Claim claimsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Policy policy = dataFetchingEnvironment.getSource();
        System.out.println("retrieveClaims =====>"+policy.getPolicyId());
        return claims.get(policy.getPolicyId());
    }

    @DgsData(parentType = "Policy", field = "retrieveClaim" )
    public Claim claimFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Policy policy = dataFetchingEnvironment.getSource();
        System.out.println("==================>"+dataFetchingEnvironment.getArguments().get("claimId"));
        // Participant participant = dataFetchingEnvironment.getSource();
        //System.out.println("==================>"+participant.toString());
        Claim claim = claims.get(policy.getPolicyId());
        if(claim.getClaimId().equals(dataFetchingEnvironment.getArguments().get("claimId"))){
            return claim;
        }else{
            return null;
        }

    }
}

