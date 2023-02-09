package com.glucoseguardian.webbackend.notifica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalNotificaService extends AbstractNotificaService {
  @Autowired
  @Qualifier("notificaServiceConcrete")
  NotificaServiceInterface notificaService;

  @Override
  public NotificaServiceInterface getImplementation() {
    return notificaService;
  }
}


