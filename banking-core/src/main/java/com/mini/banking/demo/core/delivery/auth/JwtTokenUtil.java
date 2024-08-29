// package com.mini.banking.demo.core.delivery.auth;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;

// import java.util.*;

// import javax.servlet.http.HttpServletRequest;

// @Component
// public class JwtTokenUtil {

// private String secret = "your_secret_key";

// public String generateToken(UserDetails userDetails) {
// Map<String, Object> claims = new HashMap<>();
// return Jwts.builder()
// .setClaims(claims)
// .setSubject(userDetails.getUsername())
// .setIssuedAt(new Date(System.currentTimeMillis()))
// .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //
// 10 hours validity
// .signWith(SignatureAlgorithm.HS512, secret).compact();
// }

// public Boolean validateToken(String token) {
// return !isTokenExpired(token);
// }

// private Boolean isTokenExpired(String token) {
// final Date expiration = getExpirationDateFromToken(token);
// return expiration.before(new Date());
// }

// public String getUsernameFromToken(String token) {
// return
// Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
// }

// public String getTokenFromRequest(HttpServletRequest request) {
// String bearerToken = request.getHeader("Authorization");
// if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
// return bearerToken.substring(7);
// }
// return null;
// }
// }
