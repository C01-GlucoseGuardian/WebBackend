package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.List;

/**
 * This is a PazienteService Interface.
 */
public interface PazienteServiceInterface {

  PazienteDto findByCodiceFiscale(String codiceFiscalePaziente);

  List<PazienteDto> findByDottore(String codiceFiscaleDottore);

  List<PazienteDto> findByTutore(String codiceFiscaleTutore);

  List<PazienteDto> findPaziente(String query);

  boolean save(PazienteDto dto);

}
