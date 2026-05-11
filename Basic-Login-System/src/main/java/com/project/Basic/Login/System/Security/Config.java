package com.project.Basic.Login.System.Security;

import com.project.Basic.Login.System.Entity.User;
import com.project.Basic.Login.System.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config {
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf->csrf.disable())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/user/roles/**").hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
//                .logout(logout -> logout.logoutUrl("/logout"))
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

//       .formLogin(withDefaults())


    return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Service
    @RequiredArgsConstructor
   public class CustomUser implements UserDetailsService{
        private final UserRepository userRepository;
       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userRepository.findByUsername(username).orElseThrow (()-> new UsernameNotFoundException("User not found"));
           return user;
       }
   }

   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
   }
}
