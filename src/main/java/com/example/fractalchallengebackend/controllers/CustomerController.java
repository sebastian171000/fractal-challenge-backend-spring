package com.example.fractalchallengebackend.controllers;

import com.example.fractalchallengebackend.exception.ApiRequestException;
import com.example.fractalchallengebackend.models.entity.Customer;
import com.example.fractalchallengebackend.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Value("${customerController.sizeCustomersPerPage}")
    private Integer sizeCustomersPerPage;

    @PostMapping("/insertData/{quantity}")
    public ResponseEntity<Map<String, Object>> insertManyData(@PathVariable Integer quantity){
        //Pageable pageable = PageRequest.of(0, limit);
        List<Customer> customersInserted = customerService.insertManyData(quantity);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Customers with random data inserted successfully");
        map.put("customersInserted", customersInserted);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deleteData/{limit}")
    public ResponseEntity<Map<String, Object>> deleteManyData(@PathVariable Integer limit){
        Pageable pageable = PageRequest.of(0, limit);
        List<Customer> customersDeleted = customerService.deleteManyData(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Customers deleted successfully");
        map.put("customersDeleted", customersDeleted);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCustomers(@RequestParam(defaultValue = "0") int page){
        Pageable pageRequest = PageRequest.of(page, sizeCustomersPerPage, Sort.by("id"));
        Page<Customer> customers = customerService.findAllCustomers(pageRequest);
        //PageRender<Customer> pageRender = new PageRender<>("/", customers, 3);
        Map<String, Object> map = new HashMap<>();
        map.put("customers", customers.getContent());
        map.put("totalCustomers", customers.getContent().size());
        map.put("currentPage", page);
        map.put("hasNextPage", customers.hasNext());
        map.put("hasPreviousPage", customers.hasPrevious());
        map.put("nextPage", page + 1);
        map.put("previousPage", page - 1);
        map.put("lastPage", customers.getTotalPages());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomere(@RequestBody @Valid Customer customer){
        Map<String, Object> map = new HashMap<>();
        if(customer.getId() != null){
            throw new ApiRequestException("The id is autogenerated.");
        }

        customerService.saveCustomer(customer);
        map.put("message", "Customer added successfully");
        map.put("customerAdded", customer);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PatchMapping("/{customerId}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable String customerId, @RequestBody @Valid Customer customer){
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
