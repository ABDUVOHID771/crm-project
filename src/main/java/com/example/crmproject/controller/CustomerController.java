package com.example.crmproject.controller;

import com.example.crmproject.domain.Customer;
import com.example.crmproject.mapper.CustomerMapper;
import com.example.crmproject.model.CustomerDTO;
import com.example.crmproject.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/customer/list")
    public ResponseEntity<List<CustomerDTO>> customerList() {
//        Set<Customer> customers = customerService.findAll();
//        model.addAttribute("customers", customers);
//        return customerService.findAll();
        return new ResponseEntity<>(customerMapper.findAll(customerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customerId) throws NotFoundException {
//        Customer customer = customerService.findById(customerId);
//        if (customer == null)
//            throw new NotFoundException("This customer does not exist : " + customerId);
//        return customer;
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerService.findById(customerId));
        if (customerDTO == null) {
            throw new NotFoundException("This customer does not exist : " + customerId);
        }
        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customerService.findById(customerId)), HttpStatus.OK);
    }


    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerMapper
                .customerToCustomerDTO(customerService.save(customerMapper.customerDTOToCustomer(customerDTO))),
                HttpStatus.OK);
    }

    @PutMapping("/customer")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                customerMapper.customerToCustomerDTO(customerService.save(customerMapper.customerDTOToCustomer(customerDTO))),
                HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) throws NotFoundException {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            throw new NotFoundException("Customer does not exist : " + customerId);
        }
        customerService.deleteById(customerId);
        return new ResponseEntity<>("Customer : " + customer.toString() + " is DELETED. ", HttpStatus.OK);
    }

//    @GetMapping("/customer/delete")
//    public String deleteCustomer(@RequestParam("customerId") Long id) {
//        customerService.deleteById(id);
//        return "redirect:/customer/list";
//    }

//    @PostMapping("/customer/saveCustomer")
//    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
//        System.out.println("in the process . . .");
//        customerService.save(customer);
//        return "redirect:/customer/list";
//    }
}
