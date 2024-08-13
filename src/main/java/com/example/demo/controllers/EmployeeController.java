package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        
        
        var newEmployee= employeeService.addEmployee(employee);
        if(newEmployee==null){
            return ResponseEntity.noContent().build();
        }
        
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees= employeeService.getAllEmployees();
        if(employees==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employees);      
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> searchingEmployee= employeeService.getEmployeeById(id);
        if(searchingEmployee==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(searchingEmployee);
    }
    
    
}
