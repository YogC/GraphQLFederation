package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.Participant;
import com.example.demo.generated.types.ParticipantName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParticipantsController {

    @GetMapping("/getPartcipantsByClaimId/{claimid}")
    @ResponseBody
    public List<Participant> getPartcipantsByClaimId(@PathVariable String claimid){

        Map<String, List<Participant>> participants = new HashMap<>();
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

        return participants.get(claimid);
    }
}
