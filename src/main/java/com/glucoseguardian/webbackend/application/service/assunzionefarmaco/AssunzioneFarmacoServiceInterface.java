package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.List;

/**
 * This is a AssunzioneFarmacoService Interface.
 */
public interface AssunzioneFarmacoServiceInterface {

  AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) throws UserNotFoundException;

  ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia) throws UserNotFoundException;

  ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente)
      throws UserNotFoundException;
}
