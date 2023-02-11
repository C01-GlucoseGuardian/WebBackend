package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import com.glucoseguardian.webbackend.terapia.service.TerapiaServiceConcrete;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TerapiaServiceTest {

  @Mock
  TerapiaDao terapiaDao;

  @Mock
  AssunzioneFarmacoDao assunzioneFarmacoDao;

  @Mock
  FarmacoDao farmacoDao;

  @InjectMocks
  TerapiaServiceConcrete terapiaService;


  /**
   * Paziente non trovato
   */
  @Test
  public void testSend1() {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setIdFarmaco(1L);
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("da assumere prima di un pasto");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);
    input.setIdPaziente("MRTLDA01L55C514M");

    assertThrows(UserNotFoundException.class,
        () -> terapiaService.updateTerapia(input.getIdPaziente(), input.getFarmaci()));
  }

  /**
   * Farmaco non trovato.
   */
  @Test
  public void testSend2() {
    when(terapiaDao.findByPaziente_codiceFiscale(any(String.class))).thenReturn(Optional.of(new Terapia()));

    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setIdFarmaco(1L);
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("da assumere prima di un pasto");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);
    input.setIdPaziente("MRTLDA01L55C514M");

    assertThrows(EntityNotFoundException.class,
        () -> terapiaService.updateTerapia(input.getIdPaziente(), input.getFarmaci()));

  }

  /**
   * Ora invalida.
   */
  @Test
  public void testSend3() {
    Terapia terapia = new Terapia();
    terapia.setAssunzioneFarmacos(new ArrayList<>());
    when(terapiaDao.findByPaziente_codiceFiscale(any(String.class))).thenReturn(Optional.of(terapia));

    when(farmacoDao.findById(any(Long.class))).thenReturn(Optional.of(new Farmaco()));

    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setIdFarmaco(1L);
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("AAAA");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("da assumere prima di un pasto");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);
    input.setIdPaziente("MRTLDA01L55C514M");

    assertThrows(NumberFormatException.class,
        () -> terapiaService.updateTerapia(input.getIdPaziente(), input.getFarmaci()));

  }

  /**
   * Save ok.
   */
  @Test
  public void testSend4() throws EntityNotFoundException {
    Terapia terapia = new Terapia();
    terapia.setAssunzioneFarmacos(new ArrayList<>());
    when(terapiaDao.findByPaziente_codiceFiscale(any(String.class))).thenReturn(Optional.of(terapia));

    when(farmacoDao.findById(any(Long.class))).thenReturn(Optional.of(new Farmaco()));

    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setIdFarmaco(1L);
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("da assumere prima di un pasto");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);
    input.setIdPaziente("MRTLDA01L55C514M");

    assertTrue(terapiaService.updateTerapia(input.getIdPaziente(), input.getFarmaci()));

  }
}
