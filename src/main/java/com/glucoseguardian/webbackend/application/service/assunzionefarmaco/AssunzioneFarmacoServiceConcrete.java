package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of AssunzioneFarmacoServiceInterface.
 */
@Service
public class AssunzioneFarmacoServiceConcrete implements AssunzioneFarmacoServiceInterface {

  @Autowired
  AssunzioneFarmacoDao assunzioneFarmacoDao;
  @Autowired
  DottoreDao dottoreDao;
  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  TerapiaDao terapiaDao;

  @Override
  public AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) {
    AssunzioneFarmaco result = assunzioneFarmacoDao.findById(idAssunzioneFarmaco).orElse(null);
    if (result != null) {
      return AssunzioneFarmacoDto.valueOf(result);
    } else {
      throw new RuntimeException("AssunzioneFarmaco non trovato.");
    }
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia) {
    Terapia result = terapiaDao.findById(idTerapia).orElse(null);
    if (result != null) {
      List<AssunzioneFarmacoDto> list = new ArrayList<>();
      for (AssunzioneFarmaco assunzioneFarmaco : result.getAssunzioneFarmacos()) {
        list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new RuntimeException("Terapia non trovata.");
    }
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      List<AssunzioneFarmacoDto> list = new ArrayList<>();
      for (AssunzioneFarmaco assunzioneFarmaco : result.getTerapia().getAssunzioneFarmacos()) {
        list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new RuntimeException("Paziente non trovato.");
    }
  }
}
