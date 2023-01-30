package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.List;

/**
 * This is a PazienteService Interface.
 */
public interface PazienteServiceInterface {

  PazienteDto findByCodifceFiscale(String codiceFiscalePaziente);

  List<PazienteDto> findByDottore(String codiceFiscaleDottore);

  List<PazienteDto> findByTutore(String codiceFiscaleTutore);
}
