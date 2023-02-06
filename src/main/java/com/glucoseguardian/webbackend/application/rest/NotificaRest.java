package com.glucoseguardian.webbackend.application.rest;

import com.glucoseguardian.webbackend.application.service.feedback.AbstractFeedbackService;
import com.glucoseguardian.webbackend.application.service.feedback.FeedbackServiceInterface;
import com.glucoseguardian.webbackend.application.service.notifica.AbstractNotificaService;
import com.glucoseguardian.webbackend.application.service.notifica.NotificaServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AdminDto;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * implementation NotificaRest.
 */
@RestController
@RequestMapping("notifica")
public class NotificaRest {

  @Autowired
  @Qualifier("finalNotificaService")
  private AbstractNotificaService notificaService;

  /**
   * Metodo che gestisce il servizio Notifica findById.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getNotifica(
      @RequestBody NotificaDto input) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      NotificaDto dto = getService().findById(input.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Notifica findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getNotificaByPaziente(
      @RequestBody PazienteDto input) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      ListDto<NotificaDto> dto = getService().findByPaziente(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Feedback findByDottore.
   */

  @PostMapping(value = "/getByDottore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getNotificaByDottore(
      @RequestBody DottoreDto input) throws Exception {
    ResponseEntity<RisultatoDto> response;
    try {
      ListDto<NotificaDto> dto = getService().findByDottore(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Notifica getByTutore.
   */
  @PostMapping(value = "/getByTutore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getNotificaByTutore(
      @RequestBody TutoreDto input) throws Exception {
    ResponseEntity<RisultatoDto> response;
    try {
      ListDto<NotificaDto> dto = getService().findByDottore(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Notifica getByAdmin.
   */
  @PostMapping(value = "/getByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getNotificaByAdmin(
      @RequestBody AdminDto input) throws Exception {
    ResponseEntity<RisultatoDto> response;
    try {
      ListDto<NotificaDto> dto = getService().findByDottore(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Notifica updateStato.
   */
  @PostMapping(value = "/updateStato", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> updatesato(
      @RequestBody NotificaDto input, @RequestBody Integer nuovoStato) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      NotificaDto dto = getService().findById(input.getId());
      dto.setStato(nuovoStato);
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della notifica"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio save Notifica.
   */

  @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveNotifica(
      @RequestBody NotificaDto input) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    boolean result = false;
    try {
      result = getService().send(input);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Notifica inserito correttamente"), HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento della Notifica"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private NotificaServiceInterface getService() {
    return notificaService.getImplementation();
  }
}