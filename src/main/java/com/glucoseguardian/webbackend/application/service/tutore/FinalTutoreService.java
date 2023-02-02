package com.glucoseguardian.webbackend.application.service.tutore;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalTutoreService extends AbstractTutoreService {

  @Override
  public TutoreServiceInterface getImplementation() {
    return new TutoreServiceConcrete();
  }
}
