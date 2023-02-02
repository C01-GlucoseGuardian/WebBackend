package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceConcrete;
import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceInterface;
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
