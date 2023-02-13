package com.glucoseguardian.webbackend.dottore.rest;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.dottore.service.AbstractDottoreService;
import com.glucoseguardian.webbackend.dottore.service.DottoreServiceInterface;
import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller che si occupa di esporre i servizi del package dottore.
 */
@RestController
@RequestMapping("dottore")
public class DottoreRest {

  @Autowired
  private AbstractDottoreService dottoreService;
  @Autowired
  private Utils utils;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /get.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getDottore(
      @RequestBody CodiceFiscaleDto codiceFiscaleDottore) throws Exception {


    ResponseEntity<RisultatoDto> response;
    codiceFiscaleDottore.validate();
    try {
      DottoreDto dto = getService().findByCodiceFiscale(codiceFiscaleDottore.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      // These exceptions are managed by CustomExceptionHandler
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del paziente"),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return CompletableFuture.completedFuture(response);
  }
  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByStato.
   */

  @PostMapping(value = "/getByStato", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByStato(
      @RequestBody DottoreDto dottore) {


    ResponseEntity<RisultatoDto> response;
    dottore.validateStato();
    try {
      ListDto<DottoreDto> dto = getService().findByStato(dottore.getStato());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      // These exceptions are managed by CustomExceptionHandler
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante il recupero dei dottori"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getByPaziente.
   */
  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getByPaziente(
      @RequestBody CodiceFiscaleDto codiceFiscalePaziente) throws Exception {


    ResponseEntity<RisultatoDto> response;
    codiceFiscalePaziente.validate();
    try {
      DottoreDto dto = getService().findByPaziente(codiceFiscalePaziente.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      // These exceptions are managed by CustomExceptionHandler
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante il recupero dei dottori"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getAll.
   */
  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getAll() {


    ResponseEntity<RisultatoDto> response;

    try {
      ListDto<DottoreDto> dto = getService().findAll();
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (AccessDeniedException ex) {
      // These exceptions are managed by CustomExceptionHandler
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante il recupero dei dottori"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /updateStato.
   */
  @PostMapping(value = "/updateStato", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> updateStato(
      @RequestBody DottoreDto dottore) throws UserNotFoundException {


    ResponseEntity<RisultatoDto> response;
    dottore.validateStato();
    dottore.validateCodiceFiscale();
    boolean result = false;
    try {
      Utente admin = (Utente) getAuthentication().getPrincipal();
      result = getService().updateStato(dottore.getCodiceFiscale(), dottore.getStato(),
          admin.getCodiceFiscale());
    } catch (AccessDeniedException | UserNotFoundException ex) {
      // These exceptions are managed by CustomExceptionHandler
      throw ex;
    } catch (ClassCastException ex) {
      throw new AccessDeniedException("Utente non autorizzato");
    } catch (Exception ex) {
    }

    if (result) {
      response = new ResponseEntity<>(new RisultatoDto("Modifica effettuata con successo"),
          HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(
          new RisultatoDto("Errore durante il salvataggio della modifica"),
          HttpStatus.INTERNAL_SERVER_ERROR
      );
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /save.
   */
  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveDottore(
      @RequestBody DottoreDto dottore) throws DuplicatedEntityException {

    dottore.validateDottore();
    boolean result = false;
    try {
      result = getService().save(dottore);
    } catch (DuplicatedEntityException ex) {
      throw ex;
    } catch (Exception ex) {
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(
              new RisultatoDto("Dottore registrato correttamente"),
              HttpStatus.OK
          )
      );
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento del dottore"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private DottoreServiceInterface getService() {
    return dottoreService.getImplementation();
  }

  private Authentication getAuthentication() {
    return utils.getAuthentication();
  }
}

