package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.glucoseguardian.webbackend.autenticazione.service.JwtService;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

  @InjectMocks
  JwtService jwtService;


  /**
   * Jwt token, username diverso.
   */
  @Test
  public void testTokenValid1() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente);
    utente.setEmail("dottore2@glucoseguardian.it");
    boolean valid = jwtService.isTokenValid(token, utente);
    assertFalse(valid);
  }

  /**
   * Jwt token, tipo utente diverso.
   */
  @Test
  public void testTokenValid2() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");
    Admin utente2 = new Admin();
    utente2.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente);
    boolean valid = jwtService.isTokenValid(token, utente2);
    assertFalse(valid);
  }

  /**
   * Jwt token, token scaduto
   */
  @Test
  public void testTokenValid3() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente,
        Date.from(Instant.parse("2010-12-03T10:15:30.00Z")));

    boolean valid = jwtService.isTokenValid(token, utente);
    assertFalse(valid);
  }

  /**
   * Jwt token, token nel futuro
   */
  @Test
  public void testTokenValid4() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente,
        Date.from(Instant.parse("2100-12-03T10:15:30.00Z")));

    boolean valid = jwtService.isTokenValid(token, utente);
    assertFalse(valid);
  }

  /**
   * Jwt token, different signature
   */
  @Test
  public void testTokenValid5() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente);

    genNewSecret();

    boolean valid = jwtService.isTokenValid(token, utente);
    assertFalse(valid);
  }

  /**
   * Jwt token, account disabled
   */
  @Test
  public void testTokenValid6() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");

    String token = jwtService.generateToken(utente);

    boolean valid = jwtService.isTokenValid(token, utente);
    assertFalse(valid);
  }

  /**
   * Jwt token, ok
   */
  @Test
  public void testTokenValid7() {
    Dottore utente = new Dottore();
    utente.setEmail("dottore@glucoseguardian.it");
    utente.setStato(1);

    String token = jwtService.generateToken(utente);

    boolean valid = jwtService.isTokenValid(token, utente);
    assertTrue(valid);
  }

  @BeforeEach
  void genNewSecret() {
    ReflectionTestUtils.setField(jwtService, "secret", Base64.getEncoder().encodeToString(
        RandomStringUtils.random(64).getBytes()));
  }

}


