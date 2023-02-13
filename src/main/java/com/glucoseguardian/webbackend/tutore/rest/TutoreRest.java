package com.glucoseguardian.webbackend.tutore.rest;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.tutore.service.AbstractTutoreService;
import com.glucoseguardian.webbackend.tutore.service.TutoreServiceInterface;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * implementation TutoreRest.
 */
@RestController
@RequestMapping("tutore")
public class TutoreRest {

  @Autowired
  private AbstractTutoreService tutoreService;

  /**
   * Metodo che gestisce il servizio Tutore findByCodiceFiscale.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFeedback(
      @RequestBody CodiceFiscaleDto codiceFiscaleTutore) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    codiceFiscaleTutore.validate();
    try {
      TutoreDto dto = getService().findByCodiceFiscale(codiceFiscaleTutore.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del Tutore"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Feedback findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTutoresByPaziente(
      @RequestBody CodiceFiscaleDto codiceFiscalePaziente) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    codiceFiscalePaziente.validate();
    try {
      ListDto<TutoreDto> dto = getService()
          .findByPaziente(codiceFiscalePaziente.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del tutore"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio save tutore.
   */

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveTutore(
      @RequestBody TutoreDto input) throws UserNotFoundException, DuplicatedEntityException {

    input.validate();
    boolean result = false;
    try {
      result = getService().save(input);
    } catch (UserNotFoundException | AccessDeniedException | DuplicatedEntityException ex) {
      throw ex;
    } catch (Exception ex) {
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Tutore inserito correttamente"), HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento del Tutore"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private TutoreServiceInterface getService() {
    return tutoreService.getImplementation();
  }
}