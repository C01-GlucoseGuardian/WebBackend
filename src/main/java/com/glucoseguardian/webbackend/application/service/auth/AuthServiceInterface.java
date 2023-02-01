package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.exceptions.InvalidCredentialsException;
import com.glucoseguardian.webbackend.exceptions.NeedOtpException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.TotpDto;

/**
 * Interfaccia Auth Service.
 */
public interface AuthServiceInterface {

  LoginOutputDto login(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException;

  boolean changePw(String email, String password, String newPassword, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException;

  TotpDto getTotpKey(String email, String password, Integer otp)
      throws UserNotFoundException, InvalidCredentialsException, NeedOtpException;

}
