package com.glucoseguardian.webbackend.service.tutore;

import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of TutoreServiceInterface.
 */
@Service
public class TutoreServiceConcrete implements TutoreServiceInterface {

  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  TutoreDao tutoreDao;

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) {
    Tutore result = tutoreDao.findById(codiceFiscaleTutore).orElse(null);
    if (result != null) {
      return TutoreDto.valueOf(result);
    } else {
      throw new RuntimeException("Tutore non trovato.");
    }
  }

  @Override
  public List<TutoreDto> findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result == null) {
      throw new RuntimeException("Paziente non trovato.");
    }
    List<Tutore> list = result.getProfiliTutore();
    List<TutoreDto> tutoreDtos = new ArrayList<>();
    for (Tutore tutore : list) {
      tutoreDtos.add(TutoreDto.valueOf(tutore));
    }
    return tutoreDtos;
  }
}
