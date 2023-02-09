package com.glucoseguardian.webbackend.dottore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class DottoreService for testing.
 */
@Service
public class TestDottoreService extends AbstractDottoreService {

  @Autowired
  private DottoreServiceInterface dottoreService;

  @Override
  public DottoreServiceInterface getImplementation() {
    return dottoreService;
  }
}
