package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceConcrete;
import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.NotificaDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificaServiceTest {

  @Mock
  NotificaDao notificaDao;

  @Mock
  AdminDao adminDao;

  @Mock
  DottoreDao dottoreDao;

  @Mock
  PazienteDao pazienteDao;
  @Mock
  TutoreDao tutoreDao;
  @Mock
  MailService mailService;
  @Mock
  FirebaseService firebaseService;

  @InjectMocks
  NotificaServiceConcrete notificaService;


  /**
   * Nessun destinatario
   */
  @Test
  public void testSend1() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");

    assertThrows(IllegalArgumentException.class, () -> notificaService.send(notifica));
  }

  /**
   * Admin non trovato
   */
  @Test
  public void testSend2() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setAdminDestinatario("RSSMRA80A01F205X");

    assertThrows(UserNotFoundException.class, () -> notificaService.send(notifica));
  }

  /**
   * Dottore non trovato
   */
  @Test
  public void testSend3() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setDottoreDestinatario("BNCLDA72E17A535H");

    assertThrows(UserNotFoundException.class, () -> notificaService.send(notifica));
  }

  /**
   * Paziente destinatario non trovato
   */
  @Test
  public void testSend4() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteDestinatario("MRTLDA01L55C514M");

    assertThrows(UserNotFoundException.class, () -> notificaService.send(notifica));
  }

  /**
   * Paziente oggetto non trovato
   */
  @Test
  public void testSend5() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteOggetto("MRTLDA01L55C514M");

    assertThrows(UserNotFoundException.class, () -> notificaService.send(notifica));
  }

  /**
   * Tutore non trovato
   */
  @Test
  public void testSend6() {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");

    assertThrows(UserNotFoundException.class, () -> notificaService.send(notifica));
  }

  /**
   * Save fails
   */
  @Test
  public void testSend7() throws UserNotFoundException {
    when(dottoreDao.findById(any(String.class))).thenReturn(Optional.of(new Dottore()));
    when(pazienteDao.findById(any(String.class))).thenReturn(Optional.of(new Paziente()));
    when(tutoreDao.findById(any(String.class))).thenReturn(Optional.of(new Tutore()));
    when(adminDao.findById(any(String.class))).thenReturn(Optional.of(new Admin()));

    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteDestinatario("MRTLDA01L55C514M");
    notifica.setDottoreDestinatario("BNCLDA72E17A535H");
    notifica.setAdminDestinatario("RSSMRA80A01F205X");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");
    notifica.setPazienteOggetto("MRTLDA01L55C514M");

    assertFalse(notificaService.send(notifica));
  }

  /**
   * Save ok
   */
  @Test
  public void testSend8() throws UserNotFoundException {
    when(dottoreDao.findById(any(String.class))).thenReturn(Optional.of(new Dottore()));
    when(pazienteDao.findById(any(String.class))).thenReturn(Optional.of(new Paziente()));
    when(tutoreDao.findById(any(String.class))).thenReturn(Optional.of(new Tutore()));
    when(adminDao.findById(any(String.class))).thenReturn(Optional.of(new Admin()));
    when(notificaDao.existsById(any(Long.class))).thenReturn(true);

    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteDestinatario("MRTLDA01L55C514M");
    notifica.setDottoreDestinatario("BNCLDA72E17A535H");
    notifica.setAdminDestinatario("RSSMRA80A01F205X");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");
    notifica.setPazienteOggetto("MRTLDA01L55C514M");

    assertTrue(notificaService.send(notifica));
  }
}