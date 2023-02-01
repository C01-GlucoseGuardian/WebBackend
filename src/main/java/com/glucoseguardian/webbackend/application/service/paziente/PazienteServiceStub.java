package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */
public class PazienteServiceStub implements PazienteServiceInterface {

  @Override
  public PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) {
    return new PazienteDto();
  }

  @Override
  public ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore) {
    return new ListDto<>();
  }

  @Override
  public ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore) {
    return new ListDto<>();
  }

  @Override
  public ListDto<PazienteDto> findPaziente(String query) {
    return new ListDto<>();
  }

  @Override
  public boolean save(PazienteDto dto) {
    return true;
  }
}
