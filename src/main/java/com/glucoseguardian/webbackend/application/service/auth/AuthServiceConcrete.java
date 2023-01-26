package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConcrete implements AuthServiceInterface {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AdminDao adminDao;

  @Autowired
  private DottoreDao dottoreDao;

  @Autowired
  private PazienteDao pazienteDao;

  @Autowired
  private TutoreDao tutoreDao;

  @Override
  public LoginOutputDto login(String email, String password, Integer otp) {
    Utente result = findByEmail(email);
    if (result != null) {
      if (passwordEncoder.matches(password, result.getPassword())) {
        // TODO: Check if there's an OTP
        return new LoginOutputDto(result.getCodiceFiscale(), result.getTipoUtente().ordinal(),
            false);
      }
    }
    // TODO: Add a specific exception instead of Null
    return null;
  }


  private @Nullable Utente findByEmail(@NotNull String email) {
    Utente result = adminDao.findByEmail(email).orElse(null);
    if (result != null) {
      return result;
    }

    result = dottoreDao.findByEmail(email).orElse(null);
    if (result != null) {
      return result;
    }

    result = pazienteDao.findByEmail(email).orElse(null);
    if (result != null) {
      return result;
    }

    result = tutoreDao.findByEmail(email).orElse(null);
    if (result != null) {
      return result;
    }

    return null;
  }

}
