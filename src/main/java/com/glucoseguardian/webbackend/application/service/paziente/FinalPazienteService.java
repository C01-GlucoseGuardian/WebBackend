package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.application.service.notifica.NotificaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/**
 * This is an extension of the abstract class.
 */

@Service
public class FinalPazienteService extends AbstractPazienteService {
  @Autowired
  @Qualifier("PazienteServiceConcrete")
  PazienteServiceInterface pazienteService;

  @Override
  public PazienteServiceInterface getImplementation() {
    return pazienteService;
  }
}
