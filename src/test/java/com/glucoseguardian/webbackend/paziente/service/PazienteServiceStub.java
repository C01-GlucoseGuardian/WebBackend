package com.glucoseguardian.webbackend.paziente.service;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.storage.dto.CodiceFiscaleDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class PazienteServiceStub implements PazienteServiceInterface {

  public boolean duplicatedId;
  public boolean duplicatedEmail;

  @Override
  public PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) {
    return new PazienteDto();
  }

  @Override
  public ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore) {
    return new ListDto<>();
  }

  @Override
  public ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore) {
    return new ListDto<>();
  }

  @Override
  public ListDto<PazienteDto> findPaziente(String query) {
    return new ListDto<>();
  }

  @Override
  public boolean save(PazienteDto dto) throws DuplicatedEntityException {
    if (duplicatedId) {
      throw new DuplicatedEntityException("Codice fiscale già presente nel database");
    }
    if (duplicatedEmail) {
      throw new DuplicatedEntityException("Email già presente nel database");
    }
    return true;
  }
  @Override
  public boolean updateTutori(String codiceFiscalePaziente, List<CodiceFiscaleDto> list) {
    return true;
  }
}
