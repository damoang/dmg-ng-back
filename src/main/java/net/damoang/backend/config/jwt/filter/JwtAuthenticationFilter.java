package net.damoang.backend.config.jwt.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import net.damoang.backend.config.jwt.JwtAuthenticationToken;
import net.damoang.backend.config.jwt.JwtTokenProvider;
import net.damoang.backend.config.jwt.UserDetailsImpl;
import net.damoang.backend.config.security.AuthoritiesConstants;
import net.damoang.backend.config.security.NoOpAuthenficationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final JwtTokenProvider tokenProvider;

  public JwtAuthenticationFilter(RequestMatcher requestMatcher, JwtTokenProvider tokenProvider) {
    super(requestMatcher);
    this.tokenProvider = tokenProvider;
    setAuthenticationManager(new NoOpAuthenficationManager());
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) {
    String header = request.getHeader(AuthoritiesConstants.AUTH_HEADER);

    if (StringUtils.hasText(header) && header.startsWith(AuthoritiesConstants.TOKEN_TYPE)) {
      String token = header.replace(AuthoritiesConstants.TOKEN_TYPE, "").trim();

      try {
        tokenProvider.verify(token);
      } catch (TokenExpiredException verifyEx) {
        throw new AuthenticationServiceException("The access token expired");
      } catch (JWTVerificationException verifyEx) {
        throw new BadCredentialsException("Bad token");
      }

      UserDetailsImpl userDetails = tokenProvider.getUserDetails(token);

      var authentication = new JwtAuthenticationToken(userDetails, userDetails);
      return getAuthenticationManager().authenticate(authentication);
    }

    throw new AuthenticationServiceException("Empty Token");
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authResult);
    SecurityContextHolder.setContext(context);
    chain.doFilter(request, response);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    SecurityContextHolder.clearContext();
    getFailureHandler().onAuthenticationFailure(request, response, failed);
  }
}
