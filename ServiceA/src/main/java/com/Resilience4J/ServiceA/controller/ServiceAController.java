package com.Resilience4J.ServiceA.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Stack;

@RestController
@RequestMapping("/a")
public class ServiceAController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String BASE_URL= "http://localhost:8089/";
    private static final String SERVICE_A = "serviceA";
    @GetMapping
    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    public String serviceA(){
        String url = BASE_URL + "b";
        return restTemplate.getForObject(
                url,
                String.class
        );
    }

    public String serviceAFallback(Exception e){
        return "This is a fallback method for service A";

    }
}
