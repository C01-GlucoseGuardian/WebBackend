package com.glucoseguardian.webbackend.integrationtests;

import static org.hamcrest.Matchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glucoseguardian.webbackend.autenticazione.service.JwtService;
import com.glucoseguardian.webbackend.configuration.AppConfig;
import com.glucoseguardian.webbackend.configuration.FirebaseConfiguration;
import com.glucoseguardian.webbackend.configuration.JwtAuthenticationFilter;
import com.glucoseguardian.webbackend.configuration.PasswordConfiguration;
import com.glucoseguardian.webbackend.configuration.SecurityConfig;
import com.glucoseguardian.webbackend.exceptions.CustomExceptionHandler;
import com.glucoseguardian.webbackend.feedback.rest.FeedbackRest;
import com.glucoseguardian.webbackend.feedback.service.FeedbackServiceConcrete;
import com.glucoseguardian.webbackend.feedback.service.FinalFeedbackService;
import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AppConfig.class,
    PasswordConfiguration.class, SecurityConfig.class, FirebaseConfiguration.class})
@Import({JwtAuthenticationFilter.class, JwtService.class, CustomExceptionHandler.class})
@AutoConfigureMockMvc
@EnableWebMvc
public abstract class AbstractIntegrationTest {

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired(required = false)
  JwtService jwtService;

  @MockBean
  UtenteDao utenteDao;
  @MockBean
  PazienteDao pazienteDao;
  @MockBean
  TutoreDao tutoreDao;
  @MockBean
  DottoreDao dottoreDao;
  @MockBean
  AdminDao adminDao;
  @Autowired
  private MockMvc mvc;

  /**
   * Esegue una chiamata MVC attendendo per la risposta nel caso sia asincrona o restituendola se è
   * sincrona.
   */
  protected ResultActions performSync(MockHttpServletRequestBuilder requestBuilder)
      throws Exception {
    ResultActions resultActions = mvc.perform(requestBuilder);
    if (!resultActions.andReturn().getRequest().isAsyncStarted()) {
      return resultActions;
    }
    return mvc.perform(
        asyncDispatch(resultActions.andExpect(request().asyncResult(anything())).andReturn()));
  }

  /**
   * Serializza un oggetto nel formato json.
   */
  protected String toJsonString(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  protected String generateJwtToken(Utente user) {
    return jwtService.generateToken(user);
  }
}
