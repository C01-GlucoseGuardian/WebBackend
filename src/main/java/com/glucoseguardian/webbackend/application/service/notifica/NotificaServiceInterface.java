package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {

  NotificaDto findById(Long id) throws UserNotFoundException;

  boolean send(NotificaDto notificaDto);

  ListDto<NotificaDto> findByPaziente(String codiceFiscale) throws UserNotFoundException;

  ListDto<NotificaDto> findByDottore(String codiceFiscale) throws UserNotFoundException;

  ListDto<NotificaDto> findByTutore(String codiceFiscale) throws UserNotFoundException;

  ListDto<NotificaDto> findByAdmin(String codiceFiscale) throws UserNotFoundException;
}
