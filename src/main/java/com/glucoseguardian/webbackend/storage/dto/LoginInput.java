package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Rappresenta l'input delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginInput {

  private String email;
  private String password;
  private String newPassword;
  private int otp;

  /**
   * Costruttore completo.
   */
  public LoginInput(String email, String password, String newPassword, int otp) {
    this.email = email;
    this.password = password;
    this.otp = otp;
    this.newPassword = newPassword;
  }

  public LoginInput() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getOtp() {
    return otp;
  }

  public void setOtp(int otp) {
    this.otp = otp;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
