package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import com.glucoseguardian.webbackend.tutore.rest.TutoreRest;
import com.glucoseguardian.webbackend.tutore.service.TestTutoreService;
import com.glucoseguardian.webbackend.tutore.service.TutoreServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(TutoreRest.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({Utils.class, TutoreServiceStub.class, TestTutoreService.class})
public class TutoreRestTest extends AbstractRestTest {

  @Autowired
  TutoreServiceStub serviceStub;

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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del nome non ?? valida");
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non ?? valida");
    testSave(tutore, status().isBadRequest(), oracolo);
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
    testSave(tutore, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_3.4
   */
  @Test
  public void testSave4() throws Exception {
    serviceStub.duplicatedId = true;
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Gianni");
    tutore.setCognome("Otto");
    tutore.setCodiceFiscale("TTOGNN65M07G273H");
    tutore.setSesso("M");
    tutore.setDataNascita("07/08/1965");
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("Palermo Corso Vittorio Emanuele 10");

    RisultatoDto oracolo = new RisultatoDto("Codice fiscale gi?? presente nel database");
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("il sesso non ?? valido");
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non ?? valida");
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("La data di nascita ?? nel futuro");
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("L'email non ?? valida");
    testSave(tutore, status().isBadRequest(), oracolo);
  }


  /**
   * Test ID TC_3.9
   */
  @Test
  public void testSave9() throws Exception {
    serviceStub.duplicatedEmail = true;

    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    RisultatoDto oracolo = new RisultatoDto("Email gi?? presente nel database");
    testSave(tutore, status().isBadRequest(), oracolo);
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
    testSave(tutore, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non ?? valida");
    testSave(tutore, status().isBadRequest(), oracolo);
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
    testSave(tutore, status().isOk(), oracolo);
  }


  @WithMockUser(username = "tutore", authorities = {"TUTORE"})
  // Mock User tutore with tipo Tutore
  private void testSave(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/tutore/save").contentType(MediaType.APPLICATION_JSON)
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
