package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.util.List;

/**
 * This is the stub.
 */
public class TerapiaServiceStub implements TerapiaServiceInterface {

  @Override
  public boolean updateTerapia(String codiceFiscalePaziente, List<AssunzioneFarmaco> listaFarmaci) {
    return true;
  }

  @Override
  public TerapiaDto findTerapia(Long idTerapia) {
    return new TerapiaDto();
  }

  @Override
  public TerapiaDto findByPaziente(String codiceFiscalePaziente) {
    return new TerapiaDto();
  }
}
