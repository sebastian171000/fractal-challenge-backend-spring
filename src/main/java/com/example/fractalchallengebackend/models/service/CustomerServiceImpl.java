package com.example.fractalchallengebackend.models.service;

import com.example.fractalchallengebackend.models.entity.Customer;
import com.example.fractalchallengebackend.models.repository.ICustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService{

    private final ICustomerRepository customerRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(String idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(String idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Customer> insertManyData(Integer quantity) {
        Faker faker = new Faker();
        List<Customer> listData = new ArrayList<>();
        for (int i =0; i< quantity; i++){
            String firstName = faker.name().firstName();
            String secondName = faker.name().lastName();
            String phone = faker.phoneNumber().cellPhone();
            String email = faker.internet().emailAddress();
            Customer customer = new Customer(firstName, secondName, phone, email);
            listData.add(customer);
        }
        customerRepository.saveAll(listData);
        return listData;
    }

    @Override
    @Transactional
    public List<Customer> deleteManyData(Pageable limit) {
        List<Customer> customers = customerRepository.findAll(limit).getContent();
        List<String> customerIds = customers.stream().map(Customer::getId).collect(Collectors.toList());
        customerRepository.deleteAllById(customerIds);
        return customers;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
