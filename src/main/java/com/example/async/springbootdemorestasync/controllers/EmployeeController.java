package com.example.async.springbootdemorestasync.controllers;

import com.example.async.springbootdemorestasync.models.Employee;
import com.example.async.springbootdemorestasync.models.Employees;
import com.example.async.springbootdemorestasync.services.EmployeeService;
import com.example.async.springbootdemorestasync.services.MessageProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.api.core.ActiveMQException;
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

    private EmployeeService employeeService;
    private MessageProducerService messageProducerService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, MessageProducerService messageProducerService) {
        this.employeeService = employeeService;
        this.messageProducerService = messageProducerService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException, ActiveMQException {
        List<Employee> responseEmployeeList = new ArrayList<Employee>();

        for( int portIndex=3000; portIndex <= 3010; portIndex++){
            String port = Integer.toString(portIndex);
            Employees employees = this.employeeService.getEmployees(port);
            log.info("Adding Employees to List");
            employees.getEmployees().forEach( employee -> responseEmployeeList.add(employee));
        }

        log.info("Publishing Message");
        messageProducerService.sendMessage(responseEmployeeList);
        log.info("Returning");
        return new ResponseEntity<>(responseEmployeeList, HttpStatus.OK);
    }

    @GetMapping("/async")
    public ResponseEntity<List<Employee>> getAllAsyncEmployees() throws IOException, ExecutionException, InterruptedException, ActiveMQException {
        log.info("---> Received a Request <---");

        List<String> resourcePorts = Arrays.asList("3000","3001","3002","3003","3004","3005","3006","3007","3008","3009","3010");

        CompletableFuture<Employees> emp1 = this.employeeService.getAsyncEmployees("3000");
        CompletableFuture<Employees> emp2 = this.employeeService.getAsyncEmployees("3001");
        CompletableFuture<Employees> emp3 = this.employeeService.getAsyncEmployees("3002");
        CompletableFuture<Employees> emp4 = this.employeeService.getAsyncEmployees("3003");
        CompletableFuture<Employees> emp5 = this.employeeService.getAsyncEmployees("3004");
        CompletableFuture<Employees> emp6 = this.employeeService.getAsyncEmployees("3005");
        CompletableFuture<Employees> emp7 = this.employeeService.getAsyncEmployees("3006");
        CompletableFuture<Employees> emp8 = this.employeeService.getAsyncEmployees("3007");
        CompletableFuture<Employees> emp9 = this.employeeService.getAsyncEmployees("3008");
        CompletableFuture<Employees> emp10 = this.employeeService.getAsyncEmployees("3009");
        CompletableFuture<Employees> emp11 = this.employeeService.getAsyncEmployees("3010");

        CompletableFuture.allOf(emp1,emp2,emp3,emp4,emp5,emp6,emp7,emp8,emp9,emp10,emp11);

        log.info("All Returned");

        List<Employee> resultList = new ArrayList<Employee>();
        emp1.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp2.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp3.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp4.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp5.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp6.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp7.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp8.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp9.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp10.get().getEmployees().forEach( employee -> resultList.add(employee));
        emp11.get().getEmployees().forEach( employee -> resultList.add(employee));

        log.info("Publishing Message");
        messageProducerService.sendMessage(resultList);

        log.info("Returning");
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
