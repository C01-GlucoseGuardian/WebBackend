package com.glucoseguardian.webbackend.application.service.terapia;

import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.List;

/**
 * This is a TutoreService Interface.
 */
public interface TerapiaServiceInterface {

  boolean updateTerapia(String codiceFiscalePaziente, List<AssunzioneFarmaco> listaFarmaci);

  TerapiaDto findTerapia(Long idTerapia);

  TerapiaDto findByPaziente(String codiceFiscalePaziente);
}
