package com.example.controllers;

import com.example.dto.CustomersDTO;
import com.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody CustomersDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.ok("Клиент добавлен успешно");
    }


    @GetMapping("/list")
    public ResponseEntity<List<CustomersDTO>> getAllCustomers() {
        List<CustomersDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }


    @GetMapping("/codeMain/{customerCodeMain}")
    public ResponseEntity<CustomersDTO> getCustomerByCodeMain(@PathVariable String customerCodeMain) {
        CustomersDTO customer = customerService.getCustomerByCodeMain(customerCodeMain);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    @PutMapping("/update/{customerCodeMain}")
    public ResponseEntity<String> updateCustomer(@PathVariable String customerCodeMain, @RequestBody CustomersDTO customerDTO) {
        customerService.updateCustomerByCodeMain(customerCodeMain, customerDTO);
        return ResponseEntity.ok("Клиент обновлен успешно");
    }


    @DeleteMapping("/delete/{customerCodeMain}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerCodeMain) {
        customerService.deleteCustomerByCodeMain(customerCodeMain);
        return ResponseEntity.ok("Клиент удален успешно");
    }
}
