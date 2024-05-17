package net.damoang.backend.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseSecurityHandler implements AuthenticationSuccessHandler,
    AuthenticationFailureHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) {
    log.info("authentication: {}", authentication);

  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException exception) {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    try (var printWriter = response.getWriter()) {
      printWriter.print(
          ("{\"result\":\"invalid_token\",\"description\":\"" + exception.getMessage() + "\"}"));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }
}
