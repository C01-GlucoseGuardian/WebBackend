package com.glucoseguardian.webbackend.application.service.notifica;

import com.glucoseguardian.webbackend.application.service.feedback.FeedbackServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalNotificaService extends AbstractNotificaService {
  @Autowired
  @Qualifier("NotificaServiceConcrete")
  NotificaServiceInterface notificaService;

  @Override
  public NotificaServiceInterface getImplementation() {
    return notificaService;
  }
}


