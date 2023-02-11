package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.terapia.rest.TerapiaRest;
import com.glucoseguardian.webbackend.terapia.service.TerapiaServiceStub;
import com.glucoseguardian.webbackend.terapia.service.TestTerapiaService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(TerapiaRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({Utils.class, TerapiaServiceStub.class, TestTerapiaService.class})
public class TerapiaRestTest extends AbstractRestTest {

  /**
   * Test ID TC_4.1.
   */
  @Test
  public void testSend1() throws Exception {
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("la lista farmaci non può essere vuota");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_4.2.
   */
  @Test
  public void testSend2() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco(
        "Diabrezideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
            + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
            + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    input2.setDosaggio("1 mg");
    input2.setOrarioAssunzione("20:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("il nome del farmaco è della lunghezza errata");
    testSend(input, status().isBadRequest(), oracolo);

  }

  /**
   * Test ID TC_4.3.
   */
  @Test
  public void testSend3() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco("Diamicron");
    input2.setOrarioAssunzione("13:00");
    input2.setDosaggio("");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del dosaggio è errata");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_4.4.
   */
  @Test
  public void testSend4() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco("Diabrezide");
    input2.setDosaggio("2 mg");
    input2.setOrarioAssunzione("13.00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("l'orario di assunzione non è valido");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_4.5.
   */
  @Test
  public void testSend5() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco("Gleucos");
    input2.setDosaggio("2.5 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("");
    input2.setNoteAggiuntive("");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("la lunghezza della via di somministrazione è errata");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_4.6.
   */
  @Test
  public void testSend6() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco("Diabrezide");
    input2.setDosaggio("2 mg");
    input2.setOrarioAssunzione("13:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive(
        "da assumere prima di un pastoooooooooooooooooooooooooooooooooooooooooooooooo"
            + "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
            + "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
            + "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
            + "ooooooooooooooooooooooooooooooooooooo");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("le note aggiuntive superano la lunghezza consentita");
    testSend(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_4.7.
   */
  @Test
  public void testSend7() throws Exception {
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive("da assumere prima di un pasto");
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    TerapiaDto input = new TerapiaDto();
    input.setFarmaci(list);

    RisultatoDto oracolo = new RisultatoDto("Terapia aggiornata correttamente");
    testSend(input, status().isOk(), oracolo);
  }

  @WithMockUser(username = "dottore", authorities = {"DOTTORE"})
  // Mock User dottore with tipo dottore
  private void testSend(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(
        post("/terapia/update")
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
