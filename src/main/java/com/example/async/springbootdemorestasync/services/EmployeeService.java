package com.example.async.springbootdemorestasync.services;

import com.example.async.springbootdemorestasync.models.Employee;
import com.example.async.springbootdemorestasync.models.Employees;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    public Employees getEmployees(String port) throws IOException;

    public CompletableFuture<Employees> getAsyncEmployees(String port);
}
