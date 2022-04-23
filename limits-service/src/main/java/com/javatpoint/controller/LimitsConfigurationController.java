package com.javatpoint.controller;

import com.javatpoint.limitsservice.Configuration;
import com.javatpoint.microservices.bean.LimitConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    @Autowired
    Configuration configuration;
    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}