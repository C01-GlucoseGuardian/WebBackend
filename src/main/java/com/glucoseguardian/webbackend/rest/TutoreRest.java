package com.glucoseguardian.webbackend.rest;

import com.glucoseguardian.webbackend.application.service.feedback.AbstractFeedbackService;
import com.glucoseguardian.webbackend.application.service.feedback.FeedbackServiceInterface;
import com.glucoseguardian.webbackend.application.service.tutore.AbstractTutoreService;
import com.glucoseguardian.webbackend.application.service.tutore.TutoreServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * implementation TutoreRest.
 */
public class TutoreRest {

  @Autowired
  @Qualifier("finalTutoreService")
  private AbstractTutoreService tutoreService;

  /**
   * Metodo che gestisce il servizio Tutore findByCodiceFiscale.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFeedback(
      @RequestBody TutoreDto input) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      TutoreDto dto = getService().findByCodiceFiscale(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del Tutore"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Feedback findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTutoresByPaziente(
      @RequestBody PazienteDto input) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      ListDto<TutoreDto> dto = getService().findByPaziente(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del tutore"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio save tutore.
   */

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveTutore(
      @RequestBody TutoreDto input) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    boolean result = false;
    try {
      result = getService().save(input);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
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