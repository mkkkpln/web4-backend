package com.example.web4.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private long lifeTime;


    public String generateToken(UserDetails userDetails){

        Date issuedTime = new Date();
        Date expiredTime = new Date(issuedTime.getTime() + lifeTime);
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(issuedTime)
                .expiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Date getLifeTime(String token){
        return getAllClaimsFromToken(token).getExpiration();
    }

    public String getUserName(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getToken(HttpServletRequest request){
        String token = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if("Token".equals(cookie.getName())){
                token = cookie.getValue();
            }
        }
        return token;
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
