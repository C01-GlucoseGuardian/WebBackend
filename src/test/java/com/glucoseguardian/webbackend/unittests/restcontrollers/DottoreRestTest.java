package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.dottore.rest.DottoreRest;
import com.glucoseguardian.webbackend.dottore.service.DottoreServiceStub;
import com.glucoseguardian.webbackend.dottore.service.TestDottoreService;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
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

@WebMvcTest(DottoreRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({Utils.class, DottoreServiceStub.class, TestDottoreService.class})
public class DottoreRestTest extends AbstractRestTest {

  @Autowired
  DottoreServiceStub serviceStub;

  @MockBean
  Utils utils;

  /**
   * Test ID TC_2.1.
   */
  @Test
  public void testSave1() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.2.
   */
  @Test
  public void testSave2() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome(
        "Aldiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.3.
   */
  @Test
  public void testSave3() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963YXYALA");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del codice fiscale deve essere di 16 caratteri");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.4.
   */
  @Test
  public void testSave4() throws Exception {
    serviceStub.duplicatedId = true;

    DottoreDto input = new DottoreDto();
    input.setNome("Aldo");
    input.setCognome("Bianchi");
    input.setCodiceFiscale("BNCLDA72E17A535H");
    input.setSesso("M");
    input.setDataNascita("17/05/1972");
    input.setEmail("dottore2@glucoseguardian.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Pozzuoli, Corso umberto 61");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("NA1234");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "Codice fiscale gi?? presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.5.
   */
  @Test
  public void testSave5() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("Sesso");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("il sesso non ?? valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.6.
   */
  @Test
  public void testSave6() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06-2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.7.
   */
  @Test
  public void testSave7() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/3001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La data di nascita ?? nel futuro");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.8.
   */
  @Test
  public void testSave8() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldihotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("L'email non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.9.
   */
  @Test
  public void testSave9() throws Exception {
    serviceStub.duplicatedEmail = true;

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("dottore@glucoseguardian.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "Email gi?? presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.10.
   */
  @Test
  public void testSave10() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("393877654222222222222222222222222222222222");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("il campo numero di telefono non rispetta il formato");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.11.
   */
  @Test
  public void testSave11() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.12.
   */
  @Test
  public void testSave12() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.13.
   */
  @Test
  public void testSave13() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione(
        "DiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del campo Specializzazione non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.14.
   */
  @Test
  public void testSave14() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Codice Albo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.15.
   */
  @Test
  public void testSave15() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Nome Struttura non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.16.
   */
  @Test
  public void testSave16() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura(
        "Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "La lunghezza del campo Indirizzo Struttura non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.17.
   */
  @Test
  public void testSave17() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("Dottore registrato correttamente");
    testSave(input, status().isOk(), oracolo);
  }


  /**
   * Nome troppo lungo
   */
  @Test
  public void testSave18() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("MatteoMatteoMatteoMatteoMatteoMatteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Cognome troppo breve
   */
  @Test
  public void testSave19() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Indirizzo troppo lungo
   */
  @Test
  public void testSave20() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("ViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaVia");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Password troppo lunga
   */
  @Test
  public void testSave21() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-ViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaViaVia");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Specializzazione troppo breve
   */
  @Test
  public void testSave22() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del campo Specializzazione non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * codice albo troppo breve
   */
  @Test
  public void testSave23() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Codice Albo non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }


  /**
   * indirizzo struttura troppo breve
   */
  @Test
  public void testSave24() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Indirizzo Struttura non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * indirizzo struttura troppo lungo
   */
  @Test
  public void testSave25() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("okokokokooooooooooookkookokokokokokokokokokokokokokokookokokokooooooooooookkookokokokokokokokokokokokokokoko");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Nome Struttura non ?? valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  @WithMockUser(username = "dottore", authorities = {"DOTTORE"})
  // Mock User dottore with tipo Dottore
  private void testSave(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/dottore/save").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


  /**
   * UpdateStato: stato assente
   */
  @Test
  public void testUpdateStato1() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setStato(null);
    RisultatoDto oracolo = new RisultatoDto("lo stato del dottore non pu?? essere assente");
    testUpdateStato(input, status().isBadRequest(), oracolo);
  }

  /**
   * UpdateStato: stato negativo
   */
  @Test
  public void testUpdateStato2() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setStato(-1);
    RisultatoDto oracolo = new RisultatoDto("Lo stato del dottore non ?? valido");
    testUpdateStato(input, status().isBadRequest(), oracolo);
  }

  /**
   * UpdateStato: stato troppo grande
   */
  @Test
  public void testUpdateStato3() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setStato(10);
    RisultatoDto oracolo = new RisultatoDto("Lo stato del dottore non ?? valido");
    testUpdateStato(input, status().isBadRequest(), oracolo);
  }

  /**
   * UpdateStato: codice fiscale assente
   */
  @Test
  public void testUpdateStato4() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setStato(1);
    RisultatoDto oracolo = new RisultatoDto("Il codice fiscale non pu?? essere vuoto");
    testUpdateStato(input, status().isBadRequest(), oracolo);
  }

  /**
   * UpdateStato: codice fiscale lunghezza errata
   */
  @Test
  public void testUpdateStato5() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setStato(1);
    input.setCodiceFiscale("AAAA");
    RisultatoDto oracolo = new RisultatoDto("La lunghezza del codice fiscale deve essere di 16 caratteri");
    testUpdateStato(input, status().isBadRequest(), oracolo);
  }

  /**
   * UpdateStato: input valido
   */
  @Test
  public void testUpdateStato6() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setStato(1);
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    RisultatoDto oracolo = new RisultatoDto("Modifica effettuata con successo");
    testUpdateStato(input, status().isOk(), oracolo);
  }


  @WithMockUser(username = "admin", authorities = {"ADMIN"})
  // Mock User dottore with tipo Dottore
  private void testUpdateStato(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {
    // Mock admin
    Admin admin = new Admin();
    admin.setCodiceFiscale("RSSNTN90A01H703B");
    admin.setEmail("dottore@glucoseguardian.it");
    when(utils.getAuthentication()).thenReturn(createSecurityContext(admin));

    performSync(post("/dottore/updateStato").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }

  @BeforeEach
  public void resetServiceStub() {
    serviceStub.duplicatedId = false;
    serviceStub.duplicatedEmail = false;
  }

}


