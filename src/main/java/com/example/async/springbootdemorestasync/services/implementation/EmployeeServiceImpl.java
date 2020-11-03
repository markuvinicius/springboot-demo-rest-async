package com.example.async.springbootdemorestasync.services.implementation;

import com.example.async.springbootdemorestasync.models.Employee;
import com.example.async.springbootdemorestasync.models.Employees;
import com.example.async.springbootdemorestasync.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private RestTemplate restTemplate;

    @Value("${app.resource.employee.url}")
    private String parameterURL;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Employees getEmployees(String port) throws IOException {
        String resourceURL = this.parameterURL.replaceAll("port",port);
        log.info("Calling Employees Service at port: " + port);

        ResponseEntity<Employees> response = this.restTemplate.exchange(resourceURL, HttpMethod.GET,null, Employees.class);
        return response.getBody();
    }

    @Override
    public CompletableFuture<Employees> getAsyncEmployees(String port){
        return CompletableFuture.supplyAsync( () -> {
                    String resourceURL = this.parameterURL.replaceAll("port",port);
                    log.info("Calling Employees Service at port: " + port);
                    ResponseEntity<Employees> response = this.restTemplate.exchange(resourceURL, HttpMethod.GET,null, Employees.class);
                    return response.getBody();
                });
    }
}
