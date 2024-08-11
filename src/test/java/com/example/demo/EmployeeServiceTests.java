package com.example.demo;

import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import com.example.demo.controllers.EmployeeController;
import com.example.demo.models.Employee;
import com.example.demo.models.Position;
import com.example.demo.services.EmployeeService;



@WebMvcTest(EmployeeController.class)
public class EmployeeServiceTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeService employeeService;


	@Test
	public void testAddEmployee() throws Exception {
        Employee mockemployee=new Employee(1L, "Andrii", "Smith", 123456, new Position(1L,"shoper","desc"));
        String employee = "{\"id\": 1, \"name\": \"Andrii\", \"secondName\": \"Smith\", \"passport\": 123456, \"position\": { \"id\": 1, \"name\": \"shoper\", \"description\": \"desc\" }}";
        
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockemployee);

        mvc
        .perform(MockMvcRequestBuilders.post("/employees")
        .content(employee)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", containsString("/employees/1")));
        
	}

}
