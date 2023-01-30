package com.glucoseguardian.webbackend.application.service.farmaco;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFarmacoService extends AbstractFarmacoService {

  @Override
  public FarmacoServiceInterface getImplentation() {
    return new FarmacoServiceConcrete();
  }
}
