package com.glucoseguardian.webbackend.application.service.notifica;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalNotificaService extends AbstractNotificaService {
  @Override
  public NotificaServiceInterface getImplementation() {
    return new NotificaServiceConcrete();
  }
}


