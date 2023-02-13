package com.glucoseguardian.webbackend.paziente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
/**
 * This is an extension of the abstract class.
 */

@Service
@Primary
public class FinalPazienteService extends AbstractPazienteService {
  @Autowired
  PazienteServiceInterface pazienteService;

  @Override
  public PazienteServiceInterface getImplementation() {
    return pazienteService;
  }
}
