package net.damoang.backend.config.jwt;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Builder
@Getter
@Setter
public class UserDetailsImpl {

  private String username;
  private Set<GrantedAuthority> authorities;


}
