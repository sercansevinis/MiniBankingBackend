// package com.mini.banking.demo.core.delivery.auth;

// import lombok.RequiredArgsConstructor;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;

// import com.mini.banking.demo.core.delivery.user.User;
// import com.mini.banking.demo.core.delivery.user.UserBackEnd;

// @Component
// @RequiredArgsConstructor
// public class CustomUserDetailsService implements UserDetailsService {
// private final UserBackEnd userBackEnd;
// private static final String INVALID_CREDENTIALS = "invalid credentials";

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// return userBackEnd.getByUsername(username)
// .orElseThrow(() -> new UsernameNotFoundException(INVALID_CREDENTIALS));
// }

// public void updateUser(User user) {
// userBackEnd.save(user);
// }
// }
