package com.project.Basic.Login.System.Security;

import com.project.Basic.Login.System.Entity.User;
import com.project.Basic.Login.System.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Incoming request {}",request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = authUtil.validateToken(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found "));
            UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user,null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }
}
