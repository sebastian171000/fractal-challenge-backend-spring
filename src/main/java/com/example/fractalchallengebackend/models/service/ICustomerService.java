package com.example.fractalchallengebackend.models.service;

import com.example.fractalchallengebackend.models.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public List<Customer> findAllCustomers();
    public Customer getCustomerById(String idCustomer);
    public void saveCustomer(Customer customer);
    public void deleteCustomer(String idCustomer);
    public Page<Customer> findAllCustomers(Pageable pageable);
    public List<Customer> insertManyData(Integer quantity);
    public List<Customer> deleteManyData(Pageable limit);
    public Optional<Customer> findCustomerByEmail(String email);
}
