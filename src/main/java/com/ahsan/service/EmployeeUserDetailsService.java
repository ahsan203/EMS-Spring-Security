package com.ahsan.service;

import com.ahsan.entities.Employee;
import com.ahsan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsService implements UserDetailsService
{
    @Autowired
    private EmployeeRepository repository;

    /*@Autowired
    public EmployeeUserDetailsService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeUserDetailsService() {
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Employee> employee = repository.findByUserName(userName);

        //---- Now convert the above employee-object to UserDetail-object as the return type is UserDetails

        return employee
                .map(EmployeeUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(userName + " Not Found In System. "));
    }
}
