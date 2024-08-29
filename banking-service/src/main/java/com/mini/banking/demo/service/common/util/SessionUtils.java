package com.mini.banking.demo.service.common.util;

import com.mini.banking.demo.service.common.entity.SessionMember;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.apache.mina.util.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
@Log4j2
public class SessionUtils {
    private final JwtEncoder jwtEncoder;

    private static final Set<String> INVALID_TOKENS_SET = new ConcurrentHashSet<>();
    private static final Map<String, SessionMember> ACTIVE_SESSIONS_MAP = new ConcurrentHashMap<>();
    private static final List<String> IP_HEADERS = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

    private void addInvalidAuthToken(String authToken) {
        INVALID_TOKENS_SET.add(authToken);
    }

    public boolean isInvalidAuthToken(String authToken) {
        if (INVALID_TOKENS_SET.contains(authToken))
            return true;

        /*
         * Valid JWT deciding belongs to session map.
         * That ensures JWT is not valid anymore after backend server restarted, etc.
         */
        return ACTIVE_SESSIONS_MAP.values().stream()
                .noneMatch(member -> member.getAuthToken().equals(authToken));
    }

    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        return IP_HEADERS.stream()
                .map(request::getHeader)
                .filter(Objects::nonNull)
                .filter(ip -> !ip.isEmpty() && !ip.equalsIgnoreCase("unknown"))
                .findFirst()
                .orElseGet(request::getRemoteAddr);
    }

public boolean hasUserValidActiveSession(String username) {
SessionMember sessionMember = ACTIVE_SESSIONS_MAP.get(username);
if (sessionMember == null)
return false;

Date sessionExpiryDate = addSecondToDate(sessionMember.getExpiryDate(), (int)
10000);
if (isAfter(sessionExpiryDate, new Date())) {
log.warn("Current user session hasn't expired yet. User: " + sessionMember);
return true;
}

log.debug(
String.format("Registered [%s] user session is expired. Removed from active
sessions map.", username));
unregisterUserSession(username);
return false;
}

    private void registerUserSession(String username, String authToken) {
        Date registrationDate = new Date();
        Date expiryDate = addSecondToDate(registrationDate, (int) 1000);
        SessionMember member = new SessionMember(username, authToken,
                registrationDate, expiryDate);
        ACTIVE_SESSIONS_MAP.put(username, member);
    }

    private void unregisterUserSession(String username) {
        ACTIVE_SESSIONS_MAP.remove(username);
    }

    public void invalidateSessionByUsername(String username) {
        SessionMember member = ACTIVE_SESSIONS_MAP.get(username);
        if (member != null) {
            log.debug("Previous session of login user is invalidated. Username: " +
                    username);
            unregisterUserSession(username);
            addInvalidAuthToken(member.getAuthToken());
        }
    }

    public List<SessionMember> listSessionMembers() {
        return new ArrayList<>(ACTIVE_SESSIONS_MAP.values());
    }

    public List<String> listSessionMembersUsernames() {
        return new ArrayList<>(ACTIVE_SESSIONS_MAP.keySet());
    }

    public void handleActionsAfterLogin(String username, String authToken) {
        registerUserSession(username, authToken);
    }

    public void handleActionsAfterLogout(String username, String authToken) {
        unregisterUserSession(username);
        addInvalidAuthToken(authToken);
    }

    public Jwt createJwtByIdToken(String idToken) throws ParseException {
        JWT parsedJwt = JWTParser.parse(idToken);

        Map<String, Object> headers = new LinkedHashMap<>(parsedJwt.getHeader().toJSONObject());
        Map<String, Object> claims = new HashMap<>();
        for (String key : parsedJwt.getJWTClaimsSet().getClaims().keySet()) {
            Object value = parsedJwt.getJWTClaimsSet().getClaims().get(key);
            if (key.equals("exp") || key.equals("iat")) {
                value = ((Date) value).toInstant();
            }
            claims.put(key, value);
        }

        return Jwt.withTokenValue(idToken)
                .headers(h -> h.putAll(headers))
                .claims(c -> c.putAll(claims))
                .build();
    }

    public String createAuthToken(Authentication authentication, String username) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("banking.api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(10000))
                .subject(username)
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public static Date addSecondToDate(Date date, int additionalSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, additionalSecond);
        return new Date(calendar.getTimeInMillis());
    }

    public static boolean isAfter(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.after(calendar2);
    }
}
