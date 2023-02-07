package com.glucoseguardian.webbackend.farmaco.rest;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.farmaco.service.AbstractFarmacoService;
import com.glucoseguardian.webbackend.farmaco.service.FarmacoServiceInterface;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.IdDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.RicercaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
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
 * Rest controller che si occupa di esporre i servizi del package farmaco.
 */
@RestController
@RequestMapping("farmaco")
public class FarmacoRest {

  @Autowired
  @Qualifier("finalFarmacoService")
  private AbstractFarmacoService farmacoService;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /get.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFarmaco(
      @RequestBody IdDto idFarmaco) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    idFarmaco.validate();
    try {
      FarmacoDto dto = getService().findById(idFarmaco.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del farmaco"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /find.
   */
  @PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> findFarmaco(
      @RequestBody RicercaDto query) {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<FarmacoDto> dto = getService().findFarmaco(query.getQuery());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del farmaco"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  private FarmacoServiceInterface getService() {
    return farmacoService.getImplementation();
  }
}

