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
        ParticipantName name1 = new ParticipantName("Andrew","Flitoff","");
        ParticipantName name2 = new ParticipantName("David","Miller","");
        ParticipantName name3 = new ParticipantName("Steve","Vaugh","");
        ParticipantName name4 = new ParticipantName("Brian","Lara","");

        List<Participant> claim1 = new ArrayList<>();
        claim1.add(new Participant("1","1","1","1",name1));
        claim1.add(new Participant("1","2","1","2",name2));
        participants.put("1", claim1);

        List<Participant> claim2 = new ArrayList<>();
        claim2.add(new Participant("2","1","1","4",name3));
        claim2.add(new Participant("2","2","1","2",name4));
        participants.put("2", claim2);
    }

    @DgsEntityFetcher(name = "Claim")
    public Claim movie(Map<String, Object> values) {
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
