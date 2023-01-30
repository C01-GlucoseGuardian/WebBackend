package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import java.util.List;

/**
 * This is a FarmacoService Interface.
 */
public interface FarmacoServiceInterface {
  FarmacoDto findById(Long id);

  List<FarmacoDto> findFarmaco(String ricerca);
}
