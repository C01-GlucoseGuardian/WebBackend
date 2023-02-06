package com.glucoseguardian.webbackend.notifica.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.NotificaDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Notifica;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of NotificaServiceInterface.
 */
@Service
public class NotificaServiceConcrete implements NotificaServiceInterface {

  @Autowired
  private NotificaDao notificaDao;
  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private DottoreDao dottoreDao;
  @Autowired
  private TutoreDao tutoreDao;
  @Autowired
  private AdminDao adminDao;

  @Override
  public NotificaDto findById(Long id) throws EntityNotFoundException {
    Notifica result = notificaDao.findById(id).orElse(null);
    if (result != null) {
      return NotificaDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("Notifica non trovata.");
    }
  }

  @Override
  public boolean send(NotificaDto notificaDto) {
    // TODO: Send notifications with email/firebase
    // TODO: Set destinatario/oggetto
    Notifica notifica = new Notifica(notificaDto.getMessaggio(),
        new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()),
        notificaDto.getStato());

    notificaDao.saveAndFlush(notifica);

    return notificaDao.existsById(notifica.getId());
  }

  @Override
  public ListDto<NotificaDto> findByPaziente(String codiceFiscale) throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscale).orElse(null);
    if (result != null) {
      List<NotificaDto> list = new ArrayList<>();
      for (Notifica notifica : result.getNotifiche()) {
        list.add(NotificaDto.valueOf(notifica));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }

  @Override
  public ListDto<NotificaDto> findByDottore(String codiceFiscale) throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscale).orElse(null);
    if (result != null) {
      List<NotificaDto> list = new ArrayList<>();
      for (Notifica notifica : result.getNotifiche()) {
        list.add(NotificaDto.valueOf(notifica));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Dottore non trovato.");
    }
  }

  @Override
  public ListDto<NotificaDto> findByTutore(String codiceFiscale) throws UserNotFoundException {
    Tutore result = tutoreDao.findById(codiceFiscale).orElse(null);
    if (result != null) {
      List<NotificaDto> list = new ArrayList<>();
      for (Notifica notifica : result.getNotifiche()) {
        list.add(NotificaDto.valueOf(notifica));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Tutore non trovato.");
    }
  }

  @Override
  public ListDto<NotificaDto> findByAdmin(String codiceFiscale) throws UserNotFoundException {
    Admin result = adminDao.findById(codiceFiscale).orElse(null);
    if (result != null) {
      List<NotificaDto> list = new ArrayList<>();
      for (Notifica notifica : result.getNotifiche()) {
        list.add(NotificaDto.valueOf(notifica));
      }
      ListDto<NotificaDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Admin non trovato.");
    }
  }

  @Override
  public NotificaDto updateStato(long idNotifica, Integer newStato) throws EntityNotFoundException {
    Notifica result = notificaDao.findById(idNotifica).orElse(null);
    if (result != null) {
      NotificaDto notificaDto = NotificaDto.valueOf(result);
      notificaDto.setStato(newStato);
      return notificaDto;
    } else {
      throw new EntityNotFoundException("Notifica non trovata.");
    }
  }
}

