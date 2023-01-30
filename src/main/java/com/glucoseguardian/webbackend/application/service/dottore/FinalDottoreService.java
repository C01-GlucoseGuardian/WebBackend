package com.glucoseguardian.webbackend.application.service.dottore;


import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalDottoreService extends AbstractDottoreService {


  @Override
  public DottoreServiceInterface getImplementation() {
    return new DottoreServiceConcrete();
  }
}
