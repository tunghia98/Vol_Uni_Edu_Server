package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    final private List<Customer> customers = List.of(
            Customer.builder().id("1").name("Customer 1").email("c1@gmail.com").build(),
            Customer.builder().id("2").name("Customer 2").email("c2@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
        return ResponseEntity.ok(customers.getFirst());
    }
}