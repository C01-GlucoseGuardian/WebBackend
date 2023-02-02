package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.application.service.JwtService;
import com.glucoseguardian.webbackend.exceptions.InvalidCredentialsException;
import com.glucoseguardian.webbackend.exceptions.NeedOtpException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.TotpDto;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementazione concreta di Auth Service.
 */
@Service
public class AuthServiceConcrete implements AuthServiceInterface {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UtenteDao utenteDao;

  @Autowired
  private JwtService jwtService;

  @Override
  public LoginOutputDto login(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException {
    Utente result = checkCredentials(email, password, otp);

    return new LoginOutputDto(result.getCodiceFiscale(), result.getTipoUtente().ordinal(), null,
        jwtService.generateToken(result));
  }

  @Override
  public boolean changePw(String email, String password, String newPassword, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException {
    Utente result = checkCredentials(email, password, otp);
    result.setPassword(passwordEncoder.encode(newPassword));
    utenteDao.save(result);
    return true;
  }

  @Override
  public TotpDto getTotpKey(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException {
    Utente result = checkCredentials(email, password, otp);
    String totpKey = Base32.random();
    result.setTotpKey(totpKey);
    utenteDao.save(result);
    return new TotpDto(totpKey);
  }

  private Utente checkCredentials(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException {
    Utente user = utenteDao.findByEmail(email).orElse(null);
    if (user == null) {
      throw new UserNotFoundException("Credenziali non valide");
    }
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException("Credenziali non valide");
    }
    if (user.getTotpKey() != null) {
      if (otp == null) {
        throw new NeedOtpException("Need otp");
      } else {
        Totp totp = new Totp(user.getTotpKey());
        if (!totp.verify(otp.toString())) {
          throw new InvalidCredentialsException("Credenziali non valide");
        }
      }
    }
    return user;
  }
}
