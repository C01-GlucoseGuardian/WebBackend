package com.glucoseguardian.webbackend.paziente.rest;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.paziente.service.AbstractPazienteService;
import com.glucoseguardian.webbackend.paziente.service.PazienteServiceInterface;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RicercaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreUpdateDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller che si occupa di esporre i servizi del package paziente.
 */
@RestController
@RequestMapping("paziente")
public class PazienteRest {

  @Autowired
  @Qualifier("finalPazienteService")
  private AbstractPazienteService pazienteService;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /get.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getPaziente(
      @RequestBody CodiceFiscaleDto codiceFiscalePaziente) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    codiceFiscalePaziente.validate();
    try {
      PazienteDto dto = getService().findByCodiceFiscale(codiceFiscalePaziente.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del paziente"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByDottore.
   */
  @PostMapping(value = "/getByDottore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByDottore(
      @RequestBody CodiceFiscaleDto codiceFiscaleDottore) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    codiceFiscaleDottore.validate();
    try {
      ListDto<PazienteDto> dto = getService()
          .findByDottore(codiceFiscaleDottore.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del paziente"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByTutore.
   */
  @PostMapping(value = "/getByTutore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByTutore(
      @RequestBody CodiceFiscaleDto codiceFiscaleTutore) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    codiceFiscaleTutore.validate();
    try {
      ListDto<PazienteDto> dto = getService().findByTutore(codiceFiscaleTutore.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del paziente"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /find.
   */
  @PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> findPaziente(
      @RequestBody RicercaDto query) {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<PazienteDto> dto = getService().findPaziente(query.getQuery());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del paziente"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /save.
   */
  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> savePaziente(
      @RequestBody PazienteDto paziente) {

    // TODO: Add custom checks (es. length, null etc..)
    paziente.validate();
    boolean result = false;
    try {
      Utente dottore = (Utente) getAuthentication().getPrincipal();
      paziente.setIdDottore(dottore.getCodiceFiscale());
      result = getService().save(paziente);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Paziente inserito correttamente"), HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento del paziente"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /updateTutori.
   */
  @PostMapping(value = "/updateTutori", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> updatetutori(
      @RequestBody TutoreUpdateDto input)
      throws EntityNotFoundException {
    input.validate();
    boolean result = false;
    try {
      result = getService().updateTutori(input.getIdPaziente(),
          input.getList());
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Tutori modificati correttamente"),
              HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore nell'aggiornamento dei tutori"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private PazienteServiceInterface getService() {
    return pazienteService.getImplementation();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}

