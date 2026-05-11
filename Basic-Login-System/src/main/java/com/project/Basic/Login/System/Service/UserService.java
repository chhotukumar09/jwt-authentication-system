package com.project.Basic.Login.System.Service;

import com.project.Basic.Login.System.DTO.UserDTO;
import com.project.Basic.Login.System.Entity.User;
import com.project.Basic.Login.System.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public String signupUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        String encodePass = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePass);
        userRepository.save(user);

        return "Success signUp";

    }

}
