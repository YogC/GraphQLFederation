package com.example.demo.datafetchers;

import com.example.demo.generated.types.*;
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
public class CustomerDatafetcher {

     Map<String, Customer> customers = new HashMap<>();


    public CustomerDatafetcher() {

        CustomerName customerName1 = new CustomerName("1","Andrew","Flitoff","", "Andrew Flitoff");
        CustomerName customerName2 = new CustomerName("2","David","Miller","", "David Miller");

        CustomerAddress customerAddress1 = new CustomerAddress("1","123","Ray Drive","IL","61704","USA");
        CustomerAddress customerAddress2 = new CustomerAddress("2","456","Bay Drive","IL","61701","USA");

        CustomerEmail customerEmail1 = new CustomerEmail("1","123","abc@gmail.com");
        CustomerEmail customerEmail2 = new CustomerEmail("2","456","pqr@gmail.com");

        CustomerPhone customerPhone1 = new CustomerPhone("1","123","309","1234567","USA");
        CustomerPhone customerPhone2 = new CustomerPhone("2","456","309","7654321","USA");

        Customer customer1 = new Customer("123",customerPhone1,customerEmail1,customerAddress1,customerName1);
        Customer customer2 = new Customer("456",customerPhone2,customerEmail2,customerAddress2,customerName2);

        customers.put("123", customer1);
        customers.put("456", customer2);
    }

    @DgsEntityFetcher(name = "Participant")
    public Participant movie(Map<String, Object> values) {
        return new Participant((String) values.get("customerId"),null);
    }

    @DgsData(parentType = "Participant", field = "readCustomer")
    public Customer participantsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Participant participant = dataFetchingEnvironment.getSource();
        System.out.println("participants getCustomerId =====>"+participant.getCustomerId());
        System.out.println("participants getCustomerId =====>"+participant.toString());
        return customers.get(participant.getCustomerId());
    }


}
