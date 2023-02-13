package com.glucoseguardian.webbackend.terapia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalTerapiaService extends AbstractTerapiaService {
  @Autowired
  TerapiaServiceInterface terapiaService;

  @Override
  public TerapiaServiceInterface getImplementation() {
    return terapiaService;
  }
}
