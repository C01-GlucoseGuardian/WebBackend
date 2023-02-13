package com.glucoseguardian.webbackend.autenticazione.rest;

import com.glucoseguardian.webbackend.autenticazione.service.AbstractAuthService;
import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceConcrete;
import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceInterface;
import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.storage.dto.LoginInputDto;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TotpDto;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Questo controller è responsabile dei servizi offerti da {@link AuthServiceConcrete}.
 */
@RestController
@RequestMapping("auth")
public class AuthRest {

  @Autowired
  private AbstractAuthService authService;
  @Autowired
  private Utils utils;

  /**
   * Metodo che gestisce il servizio login.
   */
  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> login(
      @RequestBody LoginInputDto input) throws Exception {

    input.validate();
    LoginOutputDto dto = getService().login(input.getEmail(), input.getPassword(), input.getOtp());
    ResponseEntity<RisultatoDto> response = new ResponseEntity<>(dto, HttpStatus.OK);

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio cambio password.
   */
  @PostMapping(value = "/changePw", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> changePw(
      @RequestBody LoginInputDto input) throws Exception {

    input.validateChangePw();
    getService().changePw(getAuthentication().getName(), input.getPassword(),
        input.getNewPassword(), input.getOtp());
    ResponseEntity<RisultatoDto> response = new ResponseEntity<>(
        new RisultatoDto("Modifica effettuata con successo"), HttpStatus.OK);

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio configura otp.
   */
  @PostMapping(value = "/getTotpKey", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTotpKey(
      @RequestBody LoginInputDto input) throws Exception {

    input.validatePw();
    TotpDto dto = getService().getTotpKey(getAuthentication().getName(), input.getPassword(),
        input.getOtp());
    ResponseEntity<RisultatoDto> response = new ResponseEntity<>(dto, HttpStatus.OK);

    return CompletableFuture.completedFuture(response);
  }

  private Authentication getAuthentication() {
    return utils.getAuthentication();
  }

  private AuthServiceInterface getService() {
    return authService.getImplementation();
  }
}

