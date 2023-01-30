package com.glucoseguardian.webbackend.service.tutore;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TutoreService for testing.
 */
@Service
public class TestTutoreService extends AbstractTutoreService {

  @Override
  public TutoreServiceInterface getImplementation() {
    return new TutoreServiceConcrete();
  }
}
