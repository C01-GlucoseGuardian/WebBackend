package com.glucoseguardian.webbackend.farmaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFarmacoService extends AbstractFarmacoService {
  @Autowired
  @Qualifier("farmacoServiceConcrete")
  FarmacoServiceInterface farmacoService;

  @Override
  public FarmacoServiceInterface getImplementation() {
    return farmacoService;
  }
}
