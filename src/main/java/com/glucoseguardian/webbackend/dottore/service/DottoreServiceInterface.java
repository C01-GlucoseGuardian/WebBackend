package com.glucoseguardian.webbackend.dottore.service;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a DottoreService Interface.
 */
public interface DottoreServiceInterface {
  @PreAuthorize("isAuthenticated()")
  DottoreDto findByCodiceFiscale(String codiceFiscaleDottore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('ADMIN')")
  ListDto<DottoreDto> findByStato(int stato);

  @PreAuthorize("hasAuthority('PAZIENTE') or hasAuthority('TUTORE')")
  DottoreDto findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('ADMIN')")
  ListDto<DottoreDto> findAll();

  @PreAuthorize("hasAuthority('ADMIN')")
  boolean updateStato(String codiceFiscaleDottore, int nuovoStato, String codiceFiscaleAdmin)
      throws UserNotFoundException;

  @PreAuthorize("permitAll()")
  boolean save(DottoreDto dto) throws DuplicatedEntityException;

}