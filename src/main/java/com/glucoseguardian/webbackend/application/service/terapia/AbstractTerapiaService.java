package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.application.service.paziente.PazienteServiceInterface;

/**
 * This is an abstact class of TerapiaService.
 */
public abstract class AbstractTerapiaService {

  public abstract PazienteServiceInterface getImplementation();
}
