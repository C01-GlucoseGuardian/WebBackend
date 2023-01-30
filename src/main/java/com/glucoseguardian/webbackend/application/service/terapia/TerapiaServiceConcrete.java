package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of TerapiaServiceInterface.
 */
@Service
public class TerapiaServiceConcrete implements TerapiaServiceInterface {

  @Autowired
  TerapiaDao terapiaDao;
  @Autowired
  PazienteDao pazienteDao;

  @Override
  public boolean updateTerapia(String codiceFiscalePaziente, List<AssunzioneFarmaco> listaFarmaci) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      Terapia terapia = result.getTerapia();
      terapia.setAssunzioneFarmacos(listaFarmaci);
      terapiaDao.save(terapia);
      return true;
    }
    return false;
  }

  @Override
  public TerapiaDto findTerapia(Long idTerapia) {
    Terapia result = terapiaDao.findById(idTerapia).orElse(null);
    if (result != null) {
      return TerapiaDto.valueOf(result);
    } else {
      throw new RuntimeException("Terapia non trovata.");
    }
  }

  @Override
  public TerapiaDto findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      return TerapiaDto.valueOf(result.getTerapia());
    } else {
      throw new RuntimeException("Terapia non trovata.");
    }
  }
}