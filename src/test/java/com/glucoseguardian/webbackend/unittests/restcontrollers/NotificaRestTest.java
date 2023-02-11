package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.notifica.rest.NotificaRest;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceStub;
import com.glucoseguardian.webbackend.notifica.service.TestNotificaService;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(NotificaRest.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({Utils.class, NotificaServiceStub.class, TestNotificaService.class})
public class NotificaRestTest extends AbstractRestTest {

  /**
   * Notifica: Messaggio assente.
   */
  @Test
  public void testSend1() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio(null);

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica non può essere assente");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: Messaggio vuoto.
   */
  @Test
  public void testSend2() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("");
    notifica.setPazienteOggetto("MRTLDA01L55C514M");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: Messaggio troppo lungo.
   */
  @Test
  public void testSend3() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio(RandomStringUtils.random(2000));
    notifica.setPazienteOggetto("MRTLDA01L55C514M");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }


  /**
   * Notifica: nessun destinatario.
   */
  @Test
  public void testSend4() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");

    RisultatoDto oracolo = new RisultatoDto("Tutti i destinatari sono vuoti");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: destinatario admin codiceFiscale non valido.
   */
  @Test
  public void testSend5() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setAdminDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: destinatario dottore codiceFiscale non valido.
   */
  @Test
  public void testSend6() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setDottoreDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: destinatario paziente codiceFiscale non valido.
   */
  @Test
  public void testSend7() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: destinatario tutore codiceFiscale non valido.
   */
  @Test
  public void testSend8() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setTutoreDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: paziente oggetto codiceFiscale non valido.
   */
  @Test
  public void testSend9() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteOggetto("A");
    notifica.setAdminDestinatario("RSSMRA80A01F205X");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del codice fiscale deve essere 16 caratteri");
    testSend(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * Notifica: Tutto ok
   */
  @Test
  public void testSend10() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteOggetto("MRTLDA01L55C514M");
    notifica.setPazienteDestinatario("MRTLDA01L55C514M");
    notifica.setDottoreDestinatario("BNCLDA72E17A535H");
    notifica.setAdminDestinatario("RSSMRA80A01F205X");
    notifica.setTutoreDestinatario("TTOGNN65M07G273H");

    RisultatoDto oracolo = new RisultatoDto("Notifica inviata correttamente");
    testSend(notifica, status().isOk(), oracolo);
  }


  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/notifica/send").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }
}
