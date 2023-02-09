package com.glucoseguardian.webbackend.glicemia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractGlicemiaService} che restituisce
 * {@link GlicemiaServiceConcrete}.
 */
@Service
@Primary
public class FinalGlicemiaService extends AbstractGlicemiaService {

  @Autowired
  @Qualifier("glicemiaServiceConcrete")
  GlicemiaServiceInterface glicemiaService;

  @Override
  public GlicemiaServiceInterface getImplementation() {
    return glicemiaService;
  }
}
