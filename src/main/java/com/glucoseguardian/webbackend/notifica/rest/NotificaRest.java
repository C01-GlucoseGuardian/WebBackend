package com.glucoseguardian.webbackend.notifica.rest;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.AbstractNotificaService;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceInterface;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
   * Metodo che gestisce il servizio Notifica getAll.
   */
  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getAll() throws Exception {
    ResponseEntity<RisultatoDto> response;
    try {
      Utente utente = (Utente) getAuthentication().getPrincipal();
      ListDto<NotificaDto> dto;

      switch (utente.getTipoUtente()) {
        case ADMIN -> dto = getService().findByAdmin(utente.getCodiceFiscale());
        case DOTTORE -> dto = getService().findByDottore(utente.getCodiceFiscale());
        case PAZIENTE -> dto = getService().findByPaziente(utente.getCodiceFiscale());
        case TUTORE -> dto = getService().findByTutore(utente.getCodiceFiscale());
        default -> throw new AccessDeniedException("Utente non autorizzato");
      }

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
      @RequestBody NotificaDto input)
      throws EntityNotFoundException {
    boolean result = false;
    try {
      result = getService().updateStato(input.getId(), input.getStato());
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Stato notifica aggiornato correttamente"),
              HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'aggiornamento della Notifica"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
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
    } catch (UserNotFoundException | AccessDeniedException ex) {
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

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}