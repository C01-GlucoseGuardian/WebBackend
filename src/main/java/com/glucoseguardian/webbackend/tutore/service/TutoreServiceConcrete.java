package com.glucoseguardian.webbackend.tutore.service;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
  private PazienteDao pazienteDao;
  @Autowired
  private TutoreDao tutoreDao;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MailService mailService;

  @Override
  public TutoreDto findByCodiceFiscale(String codiceFiscaleTutore) throws UserNotFoundException {
    Tutore result = tutoreDao.findById(codiceFiscaleTutore).orElse(null);
    if (result != null) {
      return TutoreDto.valueOf(result);
    } else {
      throw new UserNotFoundException("Tutore non trovato.");
    }
  }

  @Override
  public ListDto<TutoreDto> findByPaziente(String codiceFiscalePaziente)
      throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result == null) {
      throw new UserNotFoundException("Paziente non trovato.");
    }
    List<Tutore> list = result.getTutori();
    List<TutoreDto> tutoreDtos = new ArrayList<>();
    for (Tutore tutore : list) {
      tutoreDtos.add(TutoreDto.valueOf(tutore));
    }
    ListDto<TutoreDto> listDto = new ListDto<>(tutoreDtos);
    return listDto;
  }

  @Override
  public boolean save(TutoreDto dto) throws UserNotFoundException {
    DateFormat dateInputFormat = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dateSqlFormat = new SimpleDateFormat("yyyy-MM-dd");

    String date;
    try {
      date = dateSqlFormat.format(dateInputFormat.parse(dto.getDataNascita()));
    } catch (ParseException parse) {
      throw new IllegalArgumentException("Data non valida");
    }

    String cf = dto.getPazienteList().get(0).getCodiceFiscale();

    if (!pazienteDao.existsById(cf)) {
      throw new UserNotFoundException("Paziente non trovato.");
    }

    String randomPassword = RandomStringUtils.random(16, 0, 0, true, true, null,
        new SecureRandom());

    // Create entity
    Tutore tutoreEntity = new Tutore(dto.getCodiceFiscale(), dto.getNome(), dto.getCognome(),
        Date.valueOf(date), dto.getIndirizzo(), dto.getTelefono(), dto.getEmail(),
        passwordEncoder.encode(randomPassword), dto.getSesso().charAt(0), null,
        Collections.emptyList());
    tutoreDao.save(tutoreEntity);

    Paziente paziente = pazienteDao.findById(cf).orElse(null);
    if (paziente == null) {
      throw new UserNotFoundException("Paziente non trovato.");
    }

    List<Tutore> tutori = paziente.getTutori();
    tutori.add(tutoreEntity);
    paziente.setTutori(tutori);
    pazienteDao.save(paziente);

    // Check if entity is correctly saved
    boolean result = tutoreDao.existsById(tutoreEntity.getCodiceFiscale());
    if (result) {
      mailService.sendNotification("Account registrato",
          "Ciao " + dto.getNome() + ",\nBenvenut* nella nostra piattaforma, "
              + "questa è la tua password temporanea: " + randomPassword
              + "\nTi invitiamo a cambiarla il prima possibile.", dto.getEmail());
    }

    return result;
  }
}
