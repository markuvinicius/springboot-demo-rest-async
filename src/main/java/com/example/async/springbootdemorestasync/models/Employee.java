package com.example.async.springbootdemorestasync.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
}
