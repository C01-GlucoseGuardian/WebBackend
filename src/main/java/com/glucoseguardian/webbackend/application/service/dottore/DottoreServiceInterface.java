package com.glucoseguardian.webbackend.application.service.dottore;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import java.util.List;

/**
 * This is a DottoreService Interface.
 */
public interface DottoreServiceInterface {

  DottoreDto findByCodiceFiscale(String codiceFiscaleDottore) throws UserNotFoundException;

  ListDto<DottoreDto> findByStato(int stato);

  DottoreDto findByPaziente(String codiceFiscalePaziente);

  ListDto<DottoreDto> findAll();

  boolean updateStato(String codiceFiscaleDottore, int nuovoStato) throws UserNotFoundException;

  boolean save(DottoreDto dto);

}