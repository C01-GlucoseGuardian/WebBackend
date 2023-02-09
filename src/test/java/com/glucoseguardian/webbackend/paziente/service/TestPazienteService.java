package com.glucoseguardian.webbackend.paziente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class PazienteService for testing.
 */
@Service
public class TestPazienteService extends AbstractPazienteService {
  @Autowired
  private PazienteServiceInterface pazienteService;

  @Override
  public PazienteServiceInterface getImplementation() {
    return pazienteService;
  }
}
