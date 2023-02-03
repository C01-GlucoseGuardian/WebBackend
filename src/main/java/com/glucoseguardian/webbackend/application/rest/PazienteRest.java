package com.glucoseguardian.webbackend.application.rest;

import com.glucoseguardian.webbackend.application.service.paziente.AbstractPazienteService;
import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceInterface;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RicercaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
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

@RestController
@RequestMapping("paziente")
public class PazienteRest {

  @Autowired
  @Qualifier("finalPazienteService")
  private AbstractPazienteService pazienteService;

  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getPaziente(
      @RequestBody PazienteDto paziente) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      PazienteDto dto = getService().findByCodiceFiscale(paziente.getCodiceFiscale());
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

  @PostMapping(value = "/getByDottore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByDottore(
      @RequestBody DottoreDto dottore) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<PazienteDto> dto = getService().findByDottore(dottore.getCodiceFiscale());
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

  @PostMapping(value = "/getByTutore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByTutore(
      @RequestBody TutoreDto tutore) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<PazienteDto> dto = getService().findByTutore(tutore.getCodiceFiscale());
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

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> savePaziente(
      @RequestBody PazienteDto paziente) {

    // TODO: Add custom checks (es. length, null etc..)

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

  private PazienteServiceInterface getService() {
    return pazienteService.getImplementation();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}

