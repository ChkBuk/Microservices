package com.mic.res.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestExample {
   // @RequestMapping(method = RequestMethod.GET,path="/hello")
    @GetMapping(path="/hello")
    public String showMessage(){
        return "Hellow..!";
    }
}
