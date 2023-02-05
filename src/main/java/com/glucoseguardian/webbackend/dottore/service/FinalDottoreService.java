package com.glucoseguardian.webbackend.dottore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalDottoreService extends AbstractDottoreService {
  @Autowired
  @Qualifier("dottoreServiceConcrete")
  DottoreServiceInterface dottoreService;

  @Override
  public DottoreServiceInterface getImplementation() {
    return dottoreService;
  }
}
