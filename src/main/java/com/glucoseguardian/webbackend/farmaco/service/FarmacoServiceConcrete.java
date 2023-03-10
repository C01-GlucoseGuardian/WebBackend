package com.glucoseguardian.webbackend.farmaco.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of FarmacoServiceInterface.
 */
@Service
@Primary
public class FarmacoServiceConcrete implements FarmacoServiceInterface {

  @Autowired
  private FarmacoDao farmacoDao;

  @Override
  public FarmacoDto findById(Long id) throws EntityNotFoundException {
    Farmaco result = farmacoDao.findById(id).orElse(null);
    if (result != null) {
      return FarmacoDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("Farmaco non trovato.");
    }
  }

  @Override
  public ListDto<FarmacoDto> findFarmaco(String ricerca) {
    List<Farmaco> listNome = farmacoDao.findByNomeFarmacoContainingIgnoreCase(ricerca);
    List<Farmaco> listPrincipio = farmacoDao.findByPrincipioAttivoContainingIgnoreCase(ricerca);
    List<Farmaco> listConfezione = farmacoDao.findByConfezioneContainingIgnoreCase(ricerca);

    Set<Farmaco> set = new LinkedHashSet<>(listNome);
    set.addAll(listPrincipio);
    set.addAll(listConfezione);

    List<FarmacoDto> ricercaFarmaci = new ArrayList<>();
    for (Farmaco farmaco : set) {
      ricercaFarmaci.add(FarmacoDto.valueOf(farmaco));
    }
    ListDto<FarmacoDto> listDto = new ListDto<>(ricercaFarmaci);
    return listDto;
  }
}
