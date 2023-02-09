package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.feedback.rest.FeedbackRest;
import com.glucoseguardian.webbackend.feedback.service.FeedbackServiceStub;
import com.glucoseguardian.webbackend.feedback.service.TestFeedbackService;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(FeedbackRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({FeedbackServiceStub.class, TestFeedbackService.class})
public class FeedbackRestTest extends AbstractRestTest {
  /**
   * Test ID TC_1.1.
   */
  @Test
  public void testSend1() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("blablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablablblablablablablablablablablablabla");
    input.setOreSonno("Ieri notte ho dormito poche ore");
    input.setDolori("Nessun dolore avvertito");
    input.setSvenimenti("Non sono mai svenuto negli ultimi giorni");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di stato salute non è valida");
    testSend(input, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di ore sonno non è valida");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_1.3.
   */
  @Test
  public void testSend3() throws Exception {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Mi sono sentito molto male");
    input.setOreSonno("Ho dormito pochissimo gli ultimi giorni");
    input.setDolori("Okokokokok122932’012012012010sjidjsidajdajdiajdu4ur843r023ei12iopsiqddkamdkadmjdnurnfiwjdowjdpoqdjief843ht8fheodhihdisahdihiqhwidh38rh328hd9dhihdioancoincfiehdiqhwd8h38ehqhioqhdinakmoiejeiej3eijwidwiwjdncufufOkokokokok122932’012012012010sjidjsidajdajdiajdu4ur843r023ei12iopsiqddkamdkadmjdnurnfiwjdowjdpoqdjief843ht8fheodhihdisahdihiqhwidh38rh328hd9dhihdioancoincfiehdiqhwd8h38ehqhioqhdinakmoiejeiej3eijwidwiwjdncufuf");
    input.setSvenimenti("Non ricordo bene");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di dolori non è valida");
    testSend(input, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("la lunghezza di svenimenti non è valida");
    testSend(input, status().isBadRequest(), oracolo);
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

    RisultatoDto oracolo = new RisultatoDto("Feedback inserito correttamente");
    testSend(input, status().isOk(), oracolo);
  }

  @WithMockUser(username = "paziente", authorities = {"PAZIENTE"}) // Mock User paziente with tipo Paziente
  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(
        post("/feedback/send")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                toJsonString(input)
            )
    ).andExpect(status)
        .andExpect(
            content()
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(
            content()
                .json(toJsonString(oracolo)
                )
        );
  }
}
