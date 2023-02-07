package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
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
  private AssunzioneFarmacoDao assunzioneFarmacoDao;
  @Autowired
  private DottoreDao dottoreDao;
  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private TerapiaDao terapiaDao;

  @Override
  public AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco) throws EntityNotFoundException {
    AssunzioneFarmaco result = assunzioneFarmacoDao.findById(idAssunzioneFarmaco).orElse(null);
    if (result != null) {
      return AssunzioneFarmacoDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("AssunzioneFarmaco non trovato.");
    }
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia)
      throws EntityNotFoundException {
    Terapia result = terapiaDao.findById(idTerapia).orElse(null);
    if (result != null) {
      List<AssunzioneFarmacoDto> list = new ArrayList<>();
      for (AssunzioneFarmaco assunzioneFarmaco : result.getAssunzioneFarmacos()) {
        list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
      }
      ListDto<AssunzioneFarmacoDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new EntityNotFoundException("Terapia non trovata.");
    }
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente)
      throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      List<AssunzioneFarmacoDto> list = new ArrayList<>();
      for (AssunzioneFarmaco assunzioneFarmaco : result.getTerapia().getAssunzioneFarmacos()) {
        list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
      }
      ListDto<AssunzioneFarmacoDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }
}
