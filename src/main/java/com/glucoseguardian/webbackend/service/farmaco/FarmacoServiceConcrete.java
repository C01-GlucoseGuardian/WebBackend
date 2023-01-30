package com.glucoseguardian.webbackend.service.farmaco;

import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of FarmacoServiceInterface.
 */
@Service
public class FarmacoServiceConcrete implements FarmacoServiceInterface {

  @Autowired
  FarmacoDao farmacoDao;

  @Override
  public FarmacoDto findById(Long id) {
    Farmaco result = farmacoDao.findById(id).orElse(null);
    if (result != null) {
      return FarmacoDto.valueOf(result);
    } else {
      throw new RuntimeException("Farmaco non trovato.");
    }
  }

  @Override
  public List<FarmacoDto> findFarmaco(String ricerca) {
    List<Farmaco> list1 = farmacoDao.findBynomeFarmacoContainingIgnoreCase(ricerca);
    List<Farmaco> list2 = farmacoDao.findByprincipioAttivoContainingIgnoreCase(ricerca);
    List<Farmaco> list3 = farmacoDao.findByconfezioneContainingIgnoreCase(ricerca);
    List<FarmacoDto> ricercaFarmaci = new ArrayList<>();
    for (Farmaco farmaco : list1) {
      ricercaFarmaci.add(FarmacoDto.valueOf(farmaco));
    }
    for (Farmaco farmaco : list2) {
      ricercaFarmaci.add(FarmacoDto.valueOf(farmaco));
    }
    for (Farmaco farmaco : list3) {
      ricercaFarmaci.add(FarmacoDto.valueOf(farmaco));
    }
    return ricercaFarmaci;
  }
}
