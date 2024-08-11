package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        try{
            
            return employeeRepository.save(employee);
        }
        catch(Exception e){
            return null;
        }
        
    }
    public Optional<Employee> getEmployeeById(Long id){
        try{
            if(id!=null)
                return employeeRepository.findById(id);
            return null;
        }
        catch(Exception e){
            return null;
        }
        
    }
}
 