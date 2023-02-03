package com.glucoseguardian.webbackend.application.service.glicemia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractGlicemiaService} che restituisce
 * {@link GlicemiaServiceConcrete}.
 */
@Service
public class FinalGlicemiaService extends AbstractGlicemiaService {

  @Autowired
  @Qualifier("glicemiaServiceConcrete")
  GlicemiaServiceInterface glicemiaService;

  @Override
  public GlicemiaServiceInterface getImplementation() {
    return glicemiaService;
  }
}
