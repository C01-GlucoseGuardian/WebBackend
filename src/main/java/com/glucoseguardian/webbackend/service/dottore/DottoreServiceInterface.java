package com.glucoseguardian.webbackend.service.dottore;

import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import java.util.List;

/**
 * This is a DottoreService Interface.
 */
public interface DottoreServiceInterface {
  DottoreDto findByCodifceFiscale(String codiceFiscaleDottore);

  List<DottoreDto> findByStato(int stato);

  DottoreDto findByPaziente(String codiceFiscalePaziente);

  List<DottoreDto> findAll();

  boolean updateStato(String codifceFiscaleDottore, int nuovoStato);

}