package com.apidemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SimpleWebController {
    
    @GetMapping
    public String index() { return "Hello Spring Boot"; }
}
