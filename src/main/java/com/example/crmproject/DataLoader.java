package com.example.crmproject;

import com.example.crmproject.domain.Customer;
import com.example.crmproject.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerService customerService;

    public DataLoader(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        customerService.save(Customer.builder().firstName("David").lastName("Adams").email("david@gmail.com").build());
        customerService.save(Customer.builder().firstName("John").lastName("Doe").email("john@gmail.com").build());
        customerService.save(Customer.builder().firstName("Ajay").lastName("Rao").email("ajay@gmail.com").build());
        customerService.save(Customer.builder().firstName("Mary").lastName("Public").email("marry@gmail.com").build());
        customerService.save(Customer.builder().firstName("Maxwell").lastName("Dixon").email("maxwell@gmail.com").build());
    }
}
