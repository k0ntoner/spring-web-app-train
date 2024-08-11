package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;

@Service
public class EmployeeService {
    
    private static List<Employee> employees= new ArrayList<Employee>(); 

    public Employee addEmployee(Employee employee){
        employees.add(employee);
        return employee;
    }
    public Employee retriveEmployeeById(int id){
        try{
            return employees.get(id);
        }
        catch(Exception e){
            return null;
        }
        
    }
}
