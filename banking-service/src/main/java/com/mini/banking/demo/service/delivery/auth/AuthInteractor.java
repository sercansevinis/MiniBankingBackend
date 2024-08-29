// package com.mini.banking.demo.service.delivery.auth;

// import java.text.ParseException;

// import org.hibernate.mapping.Map;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.jwt.Jwt;
// import
// org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
// import org.springframework.stereotype.Component;

// import com.mini.banking.demo.core.delivery.auth.CustomUserDetailsService;
// // import com.mini.banking.demo.core.delivery.auth.JwtTokenUtil;
// import com.mini.banking.demo.core.delivery.user.UserDto;
// import com.mini.banking.demo.core.delivery.user.UserService;
// import com.mini.banking.demo.service.common.entity.AuthRequest;
// import com.mini.banking.demo.service.common.entity.AuthResponse;
// import com.mini.banking.demo.service.common.entity.Response;
// import com.mini.banking.demo.service.common.presenter.CommonObjectPresenter;
// import com.mini.banking.demo.service.common.util.SessionUtils;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Component
// @RequiredArgsConstructor
// @Log4j2
// public class AuthInteractor {
// private final AuthenticationManager authenticationManager;
// private final UserService userService;
// // private final JwtTokenUtil jwtTokenUtil;
// // private final CustomUserDetailsService userDetailsService;
// private final SessionUtils sessionUtils;
// private final CommonObjectPresenter<AuthResponse> loginPresenter;

// public ResponseEntity<Response<AuthResponse>> login(AuthRequest request) {
// Authentication authentication = null;
// authentication = authenticationManager
// .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
// request.getPassword()));
// AuthResponse authResponse = null;
// UserDto userDto = null;

// if (authentication != null) {
// try {
// String username = authentication.getPrincipal().toString();
// String authToken = sessionUtils.createAuthToken(authentication, username);

// userDto = userService.findByUsername(username);

// authResponse = AuthResponse.builder()
// .authToken(authToken)
// .user(userDto)
// .build();

// sessionUtils.handleActionsAfterLogin(username, authToken);

// } catch (Exception e) {
// log.error("Login is failed! (Internal Error)", e);
// loginPresenter.prepareFailedView("Login failed! (Internal Error)");
// }
// }

// log.info("Login is completed. Username: " + (userDto != null ?
// userDto.getUsername() : null));

// return loginPresenter.prepareSuccessView(authResponse,
// "rest.authentication.login");
// }

// }