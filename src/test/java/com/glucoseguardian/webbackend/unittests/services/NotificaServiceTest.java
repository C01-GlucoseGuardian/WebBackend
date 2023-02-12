package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceConcrete;
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
import java.util.List;
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

  /**
   * Notifica non trovata.
   */
  @Test
  public void testUpdateStato1() {
    assertThrows(EntityNotFoundException.class, () -> notificaService.updateStato(0L, 0));
  }

  /**
   * Notifica trovata.
   */
  @Test
  public void testUpdateStato2() throws EntityNotFoundException {
    Notifica notifica = new Notifica();
    notifica.setId(1L);
    notifica.setStato(0);
    notifica.setData(Date.valueOf("2022-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));
    Tutore tutore = new Tutore();
    tutore.setCodiceFiscale("TTOGNN65M07G273H");
    notifica.setTutoreDestinatario(tutore);
    Paziente paziente = new Paziente();
    paziente.setCodiceFiscale("MRTLDA01L55C514M");
    notifica.setPazienteDestinatario(paziente);
    Admin admin = new Admin();
    admin.setCodiceFiscale("RSSMRA80A01F205X");
    notifica.setAdminDestinatario(admin);


    when(notificaDao.findById(1L)).thenReturn(Optional.of(notifica));
    assertTrue(notificaService.updateStato(1L, 1));
  }

  /**
   * Notifica non trovata.
   */
  @Test
  public void testGet1() {
    assertThrows(EntityNotFoundException.class, () -> notificaService.findById(0L));
  }

  /**
   * Notifica trovata.
   */
  @Test
  public void testGet2() throws EntityNotFoundException {
    Notifica notifica = new Notifica();
    notifica.setId(1L);
    notifica.setStato(0);
    notifica.setData(Date.valueOf("2022-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));
    Dottore dottore = new Dottore();
    dottore.setCodiceFiscale("BNCLDA72E17A535H");
    notifica.setDottoreDestinatario(dottore);
    Paziente paziente = new Paziente();
    paziente.setCodiceFiscale("MRTLDA01L55C514M");
    notifica.setPazienteOggetto(paziente);
    when(notificaDao.findById(1L)).thenReturn(Optional.of(notifica));
    assertEquals(NotificaDto.valueOf(notifica), notificaService.findById(1L));
  }


  /**
   * Admin non trovato.
   */
  @Test
  public void testGetByAdmin1() {
    assertThrows(UserNotFoundException.class, () -> notificaService.findByAdmin("RSSMRA80A01F205X"));
  }

  /**
   * Admin trovato.
   */
  @Test
  public void testGetByAdmin2() throws UserNotFoundException {
    Admin admin = new Admin();
    admin.setCodiceFiscale("RSSMRA80A01F205X");
    Paziente paziente = new Paziente();
    paziente.setCodiceFiscale("MRTLDA01L55C514M");

    Notifica notifica = new Notifica();
    notifica.setAdminDestinatario(admin);
    notifica.setPazienteOggetto(paziente);
    notifica.setData(Date.valueOf("2023-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));

    admin.setNotifiche(List.of(notifica));
    when(adminDao.findById("RSSMRA80A01F205X")).thenReturn(Optional.of(admin));
    ListDto<NotificaDto> result = notificaService.findByAdmin("RSSMRA80A01F205X");
    ListDto<NotificaDto> oracolo = new ListDto<>(List.of(NotificaDto.valueOf(notifica)));
    assertEquals(oracolo, result);
  }


  /**
   * Dottore non trovato.
   */
  @Test
  public void testGetByDottore1() {
    assertThrows(UserNotFoundException.class, () -> notificaService.findByDottore("BNCLDA72E17A535H"));
  }

  /**
   * Dottore trovato.
   */
  @Test
  public void testGetByDottore2() throws UserNotFoundException {
    Dottore dottore = new Dottore();
    dottore.setCodiceFiscale("BNCLDA72E17A535H");

    Notifica notifica = new Notifica();
    notifica.setDottoreDestinatario(dottore);
    notifica.setData(Date.valueOf("2023-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));

    dottore.setNotifiche(List.of(notifica));
    when(dottoreDao.findById("BNCLDA72E17A535H")).thenReturn(Optional.of(dottore));
    ListDto<NotificaDto> result = notificaService.findByDottore("BNCLDA72E17A535H");
    ListDto<NotificaDto> oracolo = new ListDto<>(List.of(NotificaDto.valueOf(notifica)));
    assertEquals(oracolo, result);
  }


  /**
   * Paziente non trovato.
   */
  @Test
  public void testGetByPaziente1() {
    assertThrows(UserNotFoundException.class, () -> notificaService.findByPaziente("MRTLDA01L55C514M"));
  }

  /**
   * Paziente trovato.
   */
  @Test
  public void testGetByPaziente2() throws UserNotFoundException {
    Paziente paziente = new Paziente();
    paziente.setCodiceFiscale("MRTLDA01L55C514M");

    Notifica notifica = new Notifica();
    notifica.setPazienteDestinatario(paziente);
    notifica.setPazienteOggetto(paziente);
    notifica.setData(Date.valueOf("2023-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));

    paziente.setNotifiche(List.of(notifica));
    when(pazienteDao.findById("MRTLDA01L55C514M")).thenReturn(Optional.of(paziente));
    ListDto<NotificaDto> result = notificaService.findByPaziente("MRTLDA01L55C514M");
    ListDto<NotificaDto> oracolo = new ListDto<>(List.of(NotificaDto.valueOf(notifica)));
    assertEquals(oracolo, result);
  }

  /**
   * Tutore non trovato.
   */
  @Test
  public void testGetByTutore1() {
    assertThrows(UserNotFoundException.class, () -> notificaService.findByTutore("TTOGNN65M07G273H"));
  }

  /**
   * Tutore trovato.
   */
  @Test
  public void testGetByTutore2() throws UserNotFoundException {
    Tutore tutore = new Tutore();
    tutore.setCodiceFiscale("TTOGNN65M07G273H");

    Notifica notifica = new Notifica();
    notifica.setTutoreDestinatario(tutore);
    notifica.setData(Date.valueOf("2023-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));

    tutore.setNotifiche(List.of(notifica));
    when(tutoreDao.findById("TTOGNN65M07G273H")).thenReturn(Optional.of(tutore));
    ListDto<NotificaDto> result = notificaService.findByTutore("TTOGNN65M07G273H");
    ListDto<NotificaDto> oracolo = new ListDto<>(List.of(NotificaDto.valueOf(notifica)));
    assertEquals(oracolo, result);
  }

}