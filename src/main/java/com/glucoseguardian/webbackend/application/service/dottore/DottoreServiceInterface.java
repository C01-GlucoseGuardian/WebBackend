package com.glucoseguardian.webbackend.application.service.dottore;

import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import java.util.List;

/**
 * This is a DottoreService Interface.
 */
public interface DottoreServiceInterface {
  DottoreDto findByCodiceFiscale(String codiceFiscaleDottore);

  List<DottoreDto> findByStato(int stato);

  DottoreDto findByPaziente(String codiceFiscalePaziente);

  List<DottoreDto> findAll();

  boolean updateStato(String codiceFiscaleDottore, int nuovoStato);

}