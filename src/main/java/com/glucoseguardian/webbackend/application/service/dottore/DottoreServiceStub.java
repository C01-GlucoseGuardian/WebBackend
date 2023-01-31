package com.glucoseguardian.webbackend.application.service.dottore;

import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
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
  public ListDto<DottoreDto> findByStato(int stato) {
    return new ListDto<>();
  }

  @Override
  public DottoreDto findByPaziente(String codiceFiscalePaziente) {
    return new DottoreDto();
  }

  @Override
  public ListDto<DottoreDto> findAll() {
    return new ListDto<>();
  }

  @Override
  public boolean updateStato(String codiceFiscaleDottore, int nuovoStato) {
    return true;
  }

  @Override
  public boolean save(DottoreDto dto) {
    return true;
  }
}
