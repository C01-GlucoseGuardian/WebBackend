package com.glucoseguardian.webbackend.application.service.glicemia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractGlicemiaService} che restituisce {@link GlicemiaServiceStub}.
 * Da utilizzare a fini di test.
 */
@Service
public class TestGlicemiaService extends AbstractGlicemiaService {

  @Autowired
  @Qualifier("glicemiaServiceStub")
  GlicemiaServiceInterface glicemiaService;

  @Override
  public GlicemiaServiceInterface getImplementation() {
    return glicemiaService;
  }
}
