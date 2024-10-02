package com.example.demo.datafetchers;

import com.example.demo.generated.types.FileNote;
import com.example.demo.generated.types.Participant;
import com.example.demo.generated.types.ParticipantName;
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
public class FileNotesParticipantDatafetcher {

    Map<String, List<FileNote>> fileNotesClaims = new HashMap<>();

    public FileNotesParticipantDatafetcher() {
        ParticipantName name1 = new ParticipantName("1","Andrew","Flitoff","","Andrew Flitoff");
        FileNote fileNote1 = new FileNote("1","2B784921","1",name1, "FileNote -1");
        ParticipantName name2 = new ParticipantName("2","David","Miller","", "David Miller");
        FileNote fileNote2 = new FileNote("1","2B784921","2",name2,"FileNote -2");
        ParticipantName name3 = new ParticipantName("3","Steve","Vaugh","", "Steve Vaugh");
        FileNote fileNote3 = new FileNote("2","2B784922","1",name3,"FileNote -3");
        ParticipantName name4 = new ParticipantName("4","Brian","Lara","", "Brian Lara");
        FileNote fileNote4 = new FileNote("2","2B784922","2",name4,"FileNote -4");

        List<FileNote> fileNotesClaim1 = new ArrayList<FileNote>();
        fileNotesClaim1.add(fileNote1);
        fileNotesClaim1.add(fileNote2);
        fileNotesClaims.put("1", fileNotesClaim1);

        List<FileNote> fileNotesClaim2 = new ArrayList<FileNote>();
        fileNotesClaim2.add(fileNote3);
        fileNotesClaim2.add(fileNote4);
        fileNotesClaims.put("2", fileNotesClaim2);
    }

    @DgsEntityFetcher(name = "Participant")
    public Participant movie(Map<String, Object> values) {
        return new Participant((String) values.get("claimId"),(String) values.get("participantIdentifier"), null,null);
    }

    @DgsData(parentType = "Participant", field = "fileNotes")
    public List<FileNote> participantsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        //Claim claim = dataFetchingEnvironment.getSource();
        Participant participant = dataFetchingEnvironment.getSource();
        System.out.println("Participant =====>"+participant.getClaimId());
        System.out.println("Participant =====>"+participant.getParticipantIdentifier());
       // System.out.println("dataFetchingEnvironment.getArguments().get(\"claimId\")==================>"+dataFetchingEnvironment.getArguments().get("claimId"));
       // System.out.println("dataFetchingEnvironment.getArguments().get(\"participantIdentifier\")==================>"+dataFetchingEnvironment.getArguments().get("participantIdentifier"));
        List<FileNote> fileNotesList = fileNotesClaims.get(participant.getClaimId());
        return fileNotesList.stream().filter(fileNote -> fileNote.getParticipantIdentifier().equals(participant.getParticipantIdentifier())).collect(Collectors.toList());

    }

    @DgsData(parentType = "Participant", field = "fileNotesParticipant" )
    public List<FileNote> participantFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        //Claim claim = dataFetchingEnvironment.getSource();
        //System.out.println("claim ==================> "+claim.getClaimId());
        System.out.println("dataFetchingEnvironment.getArguments().get(\"claimId\")==================>"+dataFetchingEnvironment.getArguments().get("claimId"));
        // Participant participant = dataFetchingEnvironment.getSource();
        // System.out.println("==================>"+participant.toString());
        List<FileNote> fileNotesList = fileNotesClaims.get(dataFetchingEnvironment.getArguments().get("claimId"));
        return fileNotesList.stream().filter(fileNote -> fileNote.getParticipantIdentifier().equals(dataFetchingEnvironment.getArguments().get("participantIdentifier") )).collect(Collectors.toList());
    }
}
