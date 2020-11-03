package com.example.async.springbootdemorestasync.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employees implements Serializable {
    private List<Employee> employees;
}
