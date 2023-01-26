package com.glucoseguardian.webbackend.service.paziente;

import org.springframework.stereotype.Service;
/**
 * This is an extension of the abstract class.
 */

@Service
public class FinalPazienteService extends AbstractPazienteService {
  @Override
  public PazienteServiceInterface getImplementation() {
    return new PazienteServiceConcrete();
  }
}
