package com.glucoseguardian.webbackend.terapia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalTerapiaService extends AbstractTerapiaService {
  @Autowired
  @Qualifier("terapiaServiceConcrete")
  TerapiaServiceInterface terapiaService;

  @Override
  public TerapiaServiceInterface getImplementation() {
    return terapiaService;
  }
}
