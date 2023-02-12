package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.notifica.rest.NotificaRest;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceStub;
import com.glucoseguardian.webbackend.notifica.service.TestNotificaService;
import com.glucoseguardian.webbackend.storage.dto.IdDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(NotificaRest.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({Utils.class, NotificaServiceStub.class, TestNotificaService.class})
public class NotificaRestTest extends AbstractRestTest {

  @MockBean
  Utils utils;


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

  /**
   * update notifica: id assente
   */
  @Test
  public void testUpdate1() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setStato(0);

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non può essere assente");
    testUpdate(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * update notifica: id invalido
   */
  @Test
  public void testUpdate2() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(-100L);
    notifica.setStato(0);

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non è valido");
    testUpdate(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * update notifica: stato assente
   */
  @Test
  public void testUpdate3() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non può essere assente");
    testUpdate(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * update notifica: stato non valido
   */
  @Test
  public void testUpdate4() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);
    notifica.setStato(100);

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non è valido");
    testUpdate(notifica, status().isBadRequest(), oracolo);
  }

  /**
   * update notifica: ok
   */
  @Test
  public void testUpdate5() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);
    notifica.setStato(1);

    RisultatoDto oracolo = new RisultatoDto("Stato notifica aggiornato correttamente");
    testUpdate(notifica, status().isOk(), oracolo);
  }


  private void testUpdate(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/notifica/updateStato").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }



  /**
   * get notifica: id assente
   */
  @Test
  public void testGet1() throws Exception {
    IdDto id = new IdDto();

    RisultatoDto oracolo = new RisultatoDto("L'id non puo essere assente");
    testGet(id, status().isBadRequest(), oracolo);
  }

  /**
   * get notifica: id invalido
   */
  @Test
  public void testGet2() throws Exception {
    IdDto id = new IdDto(-100L);

    RisultatoDto oracolo = new RisultatoDto("L'id non è valido");
    testGet(id, status().isBadRequest(), oracolo);
  }

  /**
   * get notifica: everything ok
   */
  @Test
  public void testGet3() throws Exception {
    IdDto id = new IdDto(0L);

    RisultatoDto oracolo = new NotificaDto(0L);
    testGet(id, status().isOk(), oracolo);
  }

  private void testGet(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/notifica/get").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


  /**
   * getAll: internal server error
   */
  @Test
  public void testGetAll1() throws Exception {
    RisultatoDto oracolo = new RisultatoDto("Errore durante la ricerca della notifica");
    testGetAll(status().isInternalServerError(), oracolo);
  }

  /**
   * getAll: no authenticated user
   */
  @Test
  public void testGetAll2() throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(null));

    RisultatoDto oracolo = new RisultatoDto("Utente non autorizzato");
    testGetAll(status().isForbidden(), oracolo);
  }

  /**
   * getAll: authenticated admin
   */
  @Test
  public void testGetAll3() throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(new Admin()));

    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo);
  }

  /**
   * getAll: authenticated dottore
   */
  @Test
  public void testGetAll4() throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(new Dottore()));

    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo);
  }

  /**
   * getAll: authenticated paziente
   */
  @Test
  public void testGetAll5() throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(new Paziente()));

    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo);
  }

  /**
   * getAll: authenticated paziente
   */
  @Test
  public void testGetAll6() throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(new Tutore()));

    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo);
  }

  private void testGetAll(ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(get("/notifica/getAll"))
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


}
