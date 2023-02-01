package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta l'input delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginInputDto extends RisultatoDto implements Serializable {

  private String email;
  private String password;
  private String newPassword;
  private Integer otp;

  /**
   * Costruttore completo.
   */
  public LoginInputDto(String email, String password, String newPassword, Integer otp) {
    this.email = email;
    this.password = password;
    this.otp = otp;
    this.newPassword = newPassword;
  }

  public LoginInputDto() {
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

  public Integer getOtp() {
    return otp;
  }

  public void setOtp(Integer otp) {
    this.otp = otp;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
