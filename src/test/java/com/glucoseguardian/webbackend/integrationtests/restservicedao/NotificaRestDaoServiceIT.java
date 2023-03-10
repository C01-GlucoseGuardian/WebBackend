package com.glucoseguardian.webbackend.integrationtests.restservicedao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.storage.dto.IdDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Notifica;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

public class NotificaRestDaoServiceIT extends AbstractIntegrationDaoTest {

   private static final Dottore dottore = new Dottore();
  private static final Admin admin = new Admin();
  private static final Paziente paziente = new Paziente();
  private static final Tutore tutore = new Tutore();

  @BeforeAll
  public static void setupClass() {
    admin.setCodiceFiscale("RSSMRA80A01F205X");
    admin.setEmail("admin@glucoseguardian.it");
    admin.setNotifiche(List.of());

    dottore.setEmail("dottore@glucoseguardian.it");
    dottore.setCodiceFiscale("RSSNTN90A01H703B");
    dottore.setStato(1);
    dottore.setNotifiche(List.of());

    paziente.setEmail("paziente@glucoseguardian.it");
    paziente.setCodiceFiscale("MRACMB95A13A717X");
    Notifica notifica = new Notifica();
    notifica.setId(1L);
    notifica.setOra(Time.valueOf("01:01:01"));
    notifica.setData(Date.valueOf("2022-01-01"));
    notifica.setPazienteOggetto(paziente);
    notifica.setPazienteDestinatario(paziente);
    paziente.setNotifiche(List.of(notifica));

    tutore.setCodiceFiscale("TTOGNN65M07G273H");
    tutore.setEmail("tutore@glucoseguardian.it");
    tutore.setNotifiche(List.of(notifica, notifica));
  }

  @BeforeEach
  public void resetDottoreEmail() {
    dottore.setEmail("dottore@glucoseguardian.it");
  }


  /**
   * Notifica: Messaggio assente.
   */
  @Test
  public void testSend1() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio(null);

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica non pu?? essere assente");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
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

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
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

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }


  /**
   * Notifica: nessun destinatario.
   */
  @Test
  public void testSend4() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");

    RisultatoDto oracolo = new RisultatoDto("Tutti i destinatari sono vuoti");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Notifica: destinatario admin codiceFiscale non valido.
   */
  @Test
  public void testSend5() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setAdminDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Notifica: destinatario dottore codiceFiscale non valido.
   */
  @Test
  public void testSend6() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setDottoreDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Notifica: destinatario paziente codiceFiscale non valido.
   */
  @Test
  public void testSend7() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setPazienteDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Notifica: destinatario tutore codiceFiscale non valido.
   */
  @Test
  public void testSend8() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setTutoreDestinatario("A");

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale ?? di lunghezza errata");
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
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
    testSend(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * Notifica: Everything ok
   */
  @Test
  public void testSend10() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setDottoreDestinatario(dottore.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("Notifica inviata correttamente");
    testSend(notifica, status().isOk(), oracolo, dottore);
  }

  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    performSync(post("/notifica/send").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input)).header("Authorization", "Bearer " + generateJwtToken(utente))).andExpect(status)
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

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non pu?? essere assente");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: id invalido
   */
  @Test
  public void testUpdate2() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(-100L);
    notifica.setStato(0);

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non ?? valido");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: stato assente
   */
  @Test
  public void testUpdate3() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non pu?? essere assente");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: stato non valido
   */
  @Test
  public void testUpdate4() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);
    notifica.setStato(100);

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non ?? valido");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: ok
   */
  @Test
  public void testUpdate5() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(1L);
    notifica.setStato(1);
    RisultatoDto oracolo = new RisultatoDto("Stato notifica aggiornato correttamente");
    testUpdate(notifica, status().isOk(), oracolo, dottore);
  }


  private void testUpdate(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    performSync(post("/notifica/updateStato").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input)).header("Authorization", "Bearer " + generateJwtToken(utente))).andExpect(status)
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
    testGet(id, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * get notifica: id invalido
   */
  @Test
  public void testGet2() throws Exception {
    IdDto id = new IdDto(-100L);

    RisultatoDto oracolo = new RisultatoDto("L'id non ?? valido");
    testGet(id, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * get notifica: everything ok
   */
  @Test
  public void testGet3() throws Exception {
    IdDto id = new IdDto(1L);

    Notifica notifica = new Notifica();
    notifica.setId(1L);
    notifica.setStato(2);
    notifica.setMessaggio("Nuovo dottore da convalidare: dottore2@glucoseguardian.it");
    notifica.setData(Date.valueOf("2022-02-05"));
    notifica.setOra(Time.valueOf("07:55:00"));
    RisultatoDto oracolo = NotificaDto.valueOf(notifica);

    testGet(id, status().isOk(), oracolo, dottore);
  }

  private void testGet(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {
    performSync(post("/notifica/get").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input)).header("Authorization", "Bearer " + generateJwtToken(utente))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


  /**
   * getAll: no authenticated user
   */
  @Test
  public void testGetAll2() throws Exception {
    RisultatoDto oracolo = new RisultatoDto("Utente non autorizzato");
    dottore.setEmail("dottore2@glucoseguardian.it");
    testGetAll(status().isForbidden(), oracolo, dottore);
  }

  /**
   * getAll: authenticated admin
   */
  @Test
  public void testGetAll3() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo, admin);
  }

  /**
   * getAll: authenticated dottore
   */
  @Test
  public void testGetAll4() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo, dottore);
  }

  /**
   * getAll: authenticated paziente
   */
  @Test
  public void testGetAll5() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo, paziente);
  }

  /**
   * getAll: authenticated tutore
   */
  @Test
  public void testGetAll6() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    testGetAll(status().isOk(), oracolo, tutore);
  }

  private void testGetAll(ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {
    performSync(get("/notifica/getAll").header("Authorization", "Bearer " + generateJwtToken(utente)))
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }
}
