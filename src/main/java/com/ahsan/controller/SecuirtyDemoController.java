package com.ahsan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuirtyDemoController
{

    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to Ahsan's Electronics Store";
    }

    @GetMapping("/text")
    public String greeting()
    {
        return "Greeting : Happy to see you here!!!";
    }

    @GetMapping("/home")
    public String nonSecure()
    {
        return "Happy to see you here!!!, But this is unsecure....";
    }
}
