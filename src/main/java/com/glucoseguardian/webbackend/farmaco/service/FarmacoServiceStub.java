package com.glucoseguardian.webbackend.farmaco.service;

import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
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
