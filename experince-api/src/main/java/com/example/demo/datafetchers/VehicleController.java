package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.Policy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    @GetMapping("/experienceAPI/retrieveClaimByPolicyAndClaimId/{policyId}/{claimId}")
    @ResponseBody
    public List<PolicyObj> readClaimsForPolicy(@PathVariable String policyId, @PathVariable String claimId) {

        WebClient webClientPolicy = WebClient.builder()
                .baseUrl("http://localhost:8084/policyid/"+policyId)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<Object[]> responsePolicy = webClientPolicy.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class).log();

        WebClient webClientClaim = WebClient.builder()
                .baseUrl("http://localhost:8080/claimidByPolicy/"+claimId)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<Object[]> responseClaim = webClientClaim.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class).log();

        WebClient webClientVehicle = WebClient.builder()
                .baseUrl("http://localhost:8082/vehiclesByClaimId/"+claimId)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<Object[]> responseVehicle = webClientVehicle.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class).log();

        WebClient webClientParticipant = WebClient.builder()
                .baseUrl("http://localhost:8081/getPartcipantsByClaimId/"+claimId)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<Object[]> responseParticipant = webClientParticipant.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class).log();

        Object[] objectsPolicy =  responsePolicy.block();
        Object[] objectsClaim =  responseClaim.block();
        Object[] objectsVehicle =  responseVehicle.block();
        Object[] objectsParticipant =  responseParticipant.block();

        ObjectMapper mapper = new ObjectMapper();
        List<PolicyObj> list = Arrays.stream(objectsPolicy)
                .map(object -> mapper.convertValue(object, PolicyObj.class))
                .collect(Collectors.toList());

        List<ClaimObj> listClaim = Arrays.stream(objectsClaim)
                .map(object -> mapper.convertValue(object, ClaimObj.class))
                .collect(Collectors.toList());

        List<VehicleObj> listVehicle = Arrays.stream(objectsVehicle)
                .map(object -> mapper.convertValue(object, VehicleObj.class))
                .collect(Collectors.toList());

        List<ParticipantObj> listParticipant = Arrays.stream(objectsParticipant)
                .map(object -> mapper.convertValue(object, ParticipantObj.class))
                .collect(Collectors.toList());

        listClaim.get(0).setListVehicle(listVehicle);
        listClaim.get(0).setListParticipant(listParticipant);
        list.get(0).setList(listClaim);

        return list;
    }

    @GetMapping("/vehiclesByParticipantId/{participantId}")
    @ResponseBody
    public List<Vehicle> vehiclesByParticipantId(@PathVariable String participantId) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("1","1","1","Toyota","Camry","2020"));
        vehicles.add(new Vehicle("2","1","2","Honda","CIVIC","2021"));
        vehicles.add(new Vehicle("3","2","3","Honda","CRV","2020"));
        vehicles.add(new Vehicle("4","2","4","Toyota","RAV4","2021"));
        return vehicles.stream().filter(vehicle -> vehicle.getParticipantIdentifier().equals(participantId)).collect(Collectors.toList());
    }
}
