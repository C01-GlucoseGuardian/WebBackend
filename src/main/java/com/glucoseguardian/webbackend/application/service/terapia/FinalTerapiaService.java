package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceConcrete;
import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceInterface;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalTerapiaService extends AbstractTerapiaService {

  @Override
  public PazienteServiceInterface getImplementation() {
    return new PazienteServiceConcrete();
  }
}
