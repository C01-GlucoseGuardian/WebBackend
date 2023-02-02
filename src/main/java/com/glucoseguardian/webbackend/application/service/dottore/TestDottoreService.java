package com.glucoseguardian.webbackend.application.service.dottore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class DottoreService for testing.
 */
@Service
public class TestDottoreService extends AbstractDottoreService {

  @Autowired
  @Qualifier("DottoreServiceStub")
  DottoreServiceInterface dottoreService;

  @Override
  public DottoreServiceInterface getImplementation() {
    return dottoreService;
  }
}
