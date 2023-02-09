package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class AssunzioneFarmacoService for testing.
 */
@Service
public class TestAssunzioneFarmacoService extends AbstractAssunzioneFarmacoService {

  @Autowired
  @Qualifier("assunzioneFarmacoServiceStub")
  AssunzioneFarmacoServiceInterface assunzioneFarmacoService;

  @Override
  public AssunzioneFarmacoServiceInterface getImplementation() {
    return assunzioneFarmacoService;
  }
}
