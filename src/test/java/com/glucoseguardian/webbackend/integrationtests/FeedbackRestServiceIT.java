package com.glucoseguardian.webbackend.integrationtests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.feedback.rest.FeedbackRest;
import com.glucoseguardian.webbackend.feedback.service.FeedbackServiceConcrete;
import com.glucoseguardian.webbackend.feedback.service.FinalFeedbackService;
import com.glucoseguardian.webbackend.storage.dao.FeedbackDao;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

@Import({FeedbackRest.class, FeedbackServiceConcrete.class, FinalFeedbackService.class})
public class FeedbackRestServiceIT extends AbstractIntegrationTest {

  @MockBean
  FeedbackDao feedbackDao;

  static Paziente paziente = new Paziente();

  @BeforeAll
  public static void setupClass() {
    paziente.setEmail("paziente@glucoseguardian.it");
    paziente.setCodiceFiscale("MRACMB95A13A717X");
  }


  /**
   * Test ID TC_1.1.
   */
  @Test
  public void testSend1() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute(
        "blablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablabla");
    input.setOreSonno("Ieri notte ho dormito poche ore");
    input.setDolori("Nessun dolore avvertito");
    input.setSvenimenti("Non sono mai svenuto negli ultimi giorni");
    input.setIdPaziente(paziente.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di stato salute non è valida");
    testSend(input, status().isBadRequest(), oracolo, paziente);
  }

  /**
   * Test ID TC_1.2.
   */
  @Test
  public void testSend2() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Mi sono sentito molto bene ieri");
    input.setOreSonno("");
    input.setDolori("Mi faceva male la testa tutto il dì");
    input.setSvenimenti("Sono svenuto due volte ieri");
    input.setIdPaziente(paziente.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di ore sonno non è valida");
    testSend(input, status().isBadRequest(), oracolo, paziente);
  }

  /**
   * Test ID TC_1.3.
   */
  @Test
  public void testSend3() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Mi sono sentito molto male");
    input.setOreSonno("Ho dormito pochissimo gli ultimi giorni");
    input.setDolori(
        "Okokokokok122932’012012012010sjidjsidajdajdiajdu4ur843r023ei12iopsiqddkamdkadmjdnurnfiwjdowjdpoqdjief843ht8fheodhihdisahdihiqhwidh38rh328hd9dhihdioancoincfiehdiqhwd8h38ehqhioqhdinakmoiejeiej3eijwidwiwjdncufufOkokokokok122932’012012012010sjidjsidajdajdiajdu4ur843r023ei12iopsiqddkamdkadmjdnurnfiwjdowjdpoqdjief843ht8fheodhihdisahdihiqhwidh38rh328hd9dhihdioancoincfiehdiqhwd8h38ehqhioqhdinakmoiejeiej3eijwidwiwjdncufuf");
    input.setSvenimenti("Non ricordo bene");
    input.setIdPaziente(paziente.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di dolori non è valida");
    testSend(input, status().isBadRequest(), oracolo, paziente);
  }

  /**
   * Test ID TC_1.4.
   */
  @Test
  public void testSend4() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Mi sono sentito molto molto bene");
    input.setOreSonno("Ho dormito molto bene l’ultima settimana");
    input.setDolori("Nessun dolore in particolare, solo un leggero mal di testa");
    input.setSvenimenti("");
    input.setIdPaziente(paziente.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di svenimenti non è valida");
    testSend(input, status().isBadRequest(), oracolo, paziente);
  }

  /**
   * Test ID TC_1.5.
   */
  @Test
  public void testSend5() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Mi sono sentito bene, grazie");
    input.setOreSonno("Ho dormito il giusto");
    input.setDolori("Nessun dolore in particolare");
    input.setSvenimenti("Nessun che io ricordi");
    input.setIdPaziente(paziente.getCodiceFiscale());

    RisultatoDto oracolo = new RisultatoDto("Feedback inserito correttamente");

    when(pazienteDao.findById(paziente.getCodiceFiscale())).thenReturn(Optional.of(paziente));
    when(feedbackDao.existsById(any())).thenReturn(true);
    testSend(input, status().isOk(), oracolo, paziente);
  }

  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo, Utente utente)
      throws Exception {

    Optional optional = Optional.of(utente);
    when(utenteDao.findByEmail(utente.getEmail())).thenReturn(optional);

    performSync(
        post("/feedback/send")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJsonString(input))
            .header("Authorization", "Bearer " + generateJwtToken(utente))
    )
        .andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }


}
