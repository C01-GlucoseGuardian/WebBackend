package com.glucoseguardian.webbackend.integrationtests.restservicedao;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import com.glucoseguardian.webbackend.tutore.rest.TutoreRest;
import com.glucoseguardian.webbackend.tutore.service.FinalTutoreService;
import com.glucoseguardian.webbackend.tutore.service.TutoreServiceConcrete;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.ResultMatcher;

@Import({TutoreRest.class, TutoreServiceConcrete.class, FinalTutoreService.class, MailService.class, FirebaseService.class})
public class TutoreRestServiceIT extends AbstractIntegrationTest {

  @MockBean
  JavaMailSender javaMailSender;

  private static final Tutore tutore = new Tutore();
  private static final Dottore dottore = new Dottore();

  @BeforeAll
  public static void setupClass() {
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setCodiceFiscale("TTOGNN65M07G273H");
    dottore.setEmail("dottore@glucoseguardian.it");
    dottore.setCodiceFiscale("RSSNTN90A01H703B");
    dottore.setStato(1);
  }

  /**
   * Test ID TC_3.1
   */
  @Test
  public void testSave1() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del nome non è valida");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.2
   */
  @Test
  public void testSave2() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non è valida");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.3
   */
  @Test
  public void testSave3() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22HHH501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto(
        "La lunghezza del codice fiscale deve essere di 16 caratteri");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.4
   */
  @Test
  public void testSave4() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Gianni");
    tutore.setCognome("Otto");
    tutore.setCodiceFiscale("TTOGNN65M07G273H");
    tutore.setSesso("M");
    tutore.setDataNascita("07/08/1965");
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("Palermo Corso Vittorio Emanuele 10");

    RisultatoDto oracolo = new RisultatoDto("Codice fiscale già presente nel database");
    when(tutoreDao.existsById(tutore.getCodiceFiscale())).thenReturn(true);
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.5
   */
  @Test
  public void testSave5() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("Sesso");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("il sesso non è valido");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }


  /**
   * Test ID TC_3.6
   */
  @Test
  public void testSave6() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/0687/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non è valida");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }


  /**
   * Test ID TC_3.7
   */
  @Test
  public void testSave7() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/3001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("La data di nascita è nel futuro");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }


  /**
   * Test ID TC_3.8
   */
  @Test
  public void testSave8() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vitopiegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("L'email non è valida");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }


  /**
   * Test ID TC_3.9
   */
  @Test
  public void testSave9() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("Email già presente nel database");
    when(utenteDao.existsByEmail(tutore.getEmail())).thenReturn(true);
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.10
   */
  @Test
  public void testSave10() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("36f2324kjkk");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("il campo numero di telefono non rispetta il formato");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.11
   */
  @Test
  public void testSave11() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo(
        "C.so Garibaldi, 12 22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222rrwasd22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non è valida");
    testSave(tutore, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Test ID TC_3.12
   */
  @Test
  public void testSave12() throws Exception {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("Tutore inserito correttamente");
    final int[] counter = {0};
    when(tutoreDao.existsById("PGRVTT06G22H501E")).then(invocation -> counter[0]++ > 0);
    testSave(tutore, status().isOk(), oracolo, dottore);
  }



  private void testSave(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);

    performSync(
        post("/tutore/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJsonString(input))
            .header("Authorization", "Bearer " + generateJwtToken(utente))
    )
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


}
