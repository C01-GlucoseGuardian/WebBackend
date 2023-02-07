package com.glucoseguardian.webbackend.terapia.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of TerapiaServiceInterface.
 */
@Service
public class TerapiaServiceConcrete implements TerapiaServiceInterface {

  @Autowired
  private TerapiaDao terapiaDao;
  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private FarmacoDao farmacoDao;

  @Override
  public boolean updateTerapia(String codiceFiscalePaziente,
      List<AssunzioneFarmacoDto> listaFarmaci) throws EntityNotFoundException {

    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result == null) {
      throw new UserNotFoundException("Paziente non trovato");
    }

    Terapia terapia = result.getTerapia();

    List<AssunzioneFarmaco> assunzioneFarmacos = new ArrayList<>();
    for (AssunzioneFarmacoDto assunzioneFarmaco : listaFarmaci) {
      Optional<Farmaco> farmaco = farmacoDao.findById(assunzioneFarmaco.getIdFarmaco());
      if (farmaco.isPresent()) {
        // Add ":00" if missing, sql.Time wants "HH:MM:SS"
        StringBuilder orarioAssunzione = new StringBuilder(assunzioneFarmaco.getOrarioAssunzione());
        int matches = StringUtils.countMatches(orarioAssunzione.toString(), ":");
        while (matches < 2) {
          orarioAssunzione.append(":00");
          matches++;
        }
        Time time = Time.valueOf(orarioAssunzione.toString());

        AssunzioneFarmaco entity = new AssunzioneFarmaco(farmaco.get(),
            assunzioneFarmaco.getDosaggio(), time, assunzioneFarmaco.getViaDiSomministrazione(),
            assunzioneFarmaco.getNoteAggiuntive());
        entity.setTerapia(terapia);
        assunzioneFarmacos.add(entity);
      } else {
        throw new EntityNotFoundException(
            "Farmaco " + assunzioneFarmaco.getIdFarmaco() + " non trovato");
      }
      // always use the same list - otherwise we will find duplicates
      terapia.getAssunzioneFarmacos().clear();
      terapia.getAssunzioneFarmacos().addAll(assunzioneFarmacos);
      terapiaDao.save(terapia);
    }
    return true;
  }

  @Override
  public TerapiaDto findTerapia(Long idTerapia) throws EntityNotFoundException {
    Terapia result = terapiaDao.findById(idTerapia).orElse(null);
    if (result != null) {
      return TerapiaDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("Terapia non trovata.");
    }
  }

  @Override
  public TerapiaDto findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      return TerapiaDto.valueOf(result.getTerapia());
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }
}