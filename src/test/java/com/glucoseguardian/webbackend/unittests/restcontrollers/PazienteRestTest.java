package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.paziente.rest.PazienteRest;
import com.glucoseguardian.webbackend.paziente.service.PazienteServiceStub;
import com.glucoseguardian.webbackend.paziente.service.TestPazienteService;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.NumeroTelefonoDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(PazienteRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({PazienteServiceStub.class, TestPazienteService.class})
public class PazienteRestTest extends AbstractRestTest {

  @Autowired
  PazienteServiceStub serviceStub;

  @MockBean
  Utils utils;

  /**
   * Test ID TC_5.1.
   */
  @Test
  public void testSave1() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("");
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

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));
    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.2.
   */
  @Test
  public void testSave2() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("");
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

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.3.
   */
  @Test
  public void testSave3() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963YQ");
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

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto(
        "La lunghezza del codice fiscale deve essere di 16 caratteri");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.4.
   */
  @Test
  public void testSave4() throws Exception {
    serviceStub.duplicatedId = true;

    PazienteDto input = new PazienteDto();
    input.setNome("Martina");
    input.setCognome("Aldi");
    input.setCodiceFiscale("MRTLDA01L55C514M");
    input.setSesso("M");
    input.setDataNascita("15/07/2001");
    input.setEmail("paziente2@glucoseguardian.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Cerignola, Via San Samuele 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("Codice fiscale gi?? presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.5.
   */
  @Test
  public void testSave5() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("Sesso");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("il sesso non ?? valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.6.
   */
  @Test
  public void testSave6() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/20010");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.7.
   */
  @Test
  public void testSave7() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/3000");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La data di nascita ?? nel futuro");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.8.
   */
  @Test
  public void testSave8() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldihotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("L'email non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.9.
   */
  @Test
  public void testSave9() throws Exception {
    serviceStub.duplicatedEmail = true;

    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("paziente@glucoseguardian.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("Email gi?? presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.10.
   */
  @Test
  public void testSave10() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3911697894444444444");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("il campo numero di telefono non rispetta il formato");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.11.
   */
  @Test
  public void testSave11() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo(
        "Caserta Via Vico 1 ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.12.
   */
  @Test
  public void testSave12() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null,
        "3911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894391169789439116978943911697894")));
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("campo numeri utili non valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.13.
   */
  @Test
  public void testSave13() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(null);
    input.setTipoDiabete("Tipo 1");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("numeriUtili non pu?? essere assente");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.14.
   */
  @Test
  public void testSave14() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete(
        "Tipo 111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    input.setComorbilita("");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del tipo diabete non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.15.
   */
  @Test
  public void testSave15() throws Exception {
    PazienteDto input = new PazienteDto();
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
    input.setComorbilita(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    input.setFarmaciAssunti("");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza della commorbilit?? non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.16.
   */
  @Test
  public void testSave16() throws Exception {
    PazienteDto input = new PazienteDto();
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
    input.setFarmaciAssunti(
        "DiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezide");
    input.setPeriodoDiMonitoraggio(14);

    AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
    assunzioneFarmaco.setNomeFarmaco("Diabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");

    input.setTerapia(new TerapiaDto(null, null, null, null, List.of(assunzioneFarmaco)));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza di farmaci assunti non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.17.
   */
  @Test
  public void testSave17() throws Exception {
    PazienteDto input = new PazienteDto();
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
    for(int i=0; i < 21; i++) {
      AssunzioneFarmacoDto assunzioneFarmaco = new AssunzioneFarmacoDto();
      assunzioneFarmaco.setNomeFarmaco("Diabrezide");
      assunzioneFarmaco.setIdFarmaco(0L);
      assunzioneFarmaco.setDosaggio("1");
      assunzioneFarmaco.setOrarioAssunzione("20:00");
      assunzioneFarmaco.setViaDiSomministrazione("orale");
      assunzioneFarmaco.setNoteAggiuntive("");
      farmaci.add(assunzioneFarmaco);
    }

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("Numero Farmaci non valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.18.
   */
  @Test
  public void testSave18() throws Exception {
    PazienteDto input = new PazienteDto();
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
    assunzioneFarmaco.setNomeFarmaco("DiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezideDiabrezide");
    assunzioneFarmaco.setIdFarmaco(0L);
    assunzioneFarmaco.setDosaggio("1");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("il nome del farmaco ?? della lunghezza errata");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.19.
   */
  @Test
  public void testSave19() throws Exception {
    PazienteDto input = new PazienteDto();
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
    assunzioneFarmaco.setDosaggio("1001ababababababa1001ababababababa1001ababababababa1001ababababababa");
    assunzioneFarmaco.setOrarioAssunzione("20:00");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del dosaggio ?? errata");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.20.
   */
  @Test
  public void testSave20() throws Exception {
    PazienteDto input = new PazienteDto();
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
    assunzioneFarmaco.setOrarioAssunzione("20:000");
    assunzioneFarmaco.setViaDiSomministrazione("orale");
    assunzioneFarmaco.setNoteAggiuntive("");
    farmaci.add(assunzioneFarmaco);

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("L'orario di assunzione non ?? valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.21.
   */
  @Test
  public void testSave21() throws Exception {
    PazienteDto input = new PazienteDto();
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
    assunzioneFarmaco.setViaDiSomministrazione("oraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleoraleorale");
    assunzioneFarmaco.setNoteAggiuntive("Nulla");
    farmaci.add(assunzioneFarmaco);

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("La lunghezza della via di somministrazione ?? errata");
    testSave(input, status().isBadRequest(), oracolo);
  }
  /**
   * Test ID TC_5.22.
   */
  @Test
  public void testSave22() throws Exception {
    PazienteDto input = new PazienteDto();
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
    assunzioneFarmaco.setNoteAggiuntive("NullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullaNullavNullaNullaNullaNullaNullaNullaNullaNullaNulla");
    farmaci.add(assunzioneFarmaco);

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("Le note aggiuntive superano la lunghezza consentita");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.23.
   */
  @Test
  public void testSave23() throws Exception {
    PazienteDto input = new PazienteDto();
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
    input.setPeriodoDiMonitoraggio(-1);

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

    RisultatoDto oracolo = new RisultatoDto("periodo di monitoraggio non valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_5.24.
   */
  @Test
  public void testSave24() throws Exception {
    PazienteDto input = new PazienteDto();
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

    RisultatoDto oracolo = new RisultatoDto("Paziente inserito correttamente");
    testSave(input, status().isOk(), oracolo);
  }


  /**
   * Test: Cognome troppo lungo
   */
  @Test
  public void testSave25() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("AldiAldiAldiAldiAldiAldiAldiAldiAldi");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test: Nome troppo lungo
   */
  @Test
  public void testSave26() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("MatteoAldiAldiAldiAldiAldiAldiAldiAldi");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test: Indirizzo troppo breve
   */
  @Test
  public void testSave27() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Via");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test: Tipo diabete troppo breve
   */
  @Test
  public void testSave28() throws Exception {
    PazienteDto input = new PazienteDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setNumeriUtili(List.of(new NumeroTelefonoDto(null, "3911697894")));
    input.setTipoDiabete("");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del tipo diabete non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test: Nessun farmaco
   */
  @Test
  public void testSave29() throws Exception {
    PazienteDto input = new PazienteDto();
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

    input.setTerapia(new TerapiaDto(null, null, null, null, farmaci));

    RisultatoDto oracolo = new RisultatoDto("Numero Farmaci non valido");
    testSave(input, status().isBadRequest(), oracolo);
  }


  @WithMockUser(username = "dottore@glucoseguardian.it", authorities = {"DOTTORE"})
  // Mock User dottore with tipo Dottore
  private void testSave(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {
    performSync(post("/paziente/save").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }

  @BeforeEach
  public void setup() {
    serviceStub.duplicatedId = false;
    serviceStub.duplicatedEmail = false;

    // Mock dottore
    Dottore dottore = new Dottore();
    dottore.setCodiceFiscale("RSSNTN90A01H703B");
    dottore.setEmail("dottore@glucoseguardian.it");
    when(utils.getAuthentication()).thenReturn(createSecurityContext(dottore));
  }

}


