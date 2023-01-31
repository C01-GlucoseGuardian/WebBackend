package com.glucoseguardian.webbackend.application.service.notifica;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class NotificaService for testing.
 */
@Service
public class TestNotificaService extends AbstractNotificaService {
  @Override
  public NotificaServiceInterface getImplementation() {
    return new NotificaServiceConcrete();
  }
}
