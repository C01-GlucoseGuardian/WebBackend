package com.glucoseguardian.webbackend.assunzionefarmaco.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
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
  public ListDto<AssunzioneFarmacoDto> findByTerapia(Long idTerapia) {
    List<AssunzioneFarmaco> result = assunzioneFarmacoDao.findByTerapia_Id(idTerapia);
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    for (AssunzioneFarmaco assunzioneFarmaco : result) {
      list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
    }
    ListDto<AssunzioneFarmacoDto> listDto = new ListDto<>(list);
    return listDto;
  }

  @Override
  public ListDto<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente) {
    List<AssunzioneFarmaco> result = assunzioneFarmacoDao.findByTerapia_Paziente_CodiceFiscale(
        codiceFiscalePaziente);
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    for (AssunzioneFarmaco assunzioneFarmaco : result) {
      list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
    }
    ListDto<AssunzioneFarmacoDto> listDto = new ListDto<>(list);
    return listDto;
  }
}