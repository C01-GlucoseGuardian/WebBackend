package com.glucoseguardian.webbackend.application.service.tutore;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a TutoreService Interface.
 */
public interface TutoreServiceInterface {
  @PreAuthorize("hasAuthority('TUTORE')")
  TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('TUTORE')")
  ListDto<TutoreDto> findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;

  boolean save(TutoreDto dto) throws UserNotFoundException;

}
