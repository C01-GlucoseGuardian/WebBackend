package com.glucoseguardian.webbackend.application.service.glicemia;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.GlicemiaDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.GlicemiaDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Implementazione concreta di Glicemia Service.
 */
@Service
public class GlicemiaServiceConcrete implements GlicemiaServiceInterface {

  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private GlicemiaDao glicemiaDao;

  @Override
  public GlicemiaDto getLast(String codiceFiscale) throws EntityNotFoundException {
    Paziente paziente = getPaziente(codiceFiscale);
    Glicemia result = glicemiaDao.findTopByPazienteOrderByDataOraDesc(paziente).orElse(null);

    if (result != null) {
      return GlicemiaDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("Nessuna glicemia trovata.");
    }
  }

  @Override
  public ListDto<GlicemiaDto> getRange(String codiceFiscale, long start, long end)
      throws UserNotFoundException {
    Paziente paziente = getPaziente(codiceFiscale);

    List<Glicemia> list = glicemiaDao.findByPazienteAndDataOraBetweenOrderByDataOraDesc(
        paziente, new Timestamp(start), new Timestamp(end));

    return new ListDto<>(list.stream().map(GlicemiaDto::valueOf).toList());
  }

  @Override
  public boolean send(String codiceFiscale, ListDto<GlicemiaDto> list)
      throws UserNotFoundException {
    Paziente paziente = getPaziente(codiceFiscale);
    for (GlicemiaDto dto : list.getList()) {
      Timestamp dateTime = new Timestamp(dto.getTimestamp());
      Glicemia glicemia = new Glicemia(dto.getLivelloGlucosio(), dateTime);
      glicemia.setPaziente(paziente);
      glicemiaDao.save(glicemia);
    }
    return true;
  }

  private @NonNull Paziente getPaziente(String codiceFiscale) throws UserNotFoundException {
    Optional<Paziente> paziente = pazienteDao.findById(codiceFiscale);
    if (paziente.isPresent()) {
      return paziente.get();
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }

}
