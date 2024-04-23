package com.xim.ourcloset.services;

import com.xim.ourcloset.models.LoginModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtService {
    private final String SECRET_KEY="15fa2cf8f7e779ad47c2b4bb17adc869e955ebabbec96c485bc54d7e5bdf358c";

    public String extractUsername(String token){
        System.out.println("Token received dentro extractUsername: " + token); // Debug line
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        System.out.println("Token received dentro extractClaim: " + token); // Debug line
        Claims claims = extractAllClaims(token);
        System.out.println("Claims extracted: " + claims.toString() + claims.getId() ); // Aggiunto log per visualizzare le affermazioni
        T result = resolver.apply(claims);

        System.out.println("Result from resolver: " + result); // Aggiunto log per visualizzare il risultato di resolver.apply
        return result;
    }
    private Claims extractAllClaims(String token){
        System.out.println("Token received: " + token); // Debug line
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateToken(LoginModel user){
        String token = Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        System.out.println("Token  nel generate: " + token); // Debug line
        return token;
    }

    public boolean isValid(String token, UserDetails user){
        return !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSigninKey(){
        byte[]  keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
