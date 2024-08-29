// package com.mini.banking.demo.service.delivery.auth;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.mini.banking.demo.core.delivery.auth.CustomUserDetailsService;
// import com.mini.banking.demo.service.common.entity.AuthRequest;
// import com.mini.banking.demo.service.common.entity.AuthResponse;
// import com.mini.banking.demo.service.common.entity.Response;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// @Validated
// @RequestMapping("/api/auth")
// public class AuthController {
// private final AuthInteractor interactor;

// @PostMapping("/login")
// public ResponseEntity<Response<AuthResponse>> login(@RequestBody @Valid
// AuthRequest request) {
// return interactor.login(request);
// }
// }
