package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  NotificaDto findById(Long id) throws UserNotFoundException;
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  boolean send(NotificaDto notificaDto);
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByPaziente(String codiceFiscale) throws UserNotFoundException;
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByDottore(String codiceFiscale) throws UserNotFoundException;
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByTutore(String codiceFiscale) throws UserNotFoundException;
  @PreAuthorize("hasAuthority('TUTORE') or hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByAdmin(String codiceFiscale) throws UserNotFoundException;
}
