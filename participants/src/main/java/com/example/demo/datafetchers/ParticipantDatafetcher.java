package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.ParticipantName;
import com.example.demo.generated.types.Participant;
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
public class ParticipantDatafetcher {

     Map<String, List<Participant>> participants = new HashMap<>();


    public ParticipantDatafetcher() {
        ParticipantName name1 = new ParticipantName("1","Andrew","Flitoff","","Andrew Flitoff");
        ParticipantName name2 = new ParticipantName("2","David","Miller","", "David Miller");
        ParticipantName name3 = new ParticipantName("3","Steve","Vaugh","","Steve Vaugh");
        ParticipantName name4 = new ParticipantName("4","Brian","Lara","","Brian Lara");

        List<Participant> claim1 = new ArrayList<>();
        claim1.add(new Participant("1","1","123","Individual","Named Insured",name1));
        claim1.add(new Participant("1","2","456","Individual","Claimant",name2));
        participants.put("1", claim1);

        List<Participant> claim2 = new ArrayList<>();
        claim2.add(new Participant("2","3","789","Individual","Named Insured",name3));
        claim2.add(new Participant("2","4","678","Individual","Claimant",name4));
        participants.put("2", claim2);
    }

    @DgsEntityFetcher(name = "Claim")
    public Claim claimsFetcher(Map<String, Object> values) {
        return new Claim((String) values.get("claimId"), null,null);
    }

    @DgsData(parentType = "Claim", field = "participants")
    public List<Participant> participantsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Claim claim = dataFetchingEnvironment.getSource();
        System.out.println("participants =====>"+claim.getClaimId());
        return participants.get(claim.getClaimId());
    }

    @DgsData(parentType = "Claim", field = "retrieveParticipant" )
    public List<Participant> participantFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Claim claim = dataFetchingEnvironment.getSource();
        System.out.println("==================>"+dataFetchingEnvironment.getArguments().get("claimId"));
        // Participant participant = dataFetchingEnvironment.getSource();
        //System.out.println("==================>"+participant.toString());
        List<Participant> participantList = participants.get(claim.getClaimId());
        return participantList.stream().filter(participant -> participant.getParticipantIdentifier().equals(dataFetchingEnvironment.getArguments().get("participantIdentifier") )).collect(Collectors.toList());
    }
}
