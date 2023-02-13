package com.glucoseguardian.webbackend.integrationtests.restservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.notifica.rest.NotificaRest;
import com.glucoseguardian.webbackend.notifica.service.FinalNotificaService;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.notifica.service.NotificaServiceConcrete;
import com.glucoseguardian.webbackend.storage.dao.NotificaDao;
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
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.ResultMatcher;

@Import({Utils.class, NotificaRest.class, NotificaServiceConcrete.class, FinalNotificaService.class, MailService.class, FirebaseService.class})
public class NotificaRestServiceIT extends AbstractIntegrationTest {

  @MockBean
  NotificaDao notifcaDao;

  @MockBean
  JavaMailSender javaMailSender;

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
  public void resetDottoreStato() {
    dottore.setStato(1);
  }


  /**
   * Notifica: Messaggio assente.
   */
  @Test
  public void testSend1() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio(null);

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica non può essere assente");
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

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica è di lunghezza errata");
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

    RisultatoDto oracolo = new RisultatoDto("il messaggio della notifica è di lunghezza errata");
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

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
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

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
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

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
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

    RisultatoDto oracolo = new RisultatoDto("il codice fiscale è di lunghezza errata");
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
    when(dottoreDao.findById(dottore.getCodiceFiscale())).thenReturn(Optional.of(dottore));
    when(notifcaDao.existsById(any())).thenReturn(true);
    testSend(notifica, status().isOk(), oracolo, dottore);
  }

  /**
   * Notifica: Internal error
   */
  @Test
  public void testSend11() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setMessaggio("Nuova notifica");
    notifica.setDottoreDestinatario(dottore.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("Errore durante l'inserimento della Notifica");
    when(dottoreDao.findById(dottore.getCodiceFiscale())).thenReturn(Optional.of(dottore));
    testSend(notifica, status().isInternalServerError(), oracolo, dottore);
  }

  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);
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

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non può essere assente");
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

    RisultatoDto oracolo = new RisultatoDto("L'id del messaggio non è valido");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: stato assente
   */
  @Test
  public void testUpdate3() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non può essere assente");
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

    RisultatoDto oracolo = new RisultatoDto("Lo stato del messaggio non è valido");
    testUpdate(notifica, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * update notifica: ok
   */
  @Test
  public void testUpdate5() throws Exception {
    NotificaDto notifica = new NotificaDto();
    notifica.setId(0L);
    notifica.setStato(1);
    when(notifcaDao.findById(0L)).thenReturn(Optional.of(new Notifica()));
    RisultatoDto oracolo = new RisultatoDto("Stato notifica aggiornato correttamente");
    testUpdate(notifica, status().isOk(), oracolo, dottore);
  }


  private void testUpdate(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);
    
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

    RisultatoDto oracolo = new RisultatoDto("L'id non è valido");
    testGet(id, status().isBadRequest(), oracolo, dottore);
  }

  /**
   * get notifica: everything ok
   */
  @Test
  public void testGet3() throws Exception {
    IdDto id = new IdDto(0L);

    Notifica notifica = new Notifica();
    notifica.setId(0L);
    notifica.setStato(0);
    notifica.setData(Date.valueOf("2022-01-01"));
    notifica.setOra(Time.valueOf("01:01:01"));
    RisultatoDto oracolo = NotificaDto.valueOf(notifica);

    when(notifcaDao.findById(0L)).thenReturn(Optional.of(notifica));
    testGet(id, status().isOk(), oracolo, dottore);
  }

  private void testGet(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {
    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);
    performSync(post("/notifica/get").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input)).header("Authorization", "Bearer " + generateJwtToken(utente))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


  /**
   * getAll: internal server error
   */
  @Test
  public void testGetAll1() throws Exception {
    RisultatoDto oracolo = new RisultatoDto("Errore durante la ricerca della notifica");
    when(dottoreDao.findById(dottore.getCodiceFiscale())).thenReturn(null);
    testGetAll(status().isInternalServerError(), oracolo, dottore);
  }

  /**
   * getAll: no authenticated user
   */
  @Test
  public void testGetAll2() throws Exception {
    RisultatoDto oracolo = new RisultatoDto("Utente non autorizzato");
    dottore.setStato(0);
    testGetAll(status().isForbidden(), oracolo, dottore);
  }

  /**
   * getAll: authenticated admin
   */
  @Test
  public void testGetAll3() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    when(adminDao.findById(admin.getCodiceFiscale())).thenReturn(Optional.of(admin));
    testGetAll(status().isOk(), oracolo, admin);
  }

  /**
   * getAll: authenticated dottore
   */
  @Test
  public void testGetAll4() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    when(dottoreDao.findById(dottore.getCodiceFiscale())).thenReturn(Optional.of(dottore));
    testGetAll(status().isOk(), oracolo, dottore);
  }

  /**
   * getAll: authenticated paziente
   */
  @Test
  public void testGetAll5() throws Exception {
    RisultatoDto oracolo = new ListDto<>(paziente.getNotifiche().stream().map(NotificaDto::valueOf).toList());
    when(pazienteDao.findById(paziente.getCodiceFiscale())).thenReturn(
        Optional.ofNullable(paziente));
    testGetAll(status().isOk(), oracolo, paziente);
  }

  /**
   * getAll: authenticated tutore
   */
  @Test
  public void testGetAll6() throws Exception {
    RisultatoDto oracolo = new ListDto<>();
    when(tutoreDao.findById(tutore.getCodiceFiscale())).thenReturn(Optional.of(tutore));
    testGetAll(status().isOk(), oracolo, tutore);
  }

  private void testGetAll(ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {
    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);
    performSync(get("/notifica/getAll").header("Authorization", "Bearer " + generateJwtToken(utente)))
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }
}
