package com.glucoseguardian.webbackend.application.service.tutore;

import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of TutoreServiceInterface.
 */
@Service
public class TutoreServiceConcrete implements TutoreServiceInterface {

  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  TutoreDao tutoreDao;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) {
    Tutore result = tutoreDao.findById(codiceFiscaleTutore).orElse(null);
    if (result != null) {
      return TutoreDto.valueOf(result);
    } else {
      throw new RuntimeException("Tutore non trovato.");
    }
  }

  @Override
  public List<TutoreDto> findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result == null) {
      throw new RuntimeException("Paziente non trovato.");
    }
    List<Tutore> list = result.getProfiliTutore();
    List<TutoreDto> tutoreDtos = new ArrayList<>();
    for (Tutore tutore : list) {
      tutoreDtos.add(TutoreDto.valueOf(tutore));
    }
    return tutoreDtos;
  }

  @Override
  public boolean save(TutoreDto dto) {

    String cf = dto.getPazienteList().get(0).getCodiceFiscale();
    Paziente paziente = pazienteDao.findById(cf).orElse(null);

    if (paziente == null) {
      throw new RuntimeException("Paziente non trovato.");
    }

    //TODO: send email with password

    String randomPassword = RandomStringUtils.random(16, 0, 0, true, true, null,
        new SecureRandom());

    // Create entity
    Tutore tutoreEntity = new Tutore(dto.getCodiceFiscale(), dto.getNome(), dto.getCognome(),
        Date.valueOf(dto.getDataNascita()), dto.getIndirizzo(), dto.getTelefono(), dto.getEmail(),
        passwordEncoder.encode(randomPassword), dto.getSesso().charAt(0), null,
        dto.getRelazioneDiParentela(), List.of(paziente));

    tutoreDao.saveAndFlush(tutoreEntity);

    // Check if entity is correctly saved
    return tutoreDao.existsById(tutoreEntity.getCodiceFiscale());
  }
}
