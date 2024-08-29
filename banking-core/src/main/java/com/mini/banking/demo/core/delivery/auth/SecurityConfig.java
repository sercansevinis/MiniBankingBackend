// package com.mini.banking.demo.core.delivery.auth;

// import org.springframework.context.annotation.Bean;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import lombok.RequiredArgsConstructor;

// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

// private final CustomUserDetailsService userDetailsService;

// private JwtAuthenticationFilter jwtAuthenticationFilter;

// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.userDetailsService(userDetailsService).passwordEncoder(new
// BCryptPasswordEncoder());
// }

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http.csrf().disable()
// .authorizeRequests().antMatchers("/api/auth/**").permitAll()
// .anyRequest().authenticated()
// .and()
// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// http.addFilterBefore(jwtAuthenticationFilter,
// UsernamePasswordAuthenticationFilter.class);
// }

// @Bean
// @Override
// public AuthenticationManager authenticationManagerBean() throws Exception {
// return super.authenticationManagerBean();
// }
// }