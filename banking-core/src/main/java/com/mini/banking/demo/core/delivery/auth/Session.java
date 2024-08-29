// package com.mini.banking.demo.core.delivery.auth;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;
// import org.apache.commons.lang3.StringUtils;
// import org.springframework.core.env.Environment;
// import org.springframework.core.env.Profiles;
// import
// org.springframework.security.authentication.AnonymousAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;

// import com.mini.banking.demo.core.delivery.user.User;
// import com.mini.banking.demo.core.delivery.user.UserBackEnd;

// import java.util.Optional;

// @Component
// @RequiredArgsConstructor
// @Log4j2
// public class Session {

// private final UserBackEnd userBackEnd;
// private final Environment env;

// public Authentication getAuthentication() {
// return SecurityContextHolder.getContext().getAuthentication();
// }

// public Optional<User> getUser() {
// return userBackEnd.getByUsername("ADMIN");

// Authentication authentication = getAuthentication();
// Optional<User> optionalUser = Optional.empty();

// if (authentication == null) {
// log.warn("No authentication is found");

// } else if (authentication instanceof AnonymousAuthenticationToken) {
// log.warn("Anonymous user is found");

// } else {
// String username = authentication.getName();

// if (StringUtils.isBlank(username))
// log.error("Authentication username is null or blank!");
// else {
// return userBackEnd.getByUsername(username);
// }
// }

// return optionalUser;
// }

// public Optional<User> getUserOrAnonymous() {
// Optional<User> optionalUser = getUser();
// if (!optionalUser.isPresent()) {
// optionalUser = Optional.of(getAnonymousUser());
// }

// return optionalUser;
// }

// public User getAnonymousUser() {
// return userBackEnd.getAnonymousUser();
// }

// public User getUserOrThrow() {
// return getUser().orElseThrow(SessionUserNotFoundException::new);
// }
// }
