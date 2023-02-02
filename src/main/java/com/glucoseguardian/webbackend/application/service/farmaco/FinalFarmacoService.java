package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.application.service.dottore.DottoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFarmacoService extends AbstractFarmacoService {
  @Autowired
  @Qualifier("farmacoServiceConcrete")
  FarmacoServiceInterface farmacoService;

  @Override
  public FarmacoServiceInterface getImplentation() {
    return farmacoService;
  }
}
