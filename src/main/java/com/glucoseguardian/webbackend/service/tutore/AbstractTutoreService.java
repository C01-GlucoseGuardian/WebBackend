package com.glucoseguardian.webbackend.service.tutore;

import com.glucoseguardian.webbackend.service.paziente.PazienteServiceInterface;

/**
 * This is an abstact class of TutoreService.
 */
public abstract class AbstractTutoreService {

  public abstract TutoreServiceInterface getImplementation();
}
