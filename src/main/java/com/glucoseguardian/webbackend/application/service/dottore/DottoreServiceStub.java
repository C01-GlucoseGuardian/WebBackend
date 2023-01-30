package com.glucoseguardian.webbackend.application.service.dottore;

import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */

public class DottoreServiceStub implements DottoreServiceInterface {

  @Override
  public DottoreDto findByCodiceFiscale(String codiceFiscaleDottore) {
    return new DottoreDto();
  }

  @Override
  public List<DottoreDto> findByStato(int stato) {
    return new ArrayList<>();
  }

  @Override
  public DottoreDto findByPaziente(String codiceFiscalePaziente) {
    return new DottoreDto();
  }

  @Override
  public List<DottoreDto> findAll() {
    return new ArrayList<>();
  }

  @Override
  public boolean updateStato(String codiceFiscaleDottore, int nuovoStato) {
    return true;
  }
}
