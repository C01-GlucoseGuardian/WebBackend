package com.glucoseguardian.webbackend.exceptions;

import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<RisultatoDto> handleAccessDeniedException(AccessDeniedException ex,
      WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto("Utente non autorizzato");
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<RisultatoDto> handleUserNotFoundException(UserNotFoundException ex,
      WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<RisultatoDto> handleEntityNotFoundException(
      EntityNotFoundException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public final ResponseEntity<RisultatoDto> handleInvalidCredentialsException(
      InvalidCredentialsException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccountDisabledException.class)
  public final ResponseEntity<RisultatoDto> handleAccountDisabledException(
      AccountDisabledException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NeedOtpException.class)
  public final ResponseEntity<RisultatoDto> handleNeedOtpException(
      NeedOtpException ex, WebRequest request) {
    LoginOutputDto errorDetails = new LoginOutputDto();
    errorDetails.setNeedOtp(true);
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }

}