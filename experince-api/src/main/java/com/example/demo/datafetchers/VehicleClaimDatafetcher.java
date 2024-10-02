package com.example.demo.datafetchers;

import com.example.demo.generated.types.Claim;
import com.example.demo.generated.types.Vehicle;
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
public class VehicleClaimDatafetcher {

    List<Vehicle> vehicles = new ArrayList<>();

    public VehicleClaimDatafetcher() {

        //List<Vehicle> vehicleOne = new ArrayList<>();
        vehicles.add(new Vehicle("1","1","1","Toyota","Camry","2020"));
        vehicles.add(new Vehicle("2","1","2","Honda","CIVIC","2021"));
        //vehicles.put("1", vehicleOne);

        //List<Vehicle> vehicleTwo = new ArrayList<>();
        vehicles.add(new Vehicle("3","2","3","Honda","CRV","2020"));
        vehicles.add(new Vehicle("4","2","4","Toyota","RAV4","2021"));
        //vehicles.put("2", vehicleTwo);
    }

    @DgsEntityFetcher(name = "Claim")
    public Claim movie(Map<String, Object> values) {
        return new Claim((String) values.get("claimId"), null,null);
    }

    @DgsData(parentType = "Claim", field = "vehicles")
    public List<Vehicle> vehiclesFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Claim claim = dataFetchingEnvironment.getSource();
        System.out.println("Claim Vehicle ==================>"+claim.getClaimId());
        //return vehicles.get(claim.getClaimId());
        return vehicles.stream().filter(vehicle -> vehicle.getClaimId().equals(claim.getClaimId() )).collect(Collectors.toList());
    }

    @DgsData(parentType = "Claim", field = "retrieveVehicle" )
    public List<Vehicle> vehicleFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Claim claim = dataFetchingEnvironment.getSource();
        System.out.println("Claim Vehicle ==================>"+claim.getClaimId());
       // List<Vehicle> vehicleList = vehicles.get(claim.getClaimId());
        return vehicles.stream().filter(vehicle -> vehicle.getVehicleIdentifier().equals(dataFetchingEnvironment.getArguments().get("id") )).collect(Collectors.toList());
    }
}
