package com.glucoseguardian.webbackend.application.service.glicemia;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.GlicemiaDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.stereotype.Service;

/**
 * Implementazione stab di Glicemia Service.
 */
@Service
public class GlicemiaServiceStub implements GlicemiaServiceInterface {

  @Override
  public GlicemiaDto getLast(String codiceFiscalePaziente) {
    return new GlicemiaDto();
  }

  @Override
  public ListDto<GlicemiaDto> getRange(String codiceFiscalePaziente, long start, long end) {
    return new ListDto<>();
  }

  @Override
  public boolean send(String codiceFiscalePaziente, ListDto<GlicemiaDto> list)
      throws UserNotFoundException {
    return true;
  }
}
