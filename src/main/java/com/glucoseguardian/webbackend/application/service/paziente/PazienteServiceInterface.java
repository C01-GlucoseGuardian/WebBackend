package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a PazienteService Interface.
 */
public interface PazienteServiceInterface {

  @PreAuthorize("hasAuthority('PAZIENTE')")
  PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  ListDto<PazienteDto> findPaziente(String query);

  @PreAuthorize("hasAuthority('PAZIENTE')")
  boolean save(PazienteDto dto);

}
