package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;

/**
 * This is a NotificaService Interface.
 */
public interface NotificaServiceInterface {

  public NotificaDto findById(Long id);

  public boolean send(NotificaDto notificaDto);

  public ListDto<NotificaDto> findByPaziente(String codiceFiscale);

  public ListDto<NotificaDto> findByDottore(String codiceFiscale);

  public ListDto<NotificaDto> findByTutore(String codiceFiscale);

  public ListDto<NotificaDto> findByAdmin(String codiceFiscale);
}
