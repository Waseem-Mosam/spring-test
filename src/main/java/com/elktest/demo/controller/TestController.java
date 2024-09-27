package com.elktest.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test")
    public String testEndpoint() {
        logger.info("Test endpoint accessed");
        return "Hello from Spring Boot!";
    }

    @GetMapping("/error")
    public String errorEndpoint() {
        logger.error("Error occurred", new RuntimeException("Simulated error"));
        return "An error occurred";
    }
}
