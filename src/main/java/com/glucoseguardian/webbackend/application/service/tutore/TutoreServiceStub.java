package com.glucoseguardian.webbackend.application.service.tutore;

import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class TutoreServiceStub implements TutoreServiceInterface {

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) {
    return new TutoreDto();
  }

  @Override
  public ListDto<TutoreDto> findByPaziente(String codiceFiscalePaziente) {
    return new ListDto<>();
  }

  @Override
  public boolean save(TutoreDto dto) {
    return true;
  }
}
