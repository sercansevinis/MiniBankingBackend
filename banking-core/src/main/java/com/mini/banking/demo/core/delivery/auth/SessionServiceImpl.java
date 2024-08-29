// package com.mini.banking.demo.core.delivery.auth;

// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// import com.mini.banking.demo.core.delivery.user.User;
// import com.mini.banking.demo.core.delivery.user.UserDto;
// import com.mini.banking.demo.core.delivery.user.UserDtoConverter;

// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class SessionServiceImpl implements SessionService {

// private final Session session;
// private final UserDtoConverter userDtoConverter;

// @Override
// public UserDto getUser() {
// Optional<User> optionalUser = session.getUser();

// return userDtoConverter.convertDataToDto(optionalUser.orElse(null));
// }

// @Override
// public UserDto getUserOrAnonymous() {
// Optional<User> optionalUser = session.getUserOrAnonymous();

// return getUserDtoWithUserGroup(optionalUser);
// }

// @Override
// public UserDto getUserOrThrow() {
// UserDto userDto = getUser();

// if (userDto == null)
// throw new SessionUserNotFoundException();

// return userDto;
// }

// }
