package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {

  NotificaDto findById(Long id);

  boolean send(NotificaDto notificaDto);

  ListDto<NotificaDto> findByPaziente(String codiceFiscale);

  ListDto<NotificaDto> findByDottore(String codiceFiscale);

  ListDto<NotificaDto> findByTutore(String codiceFiscale);

  ListDto<NotificaDto> findByAdmin(String codiceFiscale);
}
