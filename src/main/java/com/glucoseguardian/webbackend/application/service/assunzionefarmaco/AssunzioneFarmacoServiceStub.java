package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */
public class AssunzioneFarmacoServiceStub implements AssunzioneFarmacoServiceInterface {

  @Override
  public AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) {
    return new AssunzioneFarmacoDto();
  }

  @Override
  public List<AssunzioneFarmacoDto> findByTerapia(Long idTeraoia) {
    return new ArrayList<>();
  }

  @Override
  public List<AssunzioneFarmacoDto> findByPaziente(String codifceFiscalePaziente) {
    return new ArrayList<>();
  }
}
