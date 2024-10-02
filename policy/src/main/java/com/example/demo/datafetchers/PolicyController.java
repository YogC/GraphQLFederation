package com.example.demo.datafetchers;

import com.example.demo.generated.types.Policy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PolicyController {


    @GetMapping("/policyid/{id}")
    @ResponseBody
    public List<Policy> getPolicyById(@PathVariable String id){
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new Policy("1","2B784921", "Auto"));
        policies.add( new Policy("2","2B784922", "Auto"));
        return policies.stream().filter(policyRecord -> policyRecord.getPolicyId().equals((id) )).collect(Collectors.toList());
    }

}
