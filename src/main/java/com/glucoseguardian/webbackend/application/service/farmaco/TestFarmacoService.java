package com.glucoseguardian.webbackend.application.service.farmaco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TutoreService for testing.
 */
@Service
public class TestFarmacoService extends AbstractFarmacoService {
  @Autowired
  @Qualifier("farmacoServiceStub")
  FarmacoServiceInterface farmacoService;

  @Override
  public FarmacoServiceInterface getImplentation() {
    return farmacoService;
  }
}
