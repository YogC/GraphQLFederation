package com.example.demo.datafetchers;

import com.example.demo.generated.types.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @GetMapping("/customerById/{customerId}")
    @ResponseBody
    public Customer customerById(@PathVariable String customerId) {
        Map<String, Customer> customers = new HashMap<>();
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

        return customers.get(customerId);
    }

}
