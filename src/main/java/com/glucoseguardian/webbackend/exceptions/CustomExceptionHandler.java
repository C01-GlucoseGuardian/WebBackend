package com.glucoseguardian.webbackend.exceptions;

import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Custom exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler {

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

  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<RisultatoDto> handleIllegalArgumentException(
      IllegalArgumentException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public final ResponseEntity<RisultatoDto> handleNullPointerException(
      NullPointerException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DuplicatedEntityException.class)
  public final ResponseEntity<RisultatoDto> handleDuplicatedEntityException(
      DuplicatedEntityException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public final ResponseEntity<RisultatoDto> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto("Malformed JSON request");
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public final ResponseEntity<RisultatoDto> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex, WebRequest request) {
    RisultatoDto errorDetails = new RisultatoDto("Metodo non supportato");
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

}