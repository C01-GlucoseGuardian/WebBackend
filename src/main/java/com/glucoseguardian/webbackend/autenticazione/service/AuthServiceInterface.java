package com.glucoseguardian.webbackend.autenticazione.service;

import com.glucoseguardian.webbackend.exceptions.AccountDisabledException;
import com.glucoseguardian.webbackend.exceptions.InvalidCredentialsException;
import com.glucoseguardian.webbackend.exceptions.NeedOtpException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.TotpDto;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Interfaccia Auth Service.
 */
public interface AuthServiceInterface {

  @PreAuthorize("permitAll()")
  LoginOutputDto login(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException,
      AccountDisabledException;

  @PreAuthorize("isAuthenticated()")
  boolean changePw(String email, String password, String newPassword, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException;

  @PreAuthorize("isAuthenticated()")
  TotpDto getTotpKey(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException;

}
