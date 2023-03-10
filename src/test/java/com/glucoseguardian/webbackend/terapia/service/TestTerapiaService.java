package com.glucoseguardian.webbackend.terapia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class TerapiaService for testing.
 */
@Service
public class TestTerapiaService extends AbstractTerapiaService {
  @Autowired
  private TerapiaServiceInterface terapiaService;

  @Override
  public TerapiaServiceInterface getImplementation() {
    return terapiaService;
  }
}
