package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a AssunzioneFarmacoService Interface.
 */
public interface AssunzioneFarmacoServiceInterface {
  @PreAuthorize("hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('TUTORE')")
  AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) throws EntityNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('TUTORE')")
  ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia);

  @PreAuthorize("hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('TUTORE')")
  ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente);
}
