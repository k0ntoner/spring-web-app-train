package com.example.demo.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        
        
        var newEmployee= employeeService.addEmployee(employee);
        if(newEmployee==null){
            return ResponseEntity.noContent().build();
        }
        
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> searchingEmployee= employeeService.getEmployeeById(id);
        if(searchingEmployee==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(searchingEmployee);
    }
    
    
}
