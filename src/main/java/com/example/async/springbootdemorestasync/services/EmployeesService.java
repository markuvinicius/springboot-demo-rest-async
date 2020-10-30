package com.example.async.springbootdemorestasync.services;

import com.example.async.springbootdemorestasync.models.Employee;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeesService {
    public List<Employee> getEmployees(String port) throws IOException;

    public CompletableFuture<List<Employee>> getAsyncEmployees(String port);
}
