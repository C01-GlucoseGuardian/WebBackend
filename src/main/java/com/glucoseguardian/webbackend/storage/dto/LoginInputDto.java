package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'input delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginInputDto extends RisultatoDto implements Serializable {

  private String email;
  private String password;
  private String newPassword;
  private String otp;

  /**
   * Costruttore completo.
   */
  public LoginInputDto(String email, String password, String newPassword, String otp) {
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

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public void validateChangePw(){
    Validate.notNull(password,"la vecchia password non può essere vuota");
    Validate.notNull(newPassword,"la nuova password non può essere vuota");
    Validate.isTrue(password.equals(newPassword),"la nuova password non può essere uguale alla vecchia passowrd");
  }
  public void validate() {
    Validate.notNull(email, "la mail non può essere assente");
    Pattern pattern2 = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+@(?:[a-zA-Z0-9-\\.]+)\\w$");
    Validate.isTrue(pattern2.matcher(email).matches(), "L'email non è valida");

    Validate.notNull(password, "la password non può essere assente");
  }
}
