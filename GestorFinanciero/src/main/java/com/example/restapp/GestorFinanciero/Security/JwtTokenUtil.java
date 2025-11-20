package com.example.restapp.GestorFinanciero.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L; 

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails, Integer idUsuario) {
        Map<String, Object> claims = new HashMap<>();

        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        claims.put("role", roles);

        if (idUsuario != null) {
            claims.put("idUsuario", idUsuario);
        }

        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, null);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        SecretKey key = getSigningKey();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String normalizeToken(String token) {
        if (token == null) return null;
        token = token.trim();
        if (token.startsWith("Bearer ")) return token.substring(7).trim();
        return token;
    }
    public Claims getAllClaimsFromToken(String token) {
        token = normalizeToken(token);
        SecretKey key = getSigningKey();

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String correo = getUsernameFromToken(token);
            return (correo != null &&
                    correo.equalsIgnoreCase(userDetails.getUsername()) &&
                    !isTokenExpired(token));
        } catch (Exception ex) {
            return false;
        }
    }

    public Integer getIdUsuarioFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Object idClaim = claims.get("idUsuario");
        if (idClaim == null) return null;

        if (idClaim instanceof Integer) return (Integer) idClaim;
        if (idClaim instanceof Long) return ((Long) idClaim).intValue();
        if (idClaim instanceof String) return Integer.parseInt((String) idClaim);
        return null;
    }
}
