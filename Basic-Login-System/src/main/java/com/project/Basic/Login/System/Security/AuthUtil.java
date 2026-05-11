package com.project.Basic.Login.System.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class AuthUtil {
 @Value("${jwt.secrete}")
 private String secreteKey ;
 private Key getKey(){
  return Keys.hmacShaKeyFor(secreteKey.getBytes(StandardCharsets.UTF_8));
 }
 public String generateJWT(UserDetails userDetails){
  return Jwts.builder()
          .setSubject(userDetails.getUsername())
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*10))
          .signWith(getKey())
          .compact();
 }

 public String validateToken(String token){
    Claims claims = Jwts.parser()
            .setSigningKey(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    return claims.getSubject();

 }


}
