/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.datafetchers;

import com.example.demo.generated.types.Policy;
import com.netflix.graphql.dgs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
public class PolicyDataFetcher {

    public List<Policy> policies = new ArrayList<Policy>();

    public PolicyDataFetcher() {
        policies.add( new Policy("1","2B784921", "Auto"));
        policies.add( new Policy("2","2B784922", "Auto"));
    }

    @DgsEntityFetcher(name = "Policy")
    public List<Policy> movie(List<Policy> policies , DgsDataFetchingEnvironment dataFetchingEnvironment) {
        System.out.println("dataFetchingEnvironment.getArguments() =====>"+dataFetchingEnvironment.getArguments().get("policyId"));
        return policies.stream().filter(policyRecord -> policyRecord.getPolicyId().equals(dataFetchingEnvironment.getArguments().get("policyId") )).collect(Collectors.toList());
    }

    @DgsData(parentType = "Query", field = "retrievePolicy")
    public List<Policy> policyDataFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        // Policy policy = dataFetchingEnvironment.getSource();
       // System.out.println("policy =====>"+policy.getPolicyId());
        System.out.println("dataFetchingEnvironment.getArguments() =====>"+dataFetchingEnvironment.getArguments().get("policyId"));
        return policies.stream().filter(policyRecord -> policyRecord.getPolicyId().equals(dataFetchingEnvironment.getArguments().get("policyId") )).collect(Collectors.toList());
    }
}

