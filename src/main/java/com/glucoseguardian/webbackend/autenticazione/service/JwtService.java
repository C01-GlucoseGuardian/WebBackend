package com.glucoseguardian.webbackend.autenticazione.service;

import com.glucoseguardian.webbackend.storage.entity.TipoUtente;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * Servizio che genera e valida token Jwt. Utilizza HMAC-SHA256. Basato su {@link Jwts}.
 */
@Service
public class JwtService {

  private static final String TIPO_UTENTE = "tip";
  // 30 days in seconds
  private static final int TOKEN_VALIDITY = 86_400 * 30;
  @Value("${jwt.secret}")
  private String secret;

  public @Nullable String getUsername(@NonNull String token) {
    return getClaim(token, Claims::getSubject);
  }

  private <T> @Nullable T getClaim(@NonNull String token,
      @NonNull Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private <T> @Nullable T getClaim(@NonNull String token, @NonNull String key,
      @NonNull Class<T> type) {
    final Claims claims = extractAllClaims(token);
    return claims.get(key, type);
  }

  /**
   * Genera un token jwt usando SHA256-HMAC, con i campi NotBefore, IssuedAt, Expiration,
   * TipoUtente, Subject.
   */
  public @NonNull String generateToken(@NonNull Utente userDetails) {
    return generateToken(userDetails, new Date());
  }

  /**
   * Genera un token jwt usando SHA256-HMAC, con i campi NotBefore, IssuedAt, Expiration,
   * TipoUtente, Subject, utilizzando il currentTime passato in input.
   */
  public @NonNull String generateToken(@NonNull Utente userDetails, Date currentTime) {
    Date notBefore = DateUtils.addDays(currentTime, -1);
    Date expirationTime = DateUtils.addSeconds(currentTime, TOKEN_VALIDITY);
    return Jwts.builder()
        .setClaims(Map.of(TIPO_UTENTE, userDetails.getTipoUtente().name()))
        .setSubject(userDetails.getUsername())
        .setNotBefore(notBefore)
        .setIssuedAt(currentTime)
        .setExpiration(expirationTime)
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * Controlla se un token è valido. Un token è valido se appartiene a un utente con account
   * abilitato presente nel database, se il suo tipo corrisponde a quello nel token, se il token non
   * è scaduto, se il token non è nel futuro.
   */
  public boolean isTokenValid(@NonNull String token, @NonNull Utente userDetails) {
    try {
      String username = getUsername(token);
      TipoUtente tipoUtente = getTipoUtente(token);

      return userDetails.getUsername().equals(username)
          && userDetails.getTipoUtente().equals(tipoUtente)
          && !isTokenExpired(token)
          && !isTokenInFuture(token)
          && userDetails.isEnabled();
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  private boolean isTokenExpired(@NonNull String token) {
    Date expiration = getClaim(token, Claims::getExpiration);
    if (expiration != null) {
      return expiration.before(new Date());
    }
    return true;
  }

  private boolean isTokenInFuture(@NonNull String token) {
    Date future = getClaim(token, Claims::getExpiration);
    if (future != null) {
      return future.before(new Date());
    }
    return true;
  }

  private @NonNull TipoUtente getTipoUtente(@NonNull String token) {
    return TipoUtente.valueOf(getClaim(token, TIPO_UTENTE, String.class));
  }

  private @NonNull Claims extractAllClaims(@NonNull String token) {
    try {
      return Jwts
          .parserBuilder()
          .setSigningKey(getSignInKey())
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (ExpiredJwtException | PrematureJwtException e) {
      // Get Claims from expired token
      return e.getClaims();
    }
  }

  private @NonNull Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
