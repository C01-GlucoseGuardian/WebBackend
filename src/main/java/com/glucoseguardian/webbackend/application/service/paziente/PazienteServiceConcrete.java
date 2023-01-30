package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of PazienteServiceInterface.
 */
@Service
public class PazienteServiceConcrete implements PazienteServiceInterface {

  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  DottoreDao dottoreDao;
  @Autowired
  TutoreDao tutoreDao;

  @Override
  public PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      return PazienteDto.valueOf(result);
    } else {
      throw new RuntimeException("Paziente non trovato.");
    }
  }

  @Override
  public List<PazienteDto> findByDottore(String codiceFiscaleDottore) {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result == null) {
      throw new RuntimeException("Dottore non trovato.");
    }
    List<Paziente> list = result.getPazientes();
    List<PazienteDto> pazienteDtos = new ArrayList<>();
    for (Paziente paziente : list) {
      pazienteDtos.add(PazienteDto.valueOf(paziente));
    }
    return pazienteDtos;
  }

  @Override
  public List<PazienteDto> findByTutore(String codiceFiscaleTutore) {
    Tutore result = tutoreDao.findById(codiceFiscaleTutore).orElse(null);
    if (result == null) {
      throw new RuntimeException("Tutore non trovato.");
    }
    List<Paziente> list = result.getPazienteList();
    List<PazienteDto> pazienteDtos = new ArrayList<>();
    for (Paziente paziente : list) {
      pazienteDtos.add(PazienteDto.valueOf(paziente));
    }
    return pazienteDtos;
  }

}
