package com.glucoseguardian.webbackend.service.dottore;

import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of DottoreServiceInterface.
 */
@Service
public class DottoreServiceConcrete implements DottoreServiceInterface {

  @Autowired
  DottoreDao dottoreDao;
  @Autowired
  PazienteDao pazienteDao;

  @Override
  public DottoreDto findByCodifceFiscale(String codiceFiscaleDottore) {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      return DottoreDto.valueOf(result);
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  public List<DottoreDto> findByStato(int stato) {
    List<Dottore> result = dottoreDao.findByStato(stato);
    if (result == null) {
      throw new RuntimeException("Nessun dottore con quello stato.");
    }
    List<DottoreDto> dottoreDtoStato = new ArrayList<>();
    for (Dottore dottore : result) {
      dottoreDtoStato.add(DottoreDto.valueOf(dottore));
    }
    return dottoreDtoStato;
  }

  @Override
  public DottoreDto findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result == null) {
      throw new RuntimeException("Paziente non trovato.");
    }
    Dottore dottore = result.getDottore();
    return DottoreDto.valueOf(dottore);
  }

  @Override
  public List<DottoreDto> findAll() {
    List<Dottore> result = dottoreDao.findAll();
    List<DottoreDto> tuttiDottoriDto = new ArrayList<>();
    for (Dottore dottore : result) {
      tuttiDottoriDto.add(DottoreDto.valueOf(dottore));
    }
    return tuttiDottoriDto;
  }

  @Override
  public DottoreDto updateStato(String codiceFiscaleDottore, int nuovoStato) {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      result.setStato(nuovoStato);
      return DottoreDto.valueOf(result);
    } else {
      throw new RuntimeException();
    }
  }
}
