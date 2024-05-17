package com.dmg.dmgngbe.config.security;

import com.dmg.dmgngbe.config.jwt.JwtAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class SecurityUtils {

  private SecurityUtils() {
  }

  public static void logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
  }


  public static Optional<JwtAuthenticationToken> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      return Optional.empty();
    }
    try {
      if (authentication instanceof JwtAuthenticationToken) {
        return Optional.of((JwtAuthenticationToken) authentication);
      }
    } catch (Exception e) {
      return Optional.empty();
    }
    return Optional.empty();
  }


  public static List<String> getRolesString() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      return Collections.emptyList();
    }
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
  }

}
