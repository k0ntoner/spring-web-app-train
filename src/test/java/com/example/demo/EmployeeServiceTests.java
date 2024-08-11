package com.example.demo;

import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import com.example.demo.controllers.EmployeeController;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeServiceTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeService employeeService;


	@Test
	public void testAddEmployee() throws Exception {
        Employee mockemployee=new Employee(1, "Andrii");
        String employee = "{\"id\":1, \"name\": \"Andrii\"}";
        
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockemployee);

        mvc
        .perform(MockMvcRequestBuilders.post("/employees")
        .content(employee)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", containsString("/employees/1")));
        
	}

}
