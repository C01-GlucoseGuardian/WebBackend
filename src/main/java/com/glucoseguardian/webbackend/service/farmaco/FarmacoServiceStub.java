package com.glucoseguardian.webbackend.service.farmaco;

import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
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
  public List<FarmacoDto> findFarmaco(String ricerca) {
    return new ArrayList<>();
  }
}
