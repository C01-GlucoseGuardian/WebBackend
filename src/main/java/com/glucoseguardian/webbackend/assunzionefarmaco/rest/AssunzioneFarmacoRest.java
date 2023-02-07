package com.glucoseguardian.webbackend.assunzionefarmaco.rest;

import com.glucoseguardian.webbackend.assunzionefarmaco.service.AbstractAssunzioneFarmacoService;
import com.glucoseguardian.webbackend.assunzionefarmaco.service.AssunzioneFarmacoServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
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
 * Rest controller che si occupa di esporre i servizi del package assunzioneFarmaco.
 */
@RestController
@RequestMapping("assunzioneFarmaco")
public class AssunzioneFarmacoRest {

  @Autowired
  @Qualifier("finalAssunzioneFarmacoService")
  private AbstractAssunzioneFarmacoService assunzioneFarmacoService;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /get.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getAssunzioneFarmaco(
      @RequestBody AssunzioneFarmacoDto assunzioneFarmaco) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      AssunzioneFarmacoDto dto = getService().findById(assunzioneFarmaco.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca"
          + " dell'assunzioneFarmaco"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByTerapia.
   */
  @PostMapping(value = "/getByTerapia", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByTerapia(
      @RequestBody TerapiaDto terapia) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<AssunzioneFarmacoDto> dto = getService().findByTerapia(terapia.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca"
          + " dell'assunzioneFarmaco"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByPaziente.
   */
  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByPaziente(
      @RequestBody PazienteDto paziente) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<AssunzioneFarmacoDto> dto = getService().findByPaziente(paziente.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca"
          + " dell'assunzioneFarmaco"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  private AssunzioneFarmacoServiceInterface getService() {
    return assunzioneFarmacoService.getImplementation();
  }
}

