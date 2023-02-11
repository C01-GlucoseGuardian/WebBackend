package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.paziente.service.PazienteServiceConcrete;
import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.NumeroTelefonoDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.NumeroTelefonoDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class PazienteServiceTest {

  @Mock
  PazienteDao pazienteDao;
  @Mock
  UtenteDao utenteDao;
  @Mock
  DottoreDao dottoreDao;
  @Mock
  NumeroTelefonoDao numeroTelefonoDao;
  @Mock
  FarmacoDao farmacoDao;
  @Mock
  AssunzioneFarmacoDao assunzioneFarmacoDao;
  @Mock
  TerapiaDao terapiaDao;
  @Mock
  MailService mailService;
  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  PazienteServiceConcrete pazienteServiceConcrete;

  /**
   * CF already exists
   */
  @Test
  public void testSave1() {
    when(pazienteDao.existsById(any(String.class))).thenReturn(true);

    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertThrows(DuplicatedEntityException.class, () -> pazienteServiceConcrete.save(input));
  }

  /**
   * Email already exists
   */
  @Test
  public void testSave2() {
    when(utenteDao.existsByEmail(any(String.class))).thenReturn(true);

    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertThrows(DuplicatedEntityException.class, () -> pazienteServiceConcrete.save(input));
  }


  /**
   * Date invalid
   */
  @Test
  public void testSave3() {
    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09----06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertThrows(IllegalArgumentException.class, () -> pazienteServiceConcrete.save(input));
  }

  /**
   * Dottore not found
   */
  @Test
  public void testSave4() {
    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertThrows(UserNotFoundException.class, () -> pazienteServiceConcrete.save(input));
  }

  /**
   * Farmaco not found
   */
  @Test
  public void testSave5() {
    when(dottoreDao.findById(any(String.class))).thenReturn(Optional.of(new Dottore()));

    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertThrows(EntityNotFoundException.class, () -> pazienteServiceConcrete.save(input));
  }

  /**
   * Save failure
   */
  @Test
  public void testSave6() throws DuplicatedEntityException, EntityNotFoundException {
    when(dottoreDao.findById(any(String.class))).thenReturn(Optional.of(new Dottore()));
    when(farmacoDao.findById(any(Long.class))).thenReturn(Optional.of(new Farmaco()));

    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertFalse(pazienteServiceConcrete.save(input));
  }

  /**
   * Everything ok
   */
  @Test
  public void testSave7() throws DuplicatedEntityException, EntityNotFoundException {
    final int[] counter = {0};
    when(pazienteDao.existsById(any(String.class))).then(invocation -> counter[0]++ > 0);

    when(dottoreDao.findById(any(String.class))).thenReturn(Optional.of(new Dottore()));
    when(farmacoDao.findById(any(Long.class))).thenReturn(Optional.of(new Farmaco()));

    PazienteDto input = new PazienteDto();
    input.setIdDottore("LDAMTT01H09B963Z");
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);
    List<AssunzioneFarmacoDto> farmaci = new ArrayList<>();
    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);
    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    assertTrue(pazienteServiceConcrete.save(input));
  }
}


