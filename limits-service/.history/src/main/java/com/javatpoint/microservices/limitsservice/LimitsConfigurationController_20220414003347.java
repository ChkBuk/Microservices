package com.javatpoint.microservices.limitsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    @GetMapping("/limits")
    public String retriveLimitsFromConfigurations() {
        return "new LimitConfiguration(1000, 1)";
    }
}