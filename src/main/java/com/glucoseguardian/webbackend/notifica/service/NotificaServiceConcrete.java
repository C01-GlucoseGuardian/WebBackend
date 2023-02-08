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
import java.util.Optional;
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
  @Autowired
  private MailService mailService;
  @Autowired
  private FirebaseService firebaseService;

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
  public boolean send(NotificaDto notificaDto) throws UserNotFoundException {
    Notifica notifica = new Notifica(notificaDto.getMessaggio(),
        new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()),
        notificaDto.getStato());

    List<String> destinatari = new ArrayList<>();

    if (notificaDto.getDottoreDestinatario() != null) {
      Optional<Dottore> utente = dottoreDao.findById(notificaDto.getDottoreDestinatario());
      if (utente.isPresent()) {
        notifica.setDottoreDestinatario(utente.get());
        destinatari.add(utente.get().getEmail());
      } else {
        throw new UserNotFoundException("Dottore non trovato");
      }
    }

    if (notificaDto.getPazienteDestinatario() != null) {
      Optional<Paziente> utente = pazienteDao.findById(notificaDto.getPazienteDestinatario());
      if (utente.isPresent()) {
        notifica.setPazienteDestinatario(utente.get());
        destinatari.add(utente.get().getEmail());
      } else {
        throw new UserNotFoundException("Paziente non trovato");
      }
    }

    if (notificaDto.getPazienteOggetto() != null) {
      Optional<Paziente> utente = pazienteDao.findById(notificaDto.getPazienteOggetto());
      if (utente.isPresent()) {
        notifica.setPazienteOggetto(utente.get());
      } else {
        throw new UserNotFoundException("Paziente non trovato");
      }
    }

    if (notificaDto.getTutoreDestinatario() != null) {
      Optional<Tutore> utente = tutoreDao.findById(notificaDto.getTutoreDestinatario());
      if (utente.isPresent()) {
        notifica.setTutoreDestinatario(utente.get());
        destinatari.add(utente.get().getEmail());
      } else {
        throw new UserNotFoundException("Tutore non trovato");
      }
    }

    if (notificaDto.getAdminDestinatario() != null) {
      Optional<Admin> utente = adminDao.findById(notificaDto.getAdminDestinatario());
      if (utente.isPresent()) {
        notifica.setAdminDestinatario(utente.get());
        destinatari.add(utente.get().getEmail());
      } else {
        throw new UserNotFoundException("Admin non trovato");
      }
    }

    notificaDao.save(notifica);

    // Check if entity is correctly saved
    boolean result = notificaDao.existsById(notifica.getId());

    if (result) {
      mailService.sendNotification("Hai una nuova notifica", notifica.getMessaggio(), destinatari);
      firebaseService.sendNotification("Hai una nuova notifica", notifica.getMessaggio(),
          destinatari);
    }

    return result;
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
  public boolean updateStato(long idNotifica, Integer newStato) throws EntityNotFoundException {
    Notifica result = notificaDao.findById(idNotifica).orElse(null);
    if (result != null) {
      result.setStato(newStato);
      notificaDao.save(result);
      return true;
    } else {
      throw new EntityNotFoundException("Notifica non trovata.");
    }
  }
}

