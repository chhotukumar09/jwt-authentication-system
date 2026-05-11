package com.project.Basic.Login.System.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/roles")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @GetMapping
    public String userRole()
    {
        return "hello user";
    }
    @GetMapping("/rbac")
    public String hello(){
        return "hello rbac";
    }
}
