package com.glucoseguardian.webbackend.integrationtests.restservicedao;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.dottore.rest.DottoreRest;
import com.glucoseguardian.webbackend.dottore.service.DottoreServiceConcrete;
import com.glucoseguardian.webbackend.dottore.service.FinalDottoreService;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.ResultMatcher;

@Import({Utils.class, DottoreRest.class, DottoreServiceConcrete.class, FinalDottoreService.class, MailService.class, FirebaseService.class})
public class DottoreRestServiceIT extends AbstractIntegrationTest {

  @MockBean
  JavaMailSender javaMailSender;

  static Dottore dottore = new Dottore();
  static Admin admin = new Admin();

  @BeforeAll
  public static void setupClass() {
    admin.setCodiceFiscale("RSSMRA80A01F205X");
    admin.setEmail("admin@glucoseguardian.it");
    dottore.setEmail("dottore@glucoseguardian.it");
    dottore.setCodiceFiscale("RSSNTN90A01H703B");
  }

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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non è valida");
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
        "Codice fiscale già presente nel database");
    when(dottoreDao.existsById("BNCLDA72E17A535H")).thenReturn(true);
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

    RisultatoDto oracolo = new RisultatoDto("il sesso non è valido");
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

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La data di nascita è nel futuro");
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

    RisultatoDto oracolo = new RisultatoDto("L'email non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.9.
   */
  @Test
  public void testSave9() throws Exception {
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
        "Email già presente nel database");
    when(utenteDao.existsByEmail(dottore.getEmail())).thenReturn(true);
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del campo Specializzazione non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Codice Albo non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Nome Struttura non è valida");
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
        "La lunghezza del campo Indirizzo Struttura non è valida");
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
    final int[] counter = {0};
    when(dottoreDao.existsById("LDAMTT01H09B963Y")).then(invocation -> counter[0]++ > 0);

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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del campo Specializzazione non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Codice Albo non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Indirizzo Struttura non è valida");
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Nome Struttura non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

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
    RisultatoDto oracolo = new RisultatoDto("lo stato del dottore non può essere assente");
    testUpdateStato(input, status().isBadRequest(), oracolo, admin);
  }

  /**
   * UpdateStato: stato negativo
   */
  @Test
  public void testUpdateStato2() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setStato(-1);
    RisultatoDto oracolo = new RisultatoDto("Lo stato del dottore non è valido");
    testUpdateStato(input, status().isBadRequest(), oracolo, admin);
  }

  /**
   * UpdateStato: stato troppo grande
   */
  @Test
  public void testUpdateStato3() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setStato(10);
    RisultatoDto oracolo = new RisultatoDto("Lo stato del dottore non è valido");
    testUpdateStato(input, status().isBadRequest(), oracolo, admin);
  }

  /**
   * UpdateStato: codice fiscale assente
   */
  @Test
  public void testUpdateStato4() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setStato(1);
    RisultatoDto oracolo = new RisultatoDto("Il codice fiscale non può essere vuoto");
    testUpdateStato(input, status().isBadRequest(), oracolo, admin);
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
    testUpdateStato(input, status().isBadRequest(), oracolo, admin);
  }

  /**
   * UpdateStato: input valido
   */
  @Test
  public void testUpdateStato6() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setStato(1);
    input.setCodiceFiscale("RSSNTN90A01H703B");
    RisultatoDto oracolo = new RisultatoDto("Modifica effettuata con successo");

    when(dottoreDao.findById(dottore.getCodiceFiscale())).thenReturn(Optional.of(new Dottore()));

    testUpdateStato(input, status().isOk(), oracolo, admin);
  }


  private void testUpdateStato(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {
    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);

    performSync(post("/dottore/updateStato").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input)).header("Authorization", "Bearer " + generateJwtToken(utente)))
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


}
