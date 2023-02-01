package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */
public class FarmacoServiceStub implements FarmacoServiceInterface {

  @Override
  public FarmacoDto findById(Long id) {
    return new FarmacoDto();
  }

  @Override
  public ListDto<FarmacoDto> findFarmaco(String ricerca) {
    return new ListDto<>();
  }
}
