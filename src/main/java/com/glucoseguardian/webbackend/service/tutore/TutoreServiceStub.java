package com.glucoseguardian.webbackend.service.tutore;

import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */
public class TutoreServiceStub implements TutoreServiceInterface {

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) {
    return new TutoreDto();
  }

  @Override
  public List<TutoreDto> findByPaziente(String codiceFiscalePaziente) {
    return new ArrayList<>();
  }
}