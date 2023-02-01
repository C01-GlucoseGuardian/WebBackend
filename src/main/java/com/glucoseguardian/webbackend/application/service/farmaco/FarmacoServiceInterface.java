package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a FarmacoService Interface.
 */
public interface FarmacoServiceInterface {
  @PreAuthorize("hasAuthority('DOTTORE') or hasAuthority('PAZIENTE') or hasAuthority('TUTORE')")
  FarmacoDto findById(Long id) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<FarmacoDto> findFarmaco(String ricerca);
}
