package com.glucoseguardian.webbackend.service.assunzionefarmaco;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalAssunzioneFarmacoService extends AbstractAssunzioneFarmacoService {

  @Override
  public AssunzioneFarmacoServiceInterface getImplementation() {
    return new AssunzioneFarmacoServiceConcrete();
  }
}
