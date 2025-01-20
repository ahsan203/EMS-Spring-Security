package com.ahsan.config;

import com.ahsan.service.EmployeeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableWebSecurity @EnableGlobalMethodSecurity(prePostEnabled = true)
public class EMSSecuirtyUpgradeConfig
{
            //---- below 2 @Beans are for SecurityDemoController-class
    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    {
        UserDetails ahsan = User.withUsername("ahsan")
                .password(passwordEncoder.encode("Ahsan123$"))
                .roles("USER","ADMIN").build();

        UserDetails shoeab = User.withUsername("shoeab")
                .password(passwordEncoder.encode("Shoeab123$"))
                .roles("ADMIN").build();

        UserDetails nomaan = User.withUsername("nomaan")
                .password(passwordEncoder.encode("Nomaan123$"))
                .roles("USER").build();

        return new InMemoryUserDetailsManager(ahsan,shoeab,nomaan);
    }

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http.authorizeRequests().antMatchers("/home").permitAll()
                .and()
                .authorizeRequests().antMatchers("/welcome","/text").authenticated().and().httpBasic()
                .and().build();
    }*/


    //-------------- Below 2 @Beans is for EmployeeController:....
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    {
         /*UserDetails ahsan = User.withUsername("ahsan")
                .password(passwordEncoder.encode("Ahsan123$"))
                .roles("EMPLOYEE","MANAGER").build();

        UserDetails shoeab = User.withUsername("shoeab")
                .password(passwordEncoder.encode("Shoeab123$"))
                .roles("EMPLOYEE").build();

        UserDetails nomaan = User.withUsername("nomaan")
                .password(passwordEncoder.encode("Nomaan123$"))
                .roles("HR").build();

        return new InMemoryUserDetailsManager(ahsan,shoeab,nomaan);*/

        return  new EmployeeUserDetailsService();
    }

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http.csrf().disable()
                .authorizeRequests().antMatchers("/employees/welcome","/employees/create","/employees/authenticate","/employees/welcome2").permitAll()
                .and()
                .authorizeRequests().antMatchers("/employees/update","/employees/all").authenticated().and().httpBasic()
                .and().build();
    }


   /* @Bean
    public AuthenticationManager authenticate(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
