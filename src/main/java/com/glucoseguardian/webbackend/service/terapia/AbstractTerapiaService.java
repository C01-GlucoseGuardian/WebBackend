package com.glucoseguardian.webbackend.service.terapia;

import com.glucoseguardian.webbackend.service.paziente.PazienteServiceInterface;

/**
 * This is an abstact class of TerapiaService.
 */
public abstract class AbstractTerapiaService {

  public abstract PazienteServiceInterface getImplementation();
}
