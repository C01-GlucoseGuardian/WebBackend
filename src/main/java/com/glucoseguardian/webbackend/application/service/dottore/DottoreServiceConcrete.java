package com.glucoseguardian.webbackend.application.service.dottore;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of DottoreServiceInterface.
 */
@Service
public class DottoreServiceConcrete implements DottoreServiceInterface {

  @Autowired
  private DottoreDao dottoreDao;
  @Autowired
  private PazienteDao pazienteDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public DottoreDto findByCodiceFiscale(String codiceFiscaleDottore) throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      return DottoreDto.valueOf(result);
    } else {
      throw new UserNotFoundException("Dottore non trovato.");
    }
  }

  @Override
  public ListDto<DottoreDto> findByStato(int stato) {
    List<Dottore> result = dottoreDao.findByStato(stato);
    List<DottoreDto> dottoreDtoStato = new ArrayList<>();
    for (Dottore dottore : result) {
      dottoreDtoStato.add(DottoreDto.valueOf(dottore));
    }
    ListDto<DottoreDto> listDto = new ListDto<>(dottoreDtoStato);
    return listDto;
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
  public ListDto<DottoreDto> findAll() {
    List<Dottore> result = dottoreDao.findAll();
    List<DottoreDto> tuttiDottoriDto = new ArrayList<>();
    for (Dottore dottore : result) {
      tuttiDottoriDto.add(DottoreDto.valueOf(dottore));
    }
    ListDto<DottoreDto> listDto = new ListDto<>(tuttiDottoriDto);
    return listDto;
  }

  @Override
  public boolean updateStato(String codiceFiscaleDottore, int nuovoStato)
      throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      result.setStato(nuovoStato);
      dottoreDao.save(result);
      return true;
    } else {
      throw new UserNotFoundException("Dottore non trovato.");
    }
  }

  @Override
  public boolean save(DottoreDto dto) {
    // Create entity
    Dottore entity = new Dottore(dto.getCodiceFiscale(), dto.getNome(), dto.getCognome(),
        Date.valueOf(dto.getDataNascita()), dto.getIndirizzo(), dto.getTelefono(), dto.getEmail(),
        passwordEncoder.encode(dto.getPassword()), dto.getSesso().charAt(0), null,
        dto.getSpecializzazione(), dto.getCodiceAlbo(), dto.getNomeStruttura(),
        dto.getIndirizzoStruttura(), 0);

    // persist the new entity
    dottoreDao.saveAndFlush(entity);

    // Check if entity is correctly saved
    return dottoreDao.existsById(entity.getCodiceFiscale());
  }
}
