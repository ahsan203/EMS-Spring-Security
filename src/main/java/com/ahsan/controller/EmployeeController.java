package com.ahsan.controller;

import com.ahsan.entities.Employee;
import com.ahsan.service.EmployeeService;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService  service;
    public static final String AUTHORITY_ROLE_HR = "hasAuthority('ROLE_HR')";

    @Autowired
    public EmployeeController(EmployeeService service)
    {
        this.service = service;
    }

    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to Ahsan-FinTech.";
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee onboardEmployee(@RequestBody Employee employee)
    {
        return service.createEmployee(employee);
    }

    @PutMapping("/update")
    @PreAuthorize(AUTHORITY_ROLE_HR)
    public Employee updateEmployee(@RequestBody Employee emp)
    {
        return service.changeRoleOfEmployee(emp);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_HR')")
    public List<Employee> getAll()
    {
        return service.getAllEmployees();
    }

    @GetMapping("/id/{eid}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Employee getEmpById(@PathVariable Integer eid)
    {
        return service.getEmployeeById(eid);

    }


    /*------ Note:
                    Ahsan = Employee, Manager ("/all", "/id/{id}")
                    Shoeab = Employee ("/id/{id}")
                    Nomaan = HR {"/create", "/all"}



                    {
    "id":1,
    "name": "Ahsan",
    "dept": " Software/IT",
    "salary":  89866770,
    "email": "ahsan203@gmail.com",
    "userName": "Ahsan",
    "password": "$2a$10$7X7wuMN0gCEJjp808B2KIuNNvqWjdGOeXFUXmCA6qdqPCDKhbrAiC",
    "roles":"ROLE_HR,ROLE_MANAGER"
}
     */



}
