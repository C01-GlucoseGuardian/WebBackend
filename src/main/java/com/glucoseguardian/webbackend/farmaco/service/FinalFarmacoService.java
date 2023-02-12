package com.glucoseguardian.webbackend.farmaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalFarmacoService extends AbstractFarmacoService {
  @Autowired
  FarmacoServiceInterface farmacoService;

  @Override
  public FarmacoServiceInterface getImplementation() {
    return farmacoService;
  }
}
