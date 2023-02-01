package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.List;

/**
 * This is a FarmacoService Interface.
 */
public interface FarmacoServiceInterface {
  FarmacoDto findById(Long id) throws UserNotFoundException;

  ListDto<FarmacoDto> findFarmaco(String ricerca);
}
