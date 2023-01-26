package com.glucoseguardian.webbackend.service.paziente;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class PazienteService for testing.
 */
@Service
public class TestPazienteService extends AbstractPazienteService {

  @Override
  public PazienteServiceInterface getImplementation() {
    return new PazienteServiceConcrete();
  }
}
