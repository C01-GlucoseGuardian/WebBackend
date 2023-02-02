package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceConcrete;
import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TerapiaService for testing.
 */
@Service
public class TestTerapiaService extends AbstractTerapiaService {
  @Autowired
  @Qualifier("terapiaServiceStub")
  TerapiaServiceInterface terapiaService;

  @Override
  public TerapiaServiceInterface getImplementation() {
    return terapiaService;
  }
}
