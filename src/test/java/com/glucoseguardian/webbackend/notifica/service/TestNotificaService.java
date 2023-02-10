package com.glucoseguardian.webbackend.notifica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class NotificaService for testing.
 */
@Service
public class TestNotificaService extends AbstractNotificaService {
  @Autowired
  private NotificaServiceInterface notificaService;

  @Override
  public NotificaServiceInterface getImplementation() {
    return notificaService;
  }
}
