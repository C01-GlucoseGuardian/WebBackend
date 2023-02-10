package com.glucoseguardian.webbackend.glicemia.rest;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.glicemia.service.AbstractGlicemiaService;
import com.glucoseguardian.webbackend.glicemia.service.GlicemiaServiceInterface;
import com.glucoseguardian.webbackend.storage.dto.GlicemiaDto;
import com.glucoseguardian.webbackend.storage.dto.GlicemiaInputDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller che si occupa di esporre i servizi del package glicemia.
 */
@RestController
@RequestMapping("glicemia")
public class GlicemiaRest {

  @Autowired
  private AbstractGlicemiaService glicemiaService;

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getLast.
   */
  @PostMapping(value = "/getLast", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getLast(
      @RequestBody GlicemiaInputDto glicemia) throws EntityNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    glicemia.validateIdPaziente();
    try {
      GlicemiaDto dto = getService().getLast(glicemia.getIdPaziente());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante il recupero della glicemia"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /getRange.
   */
  @PostMapping(value = "/getRange", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getRange(
      @RequestBody GlicemiaInputDto glicemia) throws UserNotFoundException {

    // TODO: Add custom checks (es. length, null etc..)

    ResponseEntity<RisultatoDto> response;
    glicemia.validateRange();
    try {
      ListDto<GlicemiaDto> dto = getService().getRange(glicemia.getIdPaziente(),
          glicemia.getStart(), glicemia.getEnd());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante il recupero della glicemia"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }

    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che si occupa delle richieste post all'endpoint /send.
   */
  @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveGlicemia(
      @RequestBody ListDto<GlicemiaDto> list) {

    // TODO: Add custom checks (es. length, null etc..)

    boolean result = false;
    for (GlicemiaDto glicemiaDto : list.getList()) {
      glicemiaDto.validate();
    }
    try {
      Utente paziente = (Utente) getAuthentication().getPrincipal();
      result = getService().send(paziente.getCodiceFiscale(), list);
    } catch (AccessDeniedException ex) {
      throw ex;
    } catch (ClassCastException ex) {
      throw new AccessDeniedException("Utente non autorizzato");
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Dati Glicemia inseriti correttamente"),
              HttpStatus.OK));
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento dei dati glicemia"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private GlicemiaServiceInterface getService() {
    return glicemiaService.getImplementation();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}

