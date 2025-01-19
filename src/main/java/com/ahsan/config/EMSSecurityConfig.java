/*
package com.ahsan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EMSSecurityConfig extends WebSecurityConfigurerAdapter
{

    //---- WebSecurity has 3-overloaded-confgiure() methods :

    //---- 1) To provide multiple usernames and password :
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("Ahsan")
                .password(passwordEncoder().encode("Ahsan7027$"))
                .roles("USER","ADMIN");

        auth.inMemoryAuthentication()
                .withUser("Shoeab")
                .password(passwordEncoder().encode("Shoeab7018$"))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("Nomaan")
                .password(passwordEncoder().encode("Nomaan7040$"))
                .roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

   // 2) To authenticate OR permit-all endpoints:
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers("/home").permitAll()
                .and()
                .authorizeRequests().antMatchers("/welcome","/text").authenticated().and().httpBasic();
    }
}
*/
