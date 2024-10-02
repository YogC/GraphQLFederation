package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.Policy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ClaimsController {

    @GetMapping("/claimidByPolicy/{policyid}")
    @ResponseBody
    public List<Claim> getClaimById(@PathVariable String policyid){
        Map<String, Claim> claims = new HashMap<>();

        claims.put("1",new Claim("1","2B784921", "Auto", "IL"));
        claims.put("2",new Claim("2","2B784922", "Auto", "IL"));

        Claim claim = claims.get(policyid);
        List<Claim> claimList = new ArrayList<Claim>();
        claimList.add(claim);

        return claimList;
    }

    @GetMapping("/allClaims")
    @ResponseBody
    public List<Claim> getAllClaims(){


        List<Claim> claimList = new ArrayList<Claim>();
        claimList.add(new Claim("1","2B784921", "Auto", "IL"));
        claimList.add(new Claim("2","2B784922", "Auto", "IL"));


        return claimList;
    }
}
