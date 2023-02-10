package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
@Import({TerapiaServiceStub.class, TestTerapiaService.class})
public class TerapiaRestTest extends AbstractRestTest {

  /**
   * Test ID TC_4.1.
   */
  @Test
  public void testSend() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("1");
  }

  /**
   * Test ID TC_4.2.
   */
  @Test
  public void testSend1() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco(
        "Diabrezideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee\n"
            + "\n");
    input2.setDosaggio("1 mg");
    input2.setOrarioAssunzione("20:00");
    input2.setViaDiSomministrazione("orale");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("1");
  }

  /**
   * Test ID TC_4.3.
   */
  @Test
  public void testSend2() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Diamicron");
    input2.setDosaggio(
        "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000 mg");
    input2.setOrarioAssunzione("13:00");
    input2.setViaDiSomministrazione("orale");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("2");
  }

  /**
   * Test ID TC_4.4.
   */
  @Test
  public void testSend3() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Diabrezide");
    input2.setDosaggio("2 mg");
    input2.setOrarioAssunzione("13.00");
    input2.setViaDiSomministrazione("orale");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("3");
  }

  /**
   * Test ID TC_4.5.
   */
  @Test
  public void testSend4() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Gleucos");
    input2.setDosaggio("2.5 mg");
    input2.setOrarioAssunzione("12:00");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("3");
  }

  /**
   * Test ID TC_4.6.
   */
  @Test
  public void testSend5() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Diabrezide");
    input2.setDosaggio("2 mg");
    input2.setOrarioAssunzione("13:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive(
        "da assumere prima di un pastoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("3");
  }

  /**
   * Test ID TC_4.7.
   */
  @Test
  public void testSend6() throws Exception {
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Dramion");
    input2.setDosaggio("3 mg");
    input2.setOrarioAssunzione("12:00");
    input2.setViaDiSomministrazione("orale");
    input2.setNoteAggiuntive(
        "da assumere prima di un pasto");
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("4");
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
