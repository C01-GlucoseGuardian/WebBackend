package com.glucoseguardian.webbackend.tutore.service;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class TutoreServiceStub implements TutoreServiceInterface {

  public boolean duplicatedId;
  public boolean duplicatedEmail;

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) {
    return new TutoreDto();
  }

  @Override
  public ListDto<TutoreDto> findByPaziente(String codiceFiscalePaziente) {
    return new ListDto<>();
  }

  @Override
  public boolean save(TutoreDto dto) throws DuplicatedEntityException {
    if (duplicatedId) {
      throw new DuplicatedEntityException("Codice fiscale già presente nel database");
    }
    if (duplicatedEmail) {
      throw new DuplicatedEntityException("Email già presente nel database");
    }
    return true;
  }
}
