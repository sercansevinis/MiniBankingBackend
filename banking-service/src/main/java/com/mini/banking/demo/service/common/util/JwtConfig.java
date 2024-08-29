// package com.mini.banking.demo.service.common.util;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

// import com.nimbusds.jose.jwk.JWK;
// import com.nimbusds.jose.jwk.JWKSet;
// import com.nimbusds.jose.jwk.RSAKey;
// import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
// import com.nimbusds.jose.jwk.source.JWKSource;
// import com.nimbusds.jose.proc.SecurityContext;

// @Configuration
// public class JwtConfig {

// @Bean
// public JwtEncoder jwtEncoder() {
// JWK jwk = new
// RSAKey.Builder(this.rsaPublicKey).privateKey(this.rsaPrivateKey).build();
// JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
// return new NimbusJwtEncoder(jwks);
// }

// @Bean
// public JwtDecoder jwtDecoder() {
// return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
// }
// }