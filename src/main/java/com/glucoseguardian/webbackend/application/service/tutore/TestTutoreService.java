package com.glucoseguardian.webbackend.application.service.tutore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TutoreService for testing.
 */
@Service
public class TestTutoreService extends AbstractTutoreService {
  @Autowired
  @Qualifier("tutoreServiceStub")
  TutoreServiceInterface tutoreService;

  @Override
  public TutoreServiceInterface getImplementation() {
    return tutoreService;
  }
}
