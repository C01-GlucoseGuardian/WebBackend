package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.ArrayList;
import java.util.List;
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
