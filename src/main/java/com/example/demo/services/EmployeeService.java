package com.example.demo.services;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Logger logger= LoggerFactory.getLogger(EmployeeService.class);
    public Employee addEmployee(Employee employee){
        try{
            
            Employee newEmployee= employeeRepository.save(employee);
              
            logger.info("Employee was successfully added");
            return newEmployee;
        }
        catch(Exception e){
            logger.info("Employee adding was failed", e);
            return null;
        }
        
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public void deleteEmployeeById(Long id){
        try {
            employeeRepository.deleteById(id);
            logger.info("Employee by id: {}, was deleted", id);
        } catch (Exception e) {
            logger.error("Error caused by deleting employee", e);
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
 