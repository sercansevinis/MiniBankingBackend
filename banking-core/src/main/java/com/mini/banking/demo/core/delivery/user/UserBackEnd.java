// package com.mini.banking.demo.core.delivery.user;

// import java.util.Optional;

// // import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;

// import lombok.RequiredArgsConstructor;

// @Component
// @RequiredArgsConstructor
// public class UserBackEnd {
// private final UserRepository userRepository;
// private static User ANONYMOUS_USER_INSTANCE = null;

// public Optional<User> getById(int id) {
// Optional<User> optionalUser = userRepository.findById(id);

// return optionalUser;
// }

// public Optional<User> getByUsername(String username) {
// Optional<User> optionalUser = userRepository.findByUsername(username);
// // if (optionalUser == null) {
// // throw new UsernameNotFoundException("User not found with username: " +
// // username);
// // }
// return optionalUser;
// }

// public void save(User user) {
// int id = user.getId();
// userRepository.saveAndFlush(user);
// }

// public void delete(int id) {
// userRepository.deleteById(id);
// }

// public synchronized User getAnonymousUser() {
// if (ANONYMOUS_USER_INSTANCE == null) {
// ANONYMOUS_USER_INSTANCE = getByUsername("ANONYMOUS")
// .orElseThrow(() -> new UserNotFoundException("ANONYMOUS"));
// }

// return ANONYMOUS_USER_INSTANCE;
// }

// }
