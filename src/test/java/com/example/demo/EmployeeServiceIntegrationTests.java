package com.example.demo;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import com.example.demo.models.Employee;
import com.example.demo.models.Position;

@SpringBootTest(classes= DemoApplication.class, webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceIntegrationTests {
    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();
    public  String createUrl(String uri)
    {
        return "http://localhost:" + port + uri;
    }
    @Test
    public void addEmployee() throws Exception {
        
        Employee employee=new Employee(1L, "Jhon", "Smith", 123456, new Position(1L,"name","test"));
        URI location= template.postForLocation(createUrl("/employees"), employee);
        
        assertEquals(location.getPath(), containsString("/employees/1"));
    }


}
