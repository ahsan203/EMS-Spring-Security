package com.ahsan.service;

import com.ahsan.entities.Employee;
import com.ahsan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    private EmployeeRepository  employeeRepository;
    private PasswordEncoder passwordEncoder;


    public static final String DEFAUL_ROLE = "ROLE_EMPLOYEE";

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,PasswordEncoder passwordEncoder)
    {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //------- CRUD.....

    public Employee createEmployee(Employee employee)
    {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(DEFAUL_ROLE);
       return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer eid)
    {
        return employeeRepository.findById(eid).orElseThrow(()-> new RuntimeException("Employee Not Found with id " + eid));

    }


    public Employee changeRoleOfEmployee(Employee emp)
    {
        //--- First get id of employee by leveraging above getEmployeeById(Integer eid) method...

        Employee existingEmployee = getEmployeeById(emp.getId());

        existingEmployee.setRoles(emp.getRoles());

        return employeeRepository.save(existingEmployee);



    }




}
