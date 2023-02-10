package com.glucoseguardian.webbackend.unittests.restcontrollers;

import com.glucoseguardian.webbackend.feedback.service.FeedbackServiceStub;
import com.glucoseguardian.webbackend.feedback.service.TestFeedbackService;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.TerapiaDto;
import com.glucoseguardian.webbackend.terapia.rest.TerapiaRest;
import com.glucoseguardian.webbackend.terapia.service.TerapiaServiceStub;
import com.glucoseguardian.webbackend.terapia.service.TestTerapiaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(TerapiaRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({TerapiaServiceStub.class, TestTerapiaService.class})
public class TerapiaRestTest extends AbstractRestTest {
  /**
   * Test ID TC_1.1.
   */
  public void testSend() throws Exception{
    TerapiaDto input = new TerapiaDto();
    AssunzioneFarmacoDto input2 = new AssunzioneFarmacoDto();
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    input2.setNomeFarmaco("Diabrezideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee\n"
        + "\n");
    input2.setDosaggio();
    input2.getOrarioAssunzione();
    input2.setViaDiSomministrazione();
    list.add(input2);
    input.setFarmaci(list);
    input.setIdPaziente("1");
  }
}
