package com.ahsan.service;

import com.ahsan.entities.Employee;
import com.ahsan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class EmployeeUserDetailsService implements UserDetailsService
{
    private EmployeeRepository repository;

    @Autowired
    public EmployeeUserDetailsService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Employee> employee = repository.findByUserName(userName);

        //---- Now convert the above employee-object to UserDetail-object

        return employee
                .map(EmployeeUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(userName + " Not Found In System. "));
    }
}
