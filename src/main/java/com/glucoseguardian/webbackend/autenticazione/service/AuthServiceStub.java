package com.glucoseguardian.webbackend.autenticazione.service;

import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceInterface;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.TotpDto;
import com.glucoseguardian.webbackend.storage.entity.TipoUtente;
import org.springframework.stereotype.Service;

/**
 * Implementazione stab di Auth Service.
 */
@Service
public class AuthServiceStub implements AuthServiceInterface {

  @Override
  public LoginOutputDto login(String email, String password, Integer otp) {
    return new LoginOutputDto("CCCCDDDEER", TipoUtente.ADMIN.ordinal(), null, null);
  }

  @Override
  public boolean changePw(String email, String password, String newPassword, Integer otp) {
    return true;
  }

  @Override
  public TotpDto getTotpKey(String email, String password, Integer otp) {
    return new TotpDto();
  }

}
