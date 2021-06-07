package com.example.fractalchallengebackend.models.repository;

import com.example.fractalchallengebackend.models.entity.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends MongoRepository<Customer, String> {

    public Optional<Customer> findCustomerByEmail(String email);

}
