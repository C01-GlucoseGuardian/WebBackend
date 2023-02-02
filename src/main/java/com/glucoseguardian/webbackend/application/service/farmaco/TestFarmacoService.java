package com.glucoseguardian.webbackend.application.service.farmaco;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TutoreService for testing.
 */
@Service
public class TestFarmacoService extends AbstractFarmacoService {

  @Override
  public FarmacoServiceInterface getImplentation() {
    return new FarmacoServiceConcrete();
  }
}
