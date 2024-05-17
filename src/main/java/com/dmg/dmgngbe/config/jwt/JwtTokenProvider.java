package com.dmg.dmgngbe.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  //todo fix
  private final String hostUrl = "https://damoang.net";
  private final String secret = "mysecret";
  private final int sessionExpiryDate = 1;


  public TokenViewModel createToken(UserDetailsImpl userDetails) {
    var now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plusHours(9);
    var expire = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        .plusDays(sessionExpiryDate);
    return TokenViewModel.builder()
        .accessToken(create(userDetails, now))
        .refreshToken(create(userDetails, expire))
        .build();
  }

  private String create(UserDetailsImpl userDetails, ZonedDateTime expirateDate) {
    try {
      var now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

      var jwt = JWT.create()
          .withIssuer(hostUrl)
          .withIssuedAt(now.withZoneSameInstant(ZoneOffset.UTC).toInstant())
          .withClaim("username", userDetails.getUsername())
          .withClaim("roles",
              StringUtils.collectionToDelimitedString(userDetails.getAuthorities(), ","));

      return jwt
          .withExpiresAt(expirateDate.withZoneSameInstant(ZoneOffset.UTC).toInstant())
          .sign(Algorithm.HMAC512(secret));
    } catch (JWTCreationException createEx) {
      return null;
    }
  }

  public void verify(String token) {
    JWTVerifier verifier = JWT.require(
        Algorithm.HMAC512(secret)).build();
    verifier.verify(token);
  }

  private Map<String, Claim> getClaims(String token) {
    DecodedJWT decodedJWT = tokenToJwt(token);
    return decodedJWT.getClaims();
  }

  public DecodedJWT tokenToJwt(String token) {
    try {
      return JWT.decode(token);
    } catch (JWTDecodeException decodeEx) {
      return null;
    }
  }

  public UserDetailsImpl getUserDetails(String token) {
    var claims = getClaims(token);
    return UserDetailsImpl.builder()
        .username(claims.get("username").asString())
        .authorities(
            new HashSet<>(AuthorityUtils.createAuthorityList(claims.get("roles").asString().split(","))))
        .build();
  }


}

