package com.glucoseguardian.webbackend.assunzionefarmaco.rest;

import com.glucoseguardian.webbackend.assunzionefarmaco.service.AbstractAssunzioneFarmacoService;
import com.glucoseguardian.webbackend.assunzionefarmaco.service.AssunzioneFarmacoServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.IdDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
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
 * Rest controller che si occupa di esporre i servizi del package assunzioneFarmaco.
 */

@RestController
@RequestMapping("assunzioneFarmaco")
public class AssunzioneFarmacoRest {

  @Autowired
  private AbstractAssunzioneFarmacoService assunzioneFarmacoService;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /get.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getAssunzioneFarmaco(
      @RequestBody IdDto idAssunzineFarmaco) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    idAssunzineFarmaco.validate();
    try {
      AssunzioneFarmacoDto dto = getService().findById(idAssunzineFarmaco.getId());
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
      @RequestBody IdDto idTerapia) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    idTerapia.validate();

    try {
      ListDto<AssunzioneFarmacoDto> dto = getService().findByTerapia(idTerapia.getId());
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
      @RequestBody CodiceFiscaleDto codiceFiscalePaziente) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    codiceFiscalePaziente.validate();
    try {
      ListDto<AssunzioneFarmacoDto> dto = getService().findByPaziente(
          codiceFiscalePaziente.getCodiceFiscale());
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

