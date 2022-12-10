package com.mic.res.controller;

import javax.naming.Name;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name1;
import com.fasterxml.jackson.core.sym.Name3;
@RestController
public class RestExample {
   // @RequestMapping(method = RequestMethod.GET,path="/hello")
    @GetMapping(path="/hello")
    public String showMessage(){
        return "Hellow..!";
    }
    @GetMapping(path="/hello-bean")
    public HelloBean showMessageBean(){
        return new HelloBean("Hellow Bean");
    }

    @GetMapping(path="/hello-bean/path_variables/{name}")
    public HelloBean showMessagePathVariable(@PathVariable String name){
        return new HelloBean(String.format("Hi %s",name));
    }
   
   
}
