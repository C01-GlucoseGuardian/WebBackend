package com.glucoseguardian.webbackend.application.service.paziente;

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
  public List<PazienteDto> findByDottore(String codiceFiscaleDottore) {
    return new ArrayList<>();
  }

  @Override
  public List<PazienteDto> findByTutore(String codiceFiscaleTutore) {
    return new ArrayList<>();
  }

  @Override
  public List<PazienteDto> findPaziente(String query) {
    return new ArrayList<>();
  }

  @Override
  public boolean save(PazienteDto dto) {
    return true;
  }
}
