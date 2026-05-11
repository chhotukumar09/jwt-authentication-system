package com.project.Basic.Login.System.Security;

import com.project.Basic.Login.System.DTO.JwtToken;
import com.project.Basic.Login.System.DTO.LoginDTO;
import com.project.Basic.Login.System.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    public JwtToken Login(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
              new  UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
              )

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateJWT(user);
        return new JwtToken(token , user.getId());

    }
}
