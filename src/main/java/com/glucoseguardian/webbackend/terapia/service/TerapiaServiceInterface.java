package com.glucoseguardian.webbackend.terapia.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a TutoreService Interface.
 */
public interface TerapiaServiceInterface {

  @PreAuthorize("hasAuthority('DOTTORE')")
  boolean updateTerapia(String codiceFiscalePaziente, List<AssunzioneFarmaco> listaFarmaci);

  @PreAuthorize("hasAuthority('DOTTORE')")
  TerapiaDto findTerapia(Long idTerapia) throws EntityNotFoundException;

  @PreAuthorize("hasAnyAuthority('DOTTORE', 'PAZIENTE', 'TUTORE')")
  TerapiaDto findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;
}
