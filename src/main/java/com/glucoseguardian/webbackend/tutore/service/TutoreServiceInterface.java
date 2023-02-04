package com.glucoseguardian.webbackend.tutore.service;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a TutoreService Interface.
 */
public interface TutoreServiceInterface {
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('DOTTORE')")
  TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) throws UserNotFoundException;

  @PreAuthorize("hasAnyAuthority('PAZIENTE', 'DOTTORE')")
  ListDto<TutoreDto> findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  boolean save(TutoreDto dto) throws UserNotFoundException;

}
