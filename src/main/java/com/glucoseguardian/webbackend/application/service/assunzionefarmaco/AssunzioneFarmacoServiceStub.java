package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class AssunzioneFarmacoServiceStub implements AssunzioneFarmacoServiceInterface {

  @Override
  public AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) {
    return new AssunzioneFarmacoDto();
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia) {
    return new ListDto<>();
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente) {
    return new ListDto<>();
  }
}
