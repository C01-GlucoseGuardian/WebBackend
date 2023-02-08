package com.glucoseguardian.webbackend.notifica.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.entity.Notifica;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {

  @PreAuthorize("isAuthenticated()")
  NotificaDto findById(Long id) throws EntityNotFoundException;

  @PreAuthorize("isAuthenticated()")
  boolean send(NotificaDto notificaDto) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  ListDto<NotificaDto> findByPaziente(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<NotificaDto> findByDottore(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('TUTORE')")
  ListDto<NotificaDto> findByTutore(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('ADMIN')")
  ListDto<NotificaDto> findByAdmin(String codiceFiscale) throws UserNotFoundException;

  @PreAuthorize("isAuthenticated()")
  boolean updateStato(long idNotifica, Integer newStato) throws EntityNotFoundException;
}
