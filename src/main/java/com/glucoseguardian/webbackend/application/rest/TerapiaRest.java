package com.glucoseguardian.webbackend.application.rest;

import com.glucoseguardian.webbackend.application.service.feedback.AbstractFeedbackService;
import com.glucoseguardian.webbackend.application.service.feedback.FeedbackServiceInterface;
import com.glucoseguardian.webbackend.application.service.terapia.AbstractTerapiaService;
import com.glucoseguardian.webbackend.application.service.terapia.TerapiaServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.util.List;
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
 * Implementation TerapiaRest.
 */
@RestController
@RequestMapping("terapia")
public class TerapiaRest {

  @Autowired
  @Qualifier("finalTerapiaService")
  private AbstractTerapiaService terapiaService;

  /**
   * Metodo che gestisce il servizio Feedback findById.
   */
  @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTerapia(
      @RequestBody TerapiaDto input) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      TerapiaDto dto = getService().findTerapia(input.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della Terapia"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio Feedback findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getTerapiaByPaziente(
      @RequestBody PazienteDto input) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      TerapiaDto dto = getService().findByPaziente(input.getCodiceFiscale());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (UserNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del feedback"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  /**
   * Metodo che gestisce il servizio updateTerapia.
   */
  @PostMapping(value = "/updateTerapia", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> updateTerapia(
      @RequestBody TerapiaDto input, @RequestBody List<AssunzioneFarmacoDto> listaFarmaci) throws EntityNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      TerapiaDto dto = getService().findTerapia(input.getId());
      dto.setFarmaci(listaFarmaci);
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (EntityNotFoundException | AccessDeniedException ex) {
      throw ex;
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca della Terapia"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }

  private TerapiaServiceInterface getService() {
    return terapiaService.getImplementation();
  }
}
