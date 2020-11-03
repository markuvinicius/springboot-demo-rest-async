package com.example.async.springbootdemorestasync.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
}
