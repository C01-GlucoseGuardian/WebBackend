package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalAssunzioneFarmacoService extends AbstractAssunzioneFarmacoService {

  @Autowired
  AssunzioneFarmacoServiceInterface assunzioneFarmacoService;

  @Override
  public AssunzioneFarmacoServiceInterface getImplementation() {
    return assunzioneFarmacoService;
  }
}
