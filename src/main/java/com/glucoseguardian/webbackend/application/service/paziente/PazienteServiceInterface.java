package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.List;

/**
 * This is a PazienteService Interface.
 */
public interface PazienteServiceInterface {

  PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) throws UserNotFoundException;

  ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore) throws UserNotFoundException;

  ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore) throws UserNotFoundException;

  ListDto<PazienteDto> findPaziente(String query);

  boolean save(PazienteDto dto);

}
