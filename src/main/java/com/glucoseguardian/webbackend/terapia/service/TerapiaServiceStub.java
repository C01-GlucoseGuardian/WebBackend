package com.glucoseguardian.webbackend.terapia.service;

import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class TerapiaServiceStub implements TerapiaServiceInterface {

  @Override
  public boolean updateTerapia(String codiceFiscalePaziente,
      List<AssunzioneFarmacoDto> listaFarmaci) {
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
