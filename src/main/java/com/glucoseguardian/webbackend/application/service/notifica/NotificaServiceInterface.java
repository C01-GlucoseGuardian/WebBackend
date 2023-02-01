package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {

  @PreAuthorize("hasAnyAuthority()")
  NotificaDto findById(Long id) throws EntityNotFoundException;

  @PreAuthorize("hasAnyAuthority()")
  boolean send(NotificaDto notificaDto);

  @PreAuthorize("hasAuthority('PAZIENTE')")
  ListDto<NotificaDto> findByPaziente(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<NotificaDto> findByDottore(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('TUTORE')")
  ListDto<NotificaDto> findByTutore(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByAdmin(String codiceFiscale) throws UserNotFoundException;
}
