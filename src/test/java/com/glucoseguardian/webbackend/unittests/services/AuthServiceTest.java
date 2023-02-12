package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceConcrete;
import com.glucoseguardian.webbackend.autenticazione.service.JwtService;
import com.glucoseguardian.webbackend.exceptions.AccountDisabledException;
import com.glucoseguardian.webbackend.exceptions.InvalidCredentialsException;
import com.glucoseguardian.webbackend.exceptions.NeedOtpException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.Optional;
import org.jboss.aerogear.security.otp.Totp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings({"rawtypes", "unchecked"}) // Optional<extends ...> doesn't work with mockito
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  @Mock
  UtenteDao utenteDao;
  @Mock
  PasswordEncoder passwordEncoder;
  @Mock
  JwtService jwtService;
  @InjectMocks
  AuthServiceConcrete authServiceConcrete;

  /**
   * Login, utente non esistente
   */
  @Test
  public void testLogin1() {
    assertThrows(UserNotFoundException.class,
        () -> authServiceConcrete.login("admin2222@2222.it", "password", null));
  }

  /**
   * Login, password non valida
   */
  @Test
  public void testLogin2() {
    Optional result = Optional.of(new Dottore());
    when(utenteDao.findByEmail("admin@glucoseguardian.it")).thenReturn(result);

    assertThrows(InvalidCredentialsException.class,
        () -> authServiceConcrete.login("admin@glucoseguardian.it", "password2", null));
  }

  /**
   * Login, password valida need otp
   */
  @Test
  public void testLogin3() {
    Paziente paziente = new Paziente();
    paziente.setPassword(passwordEncoder.encode("password"));
    paziente.setTotpKey("KEVK3WTVT36MH7BG");
    Optional result = Optional.of(paziente);

    when(utenteDao.findByEmail("paziente2@glucoseguardian.it")).thenReturn(result);
    when(passwordEncoder.matches(any(), any())).thenReturn(true);

    assertThrows(NeedOtpException.class,
        () -> authServiceConcrete.login("paziente2@glucoseguardian.it", "password", null));
  }

  /**
   * Login, password valida otp non valido
   */
  @Test
  public void testLogin4() {
    Paziente paziente = new Paziente();
    paziente.setPassword(passwordEncoder.encode("password"));
    paziente.setTotpKey("KEVK3WTVT36MH7BG");
    Optional result = Optional.of(paziente);

    when(utenteDao.findByEmail("paziente2@glucoseguardian.it")).thenReturn(result);
    when(passwordEncoder.matches(any(), any())).thenReturn(true);

    assertThrows(InvalidCredentialsException.class,
        () -> authServiceConcrete.login("paziente2@glucoseguardian.it", "password", "0000"));
  }

  /**
   * Login, password valida otp valido
   */
  @Test
  public void testLogin5()
      throws UserNotFoundException, NeedOtpException, InvalidCredentialsException, AccountDisabledException {
    Paziente paziente = new Paziente();
    paziente.setCodiceFiscale("MRTLDA01L55C514M");
    paziente.setPassword(passwordEncoder.encode("password"));
    paziente.setTotpKey("KEVK3WTVT36MH7BG");
    Optional pazienteOptional = Optional.of(paziente);

    when(utenteDao.findByEmail("paziente2@glucoseguardian.it")).thenReturn(pazienteOptional);
    when(passwordEncoder.matches(any(), any())).thenReturn(true);

    LoginOutputDto oracolo = new LoginOutputDto();
    oracolo.setIdUtente(paziente.getCodiceFiscale());
    oracolo.setTipoUtente(paziente.getTipoUtente().ordinal());
    oracolo.setToken(null);

    LoginOutputDto result = authServiceConcrete.login("paziente2@glucoseguardian.it", "password",
        new Totp(paziente.getTotpKey()).now());
    assertEquals(oracolo, result);
  }

  /**
   * Login, password valida otp non richiesto, account disabilitato
   */
  @Test
  public void testLogin6() {
    Dottore dottore = new Dottore();
    dottore.setPassword(passwordEncoder.encode("password"));
    Optional dottoreOptiona = Optional.of(dottore);

    when(utenteDao.findByEmail("dottore2@glucoseguardian.it")).thenReturn(dottoreOptiona);
    when(passwordEncoder.matches(any(), any())).thenReturn(true);

    assertThrows(AccountDisabledException.class,
        () -> authServiceConcrete.login("dottore2@glucoseguardian.it", "password", null));
  }
}
