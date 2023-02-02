package com.glucoseguardian.webbackend.application.service.tutore;

import com.glucoseguardian.webbackend.application.service.terapia.TerapiaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalTutoreService extends AbstractTutoreService {
  @Autowired
  @Qualifier("TutoreServiceConcrete")
  TutoreServiceInterface tutoreService;

  @Override
  public TutoreServiceInterface getImplementation() {
    return tutoreService;
  }
}
