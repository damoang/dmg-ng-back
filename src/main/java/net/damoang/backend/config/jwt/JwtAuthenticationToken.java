package net.damoang.backend.config.jwt;

import java.io.Serial;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

  @Serial
  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private final Object principal;
  private Object credentials;

  public JwtAuthenticationToken(Object principal, UserDetailsImpl userDetails) {
    super(userDetails.getAuthorities());
    this.principal = principal;
    this.credentials = true;
    super.setAuthenticated(true);
  }

  public void setAuthenticated(boolean isAuthenticated) {
    if (isAuthenticated) {
      throw new IllegalArgumentException(
          "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }

    super.setAuthenticated(false);
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
    credentials = null;
  }

}
