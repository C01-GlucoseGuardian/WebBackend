package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class AssunzioneFarmacoService for testing.
 */
@Service
public class TestAssunzioneFarmacoService extends AbstractAssunzioneFarmacoService {

  @Override
  public AssunzioneFarmacoServiceInterface getImplementation() {
    return new AssunzioneFarmacoServiceConcrete();
  }
}
