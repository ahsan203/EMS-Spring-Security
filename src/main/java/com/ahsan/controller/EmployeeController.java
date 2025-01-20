package com.ahsan.controller;

import com.ahsan.dto.AuthRequest;
import com.ahsan.entities.Employee;
import com.ahsan.service.EmployeeService;
import com.ahsan.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {



    public static final String AUTHORITY_ROLE_HR = "hasAuthority('ROLE_HR')";

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public JwtService jwtService;
    @Autowired
    private EmployeeService  service;


    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to Ahsan-FinTech.";
    }
    @GetMapping("/welcome2")
    public String welcome2()
    {
        return "Welcome to Zoro-FinTech.";
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


   /* @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest ar){

        Authentication authenticate = am.authenticate(new UsernamePasswordAuthenticationToken(ar.getUserName(), ar.getPassword()));

        if(authenticate.isAuthenticated())
        {
            return jwtService.generateToken(ar.getUserName());
        }
        else {
            throw new UsernameNotFoundException("Authentication Failed!!!");
        }
    }*/
   /*@PostMapping("/authenticate")
   public String authenticate(@RequestBody AuthRequest authRequest) {
       Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
       if (authenticate.isAuthenticated()) {
           return jwtService.generateToken(authRequest.getUserName());
       } else {
           throw new UsernameNotFoundException("Authentication failed !");
       }
   }*/

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            // Check if user name and password are present
            if (authRequest.getUserName() == null || authRequest.getPassword() == null) {
                return ResponseEntity.badRequest().body("Username and password cannot be null");
            }

            // Attempt authentication
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );

            // Check if authentication is successful
            if (authenticate.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUserName());
                return ResponseEntity.ok(token); // Return token with 200 OK
            } else {
                throw new UsernameNotFoundException("Authentication failed!");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
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
