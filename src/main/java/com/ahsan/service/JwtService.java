package com.ahsan.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService
{
    public static final String SECRETS = "357538782F4125442A472D4B6150645367566B59703373367639792442264528";



    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRETS);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    /*public String generateToken(String userName)
    {
        Map<String,Object> claims = new HashMap<>();

        return createToken(claims,userName);
    }

    public String createToken(Map<String,Object> claims,String userName)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }




    public Key getSignKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETS);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/
}
