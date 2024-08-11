package com.example.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeController {
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
        
        
        var newEmployee= employeeService.addEmployee(employee);
        if(newEmployee==null){
            return ResponseEntity.noContent().build();
        }
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getMethodName(@PathVariable int id) {
        Employee searchingEmployee= employeeService.retriveEmployeeById(id);
        if(searchingEmployee==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(searchingEmployee);
    }
    
    
}
