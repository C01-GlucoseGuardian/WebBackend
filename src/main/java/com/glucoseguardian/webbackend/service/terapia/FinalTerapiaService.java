package com.glucoseguardian.webbackend.service.terapia;

import com.glucoseguardian.webbackend.service.paziente.PazienteServiceConcrete;
import com.glucoseguardian.webbackend.service.paziente.PazienteServiceInterface;
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
