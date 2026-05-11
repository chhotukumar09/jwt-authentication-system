package com.project.Basic.Login.System.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class helloController {
    @GetMapping("/hello")
    public String hellocon(){
        return "Hello Controller";
    }
}
