package com.example.fractalchallengebackend.models.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Document
@Data
public class Customer {

    @Id
    private String id;

    @Length(min = 3, max = 100)
    @Pattern(regexp = "^\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+$")
    private String firstName;

    @Length(min = 3, max = 100)
    @Pattern(regexp = "^\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+$")
    private String lastName;

    @Length(min = 3, max = 13)
    @Pattern(regexp = "^/d+$")
    private String phone;

    @Email
    private String email;

    public Customer(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
