package com.glucoseguardian.webbackend.application.service.glicemia;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.GlicemiaDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Interfaccia Glicemia Service.
 */
public interface GlicemiaServiceInterface {

  @PreAuthorize("hasAnyAuthority('TUTORE', 'DOTTORE', 'PAZIENTE')")
  GlicemiaDto getLast(String codiceFiscalePaziente) throws EntityNotFoundException;

  @PreAuthorize("hasAnyAuthority('TUTORE', 'DOTTORE', 'PAZIENTE')")
  ListDto<GlicemiaDto> getRange(String codiceFiscalePaziente, long start, long end)
      throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  boolean send(String codiceFiscalePaziente, ListDto<GlicemiaDto> list)
      throws UserNotFoundException;

}
