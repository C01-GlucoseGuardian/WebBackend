package com.glucoseguardian.webbackend.dottore.service;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of DottoreServiceInterface.
 */
@Service
@Primary
public class DottoreServiceConcrete implements DottoreServiceInterface {

  @Autowired
  private DottoreDao dottoreDao;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AdminDao adminDao;
  @Autowired
  private MailService mailService;
  @Autowired
  private FirebaseService firebase;

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
  public DottoreDto findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException {
    Dottore dottore = dottoreDao.findByPazientes_codiceFiscale(codiceFiscalePaziente).orElse(null);
    if (dottore == null) {
      throw new UserNotFoundException("Dottore non trovato.");
    }
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
  public boolean updateStato(String codiceFiscaleDottore, int nuovoStato, String codiceFiscaleAdmin)
      throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      result.setStato(nuovoStato);
      Admin admin = adminDao.findById(codiceFiscaleAdmin).orElse(null);
      result.setConvalidatoDa(admin);
      dottoreDao.save(result);
      return true;
    } else {
      throw new UserNotFoundException("Dottore non trovato.");
    }
  }

  @Override
  public boolean save(DottoreDto dto) {
    DateFormat dateInputFormat = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dateSqlFormat = new SimpleDateFormat("yyyy-MM-dd");

    String date;
    try {
      date = dateSqlFormat.format(dateInputFormat.parse(dto.getDataNascita()));
    } catch (ParseException parse) {
      throw new IllegalArgumentException("Data non valida");
    }

    // Create entity
    Dottore entity = new Dottore(dto.getCodiceFiscale(), dto.getNome(), dto.getCognome(),
        Date.valueOf(date), dto.getIndirizzo(), dto.getTelefono(), dto.getEmail(),
        passwordEncoder.encode(dto.getPassword()), dto.getSesso().charAt(0), null,
        dto.getSpecializzazione(), dto.getCodiceAlbo(), dto.getNomeStruttura(),
        dto.getIndirizzoStruttura(), 0);

    // persist the new entity
    dottoreDao.save(entity);

    // Check if entity is correctly saved
    boolean result = dottoreDao.existsById(entity.getCodiceFiscale());

    if (result) {
      // Send notification to admins
      mailService.sendNotification("Nuovo dottore da convalidare",
          "Ciao,\n c'è un nuovo dottore da convalidare: " + entity.getEmail(),
          adminDao.findAll().stream().map(Admin::getEmail).toList());
      firebase.sendNotification("Nuovo dottore da convalidare",
          "Ciao,\n c'è un nuovo dottore da convalidare: " + entity.getEmail(),
          adminDao.findAll().stream().map(Admin::getEmail).toList());
    }

    return result;
  }
}
