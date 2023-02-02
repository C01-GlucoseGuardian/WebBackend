package com.glucoseguardian.webbackend.application.service.notifica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class NotificaService for testing.
 */
@Service
public class TestNotificaService extends AbstractNotificaService {
  @Autowired
  @Qualifier("notificaServiceStub")
  NotificaServiceInterface notificaService;

  @Override
  public NotificaServiceInterface getImplementation() {
    return notificaService;
  }
}
