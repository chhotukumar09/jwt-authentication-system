package com.project.Basic.Login.System.Controller;

import com.project.Basic.Login.System.DTO.JwtToken;
import com.project.Basic.Login.System.DTO.LoginDTO;
import com.project.Basic.Login.System.DTO.UserDTO;
import com.project.Basic.Login.System.Security.AuthService;
import com.project.Basic.Login.System.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class UserLogin {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody UserDTO userDTO) {
        String response = userService.signupUser(userDTO);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/login")
    public JwtToken Login(@RequestBody LoginDTO loginDTO){
        return authService.Login(loginDTO);


    }


}
