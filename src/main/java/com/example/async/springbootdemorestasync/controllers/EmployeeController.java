package com.example.async.springbootdemorestasync.controllers;

import com.example.async.springbootdemorestasync.models.Employee;
import com.example.async.springbootdemorestasync.services.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        List<Employee> responseEmployeeList = new ArrayList<Employee>();

        for( int portIndex=3000; portIndex <= 3010; portIndex++){
            String port = Integer.toString(portIndex);
            List<Employee> employees = this.employeesService.getEmployees(port);
            log.info("Adding Employees to List");
            employees.forEach( employee -> responseEmployeeList.add(employee));
        }

        log.info("Returning");
        return new ResponseEntity<>(responseEmployeeList, HttpStatus.OK);
    }

    @GetMapping("/async")
    public ResponseEntity<List<Employee>> getAllAsyncEmployees() throws IOException, ExecutionException, InterruptedException {
        log.info("---> Received a Request <---");

        List<String> resourcePorts = Arrays.asList("3000","3001","3002","3003","3004","3005","3006","3007","3008","3009","3010");

        CompletableFuture<List<Employee>> emp1 = this.employeesService.getAsyncEmployees("3000");
        CompletableFuture<List<Employee>> emp2 = this.employeesService.getAsyncEmployees("3001");
        CompletableFuture<List<Employee>> emp3 = this.employeesService.getAsyncEmployees("3002");
        CompletableFuture<List<Employee>> emp4 = this.employeesService.getAsyncEmployees("3003");
        CompletableFuture<List<Employee>> emp5 = this.employeesService.getAsyncEmployees("3004");
        CompletableFuture<List<Employee>> emp6 = this.employeesService.getAsyncEmployees("3005");
        CompletableFuture<List<Employee>> emp7 = this.employeesService.getAsyncEmployees("3006");
        CompletableFuture<List<Employee>> emp8 = this.employeesService.getAsyncEmployees("3007");
        CompletableFuture<List<Employee>> emp9 = this.employeesService.getAsyncEmployees("3008");
        CompletableFuture<List<Employee>> emp10 = this.employeesService.getAsyncEmployees("3009");
        CompletableFuture<List<Employee>> emp11 = this.employeesService.getAsyncEmployees("3010");

        CompletableFuture.allOf(emp1,emp2,emp3,emp4,emp5,emp6,emp7,emp8,emp9,emp10,emp11);

        log.info("All Returned");

        List<Employee> resultList = new ArrayList<Employee>();
        emp1.get().forEach( employee -> resultList.add(employee));
        emp2.get().forEach( employee -> resultList.add(employee));
        emp3.get().forEach( employee -> resultList.add(employee));
        emp4.get().forEach( employee -> resultList.add(employee));
        emp5.get().forEach( employee -> resultList.add(employee));
        emp6.get().forEach( employee -> resultList.add(employee));
        emp7.get().forEach( employee -> resultList.add(employee));
        emp8.get().forEach( employee -> resultList.add(employee));
        emp9.get().forEach( employee -> resultList.add(employee));
        emp10.get().forEach( employee -> resultList.add(employee));
        emp11.get().forEach( employee -> resultList.add(employee));

        log.info("Returning");
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
