package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class NotificaServiceStub implements NotificaServiceInterface {

  @Override
  public NotificaDto findById(Long id) {
    return new NotificaDto();
  }

  @Override
  public boolean send(NotificaDto notificaDto) {
    return true;
  }

  @Override
  public ListDto<NotificaDto> findByPaziente(String codiceFiscale) {
    return new ListDto<>();
  }

  @Override
  public ListDto<NotificaDto> findByDottore(String codiceFiscale) {
    return new ListDto<>();
  }

  @Override
  public ListDto<NotificaDto> findByTutore(String codiceFiscale) {
    return new ListDto<>();
  }

  @Override
  public ListDto<NotificaDto> findByAdmin(String codiceFiscale) {
    return new ListDto<>();
  }
}
