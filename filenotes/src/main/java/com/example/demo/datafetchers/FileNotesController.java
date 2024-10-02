package com.example.demo.datafetchers;

import com.example.demo.generated.types.FileNote;
import com.example.demo.generated.types.ParticipantName;
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
public class FileNotesController {

    @GetMapping("/filenotesByClaimId/{claimId}")
    @ResponseBody
    public List<FileNote> filenotesByClaimId(@PathVariable String claimId) {
        List<FileNote> fileNotesClaims = new ArrayList<>();
        ParticipantName name1 = new ParticipantName("1","Andrew","Flitoff","","Andrew Flitoff");
        FileNote fileNote1 = new FileNote("1","2B784921","1",name1, "FileNote -1");
        ParticipantName name2 = new ParticipantName("2","David","Miller","", "David Miller");
        FileNote fileNote2 = new FileNote("1","2B784921","2",name2,"FileNote -2");
        ParticipantName name3 = new ParticipantName("3","Steve","Vaugh","", "Steve Vaugh");
        FileNote fileNote3 = new FileNote("2","2B784922","1",name3,"FileNote -3");
        ParticipantName name4 = new ParticipantName("4","Brian","Lara","", "Brian Lara");
        FileNote fileNote4 = new FileNote("2","2B784922","2",name4,"FileNote -4");

        //List<FileNote> fileNotesClaim1 = new ArrayList<FileNote>();
        fileNotesClaims.add(fileNote1);
        fileNotesClaims.add(fileNote2);
        //fileNotesClaims.put("1", fileNotesClaim1);

        //List<FileNote> fileNotesClaim2 = new ArrayList<FileNote>();
        fileNotesClaims.add(fileNote3);
        fileNotesClaims.add(fileNote4);

        return fileNotesClaims.stream().filter(fileNote -> fileNote.getClaimId().equals(claimId)).collect(Collectors.toList());
    }

    @GetMapping("/filenotesByClaimId/{claimId}/{participantiId}")
    @ResponseBody
    public List<FileNote> filenotesByparticipantiId(@PathVariable String claimId, @PathVariable String participantiId) {
        Map<String, List<FileNote>> fileNotesClaims = new HashMap<>();

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

        List<FileNote> fileNotesList = fileNotesClaims.get(claimId);
        return fileNotesList.stream().filter(fileNote -> fileNote.getParticipantIdentifier().equals(participantiId)).collect(Collectors.toList());

    }
}
