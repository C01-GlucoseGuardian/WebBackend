package com.glucoseguardian.webbackend.terapia.rest;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.IdDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.terapia.service.AbstractTerapiaService;
import com.glucoseguardian.webbackend.terapia.service.TerapiaServiceInterface;
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
 * Implementation TerapiaRest.
 */
@RestController
@RequestMapping("terapia")
public class TerapiaRest {

  @Autowired
  private AbstractTerapiaService terapiaService;

  /**
   * Metodo che gestisce il servizio Feedback findById.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTerapia(
      @RequestBody IdDto idTerapia) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    idTerapia.validate();
    try {
      TerapiaDto dto = getService().findTerapia(idTerapia.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della Terapia"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Feedback findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTerapiaByPaziente(
      @RequestBody CodiceFiscaleDto codiceFiscalePaziente) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      TerapiaDto dto = getService().findByPaziente(codiceFiscalePaziente.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del feedback"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio updateTerapia.
   */
  @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> updateTerapia(
      @RequestBody TerapiaDto input)
      throws EntityNotFoundException {
    input.validate();
    boolean result = false;
    try {
      result = getService().updateTerapia(input.getIdPaziente(), input.getFarmaci());
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Terapia aggiornata correttamente"),
              HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'aggiornamento della Terapia"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private TerapiaServiceInterface getService() {
    return terapiaService.getImplementation();
  }
}
