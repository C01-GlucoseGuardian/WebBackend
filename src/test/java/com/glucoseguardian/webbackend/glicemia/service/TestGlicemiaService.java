package com.glucoseguardian.webbackend.glicemia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractGlicemiaService} che restituisce {@link GlicemiaServiceStub}.
 * Da utilizzare a fini di test.
 */
@Service
public class TestGlicemiaService extends AbstractGlicemiaService {

  @Autowired
  private GlicemiaServiceInterface glicemiaService;

  @Override
  public GlicemiaServiceInterface getImplementation() {
    return glicemiaService;
  }
}
