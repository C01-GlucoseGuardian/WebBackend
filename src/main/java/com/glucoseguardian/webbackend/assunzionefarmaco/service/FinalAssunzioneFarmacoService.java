package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalAssunzioneFarmacoService extends AbstractAssunzioneFarmacoService {

  @Autowired
  @Qualifier("assunzioneFarmacoServiceConcrete")
  AssunzioneFarmacoServiceInterface assunzioneFarmacoService;

  @Override
  public AssunzioneFarmacoServiceInterface getImplementation() {
    return assunzioneFarmacoService;
  }
}
