// package com.mini.banking.demo.core.delivery.auth;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import
// org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
// import org.springframework.stereotype.Component;
// import org.apache.commons.lang3.StringUtils;

// import com.mini.banking.demo.core.delivery.user.User;
// import com.mini.banking.demo.core.delivery.user.UserDtoConverter;

// import javax.annotation.PostConstruct;
// import java.util.Comparator;
// import java.util.Date;
// import java.util.List;
// import java.util.Map;

// @Component
// @RequiredArgsConstructor
// @Log4j2
// public class CustomAuthenticationProvider implements AuthenticationProvider {

// private final CustomUserDetailsService userDetailsService;
// private final UserDtoConverter userDtoConverter;
// private final List<AuthenticationService> authServices;
// private AuthenticationService internalAuthService;

// private static final String ERROR_MSG = "Authentication failed";

// @PostConstruct
// private void init() {
// authServices.sort(Comparator.comparing(AuthenticationService::getOrder));
// internalAuthService = authServices.stream()
// .findFirst()
// .orElseThrow(() -> new RuntimeException("No internal authentication service
// implementation found!"));
// }

// @Override
// public Authentication authenticate(Authentication authentication) throws
// AuthenticationException {
// final String principal = authentication.getName();
// final String password = authentication.getCredentials().toString();

// if (StringUtils.isEmpty(principal)) {
// throw new BadCredentialsException(ERROR_MSG);
// }

// UserDetails userDetails;
// try {

// userDetails = userDetailsService.loadUserByUsername(principal);

// } catch (UsernameNotFoundException e) {
// log.error("Username is not found, authenticated failed! Principal: " +
// principal);
// throw new BadCredentialsException(ERROR_MSG);

// }

// User loginUser = (User) userDetails;

// if (!loginUser.isApplicationUser())
// throw new
// NotApplicationUserException(userDtoConverter.convertDataToDto(loginUser));

// if (loginUser.isInternalAuthSource() && !internalAuthService.isEnabled())
// throw new
// InternalAuthServiceDisabledException(userDtoConverter.convertDataToDto(loginUser));

// if (!loginUser.isAccountNonLocked()) {
// log.warn("Login user has reached max login attempt count");
// throw new
// TooManyLoginAttemptException(userDtoConverter.convertDataToDto(loginUser));

// } else {
// // If it passed timeout, then fresh start..
// if (loginUser.getAttemptCount() >= CoreConstants.USER_MAX_LOGIN_ATTEMPT_COUNT
// && loginUser.passedLockTimout()) {
// log.debug("Attempt count is set to zero, user lock is removed (timout is
// completed)");
// loginUser.setLockedDate(null);
// loginUser.setAttemptCount(0);
// }
// }

// boolean authenticated = false;
// if (props.isOidcAuthEnabled()) {
// authenticated = true;

// } else {
// for (AuthenticationService authService : authServices) {
// if (authService.isEnabled()) {
// if (log.isTraceEnabled())
// log.trace(authService + " Validating credentials is started... Username: "
// + userDetails.getUsername());

// authenticated = authService.authenticate(userDetails, password);

// if (log.isTraceEnabled())
// log.trace(authService + " Validating credentials is completed. Username: "
// + userDetails.getUsername() + " Authenticated: " + authenticated);

// if (authenticated)
// break;
// }
// }
// }

// if (authenticated) {
// loginUser.setAttemptCount(0);
// loginUser.setLockedDate(null);
// userDetailsService.updateUser(loginUser);

// log.debug("Authentication success. Username: " + userDetails.getUsername());
// return createSuccessfulAuthentication(authentication, userDetails);

// } else {
// loginUser.setAttemptCount(loginUser.getAttemptCount() + 1);

// if (loginUser.getAttemptCount() >=
// CoreConstants.USER_MAX_LOGIN_ATTEMPT_COUNT) // reached max attempt
// loginUser.setLockedDate(new Date());

// userDetailsService.updateUser(loginUser);

// log.debug("Authentication failed. Username: " + userDetails.getUsername());
// }

// throw new
// UserBadCredentialsException(userDtoConverter.convertDataToDto(loginUser),
// ERROR_MSG);
// }

// private UserDetails getUserDetailsOidc(Authentication authentication) throws
// OidcUserNotFoundException {
// if (!(authentication instanceof JwtAuthenticationToken)) {
// throw new
// NotSupportedAuthenticationTokenException(JwtAuthenticationToken.class.getSimpleName());
// }

// if (StringUtils.isBlank(props.getOidcIdTokenMappingAttribute()) ||
// props.getOidcUserAttribute() == null) {
// throw new MissingOidcConfigurationException();
// }

// Map<String, Object> tokenAttributes = ((JwtAuthenticationToken)
// authentication).getTokenAttributes();

// if (log.isTraceEnabled()) {
// log.trace("Retrieved Token Attributes: " + tokenAttributes);
// }

// Object tokenAttributeValue =
// tokenAttributes.get(props.getOidcIdTokenMappingAttribute());
// if (!(tokenAttributeValue instanceof String) || StringUtils.isBlank((String)
// tokenAttributeValue)) {
// throw new IdTokenAttributeValueNotFoundOrValidException(tokenAttributeValue);
// }

// UserDetails userDetails = null;
// String strTokenAttributeValue = (String) tokenAttributeValue;
// try {
// log.debug(String.format(
// "Oidc user is being determined... UserAttribute: %s MappingAttribute: %s
// TokenAttributeValue: %s",
// props.getOidcUserAttribute(), props.getOidcIdTokenMappingAttribute(),
// strTokenAttributeValue));

// switch (props.getOidcUserAttribute()) {
// case USERNAME:
// userDetails = userDetailsService.loadUserByUsername(strTokenAttributeValue);
// break;
// case TRADER:
// userDetails =
// userDetailsService.loadUserByTraderCode(strTokenAttributeValue);
// break;
// case EMAIL:
// userDetails = userDetailsService.loadUserByEmail(strTokenAttributeValue);
// break;
// case SUBJECT:
// userDetails =
// userDetailsService.loadUserByOidcSubject(strTokenAttributeValue);
// break;
// }
// } catch (UsernameNotFoundException e) {
// throw new OidcUserNotFoundException(props.getOidcUserAttribute(),
// strTokenAttributeValue);
// }

// return userDetails;
// }

// private Authentication createSuccessfulAuthentication(final Authentication
// authentication, final UserDetails user) {
// UsernamePasswordAuthenticationToken authToken = new
// UsernamePasswordAuthenticationToken(user.getUsername(),
// authentication.getCredentials(), user.getAuthorities());
// authToken.setDetails(authentication.getDetails());
// return authToken;
// }

// @Override
// public boolean supports(Class<?> authentication) {
// return authentication.equals(UsernamePasswordAuthenticationToken.class)
// || authentication.equals(JwtAuthenticationToken.class);
// }
// }
