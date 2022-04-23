package com.javatpoint.limitsservice;

import com.javatpoint.microservices.bean.LimitConfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfigurations() {
        return new LimitConfiguration(1000, 1);
    }
}