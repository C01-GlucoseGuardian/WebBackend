package com.glucoseguardian.webbackend.dottore.service;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */

@Service
public class DottoreServiceStub implements DottoreServiceInterface {

  public boolean duplicatedId;
  public boolean duplicatedEmail;

  @Override
  public DottoreDto findByCodiceFiscale(String codiceFiscaleDottore) {
    return new DottoreDto();
  }

  @Override
  public ListDto<DottoreDto> findByStato(int stato) {
    return new ListDto<>();
  }

  @Override
  public DottoreDto findByPaziente(String codiceFiscalePaziente) {
    return new DottoreDto();
  }

  @Override
  public ListDto<DottoreDto> findAll() {
    return new ListDto<>();
  }

  @Override
  public boolean updateStato(String codiceFiscaleDottore, int nuovoStato,
      String codiceFiscaleAdmin) {
    return true;
  }

  @Override
  public boolean save(DottoreDto dto) throws DuplicatedEntityException {
    if (duplicatedId) {
      throw new DuplicatedEntityException("Codice fiscale già presente nel database");
    }
    if (duplicatedEmail) {
      throw new DuplicatedEntityException("Email già presente nel database");
    }
    return true;
  }
}
