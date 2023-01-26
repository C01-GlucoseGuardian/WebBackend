package com.glucoseguardian.webbackend.service.paziente;

import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the stub.
 */
public class PazienteServiceStub implements PazienteServiceInterface {

  @Override
  public PazienteDto findByCodifceFiscale(String codiceFiscalePaziente) {
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
}
