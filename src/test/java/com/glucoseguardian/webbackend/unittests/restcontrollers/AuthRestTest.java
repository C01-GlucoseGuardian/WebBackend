package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.autenticazione.rest.AuthRest;
import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceStub;
import com.glucoseguardian.webbackend.autenticazione.service.TestAuthService;
import com.glucoseguardian.webbackend.configuration.Utils;
import com.glucoseguardian.webbackend.storage.dto.LoginInputDto;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.TipoUtente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(AuthRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({Utils.class, AuthServiceStub.class, TestAuthService.class})
public class AuthRestTest extends AbstractRestTest {

  @MockBean
  Utils utils;

  /**
   * Login, email assente
   */
  @Test
  public void testLogin1() throws Exception {
    LoginInputDto input = new LoginInputDto();

    RisultatoDto oracolo = new RisultatoDto("la mail non può essere assente");
    testLogin(input, status().isBadRequest(), oracolo);
  }

  /**
   * Login, email invalida
   */
  @Test
  public void testLogin2() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setEmail("adminnnnglucoseguardian.it");

    RisultatoDto oracolo = new RisultatoDto("L'email non è valida");
    testLogin(input, status().isBadRequest(), oracolo);
  }

  /**
   * Login, email presente, password assente
   */
  @Test
  public void testLogin3() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setEmail("admin@glucoseguardian.it");

    RisultatoDto oracolo = new RisultatoDto("la password non può essere assente");
    testLogin(input, status().isBadRequest(), oracolo);
  }

  /**
   * Login, email presente, password troppo corta
   */
  @Test
  public void testLogin4() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setEmail("admin@glucoseguardian.it");
    input.setPassword("123");
    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non è valida");
    testLogin(input, status().isBadRequest(), oracolo);
  }

  /**
   * Login, email presente, password troppo lunga
   */
  @Test
  public void testLogin5() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setEmail("admin@glucoseguardian.it");
    input.setPassword("1234567890123456789012345678901234567890");
    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non è valida");
    testLogin(input, status().isBadRequest(), oracolo);
  }

  /**
   * Login: ok
   */
  @Test
  public void testLogin6() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setEmail("admin@glucoseguardian.it");
    input.setPassword("password");
    LoginOutputDto oracolo = new LoginOutputDto();
    oracolo.setIdUtente("RSSMRA80A01F205X");
    oracolo.setTipoUtente(TipoUtente.ADMIN.ordinal());
    testLogin(input, status().isOk(), oracolo);
  }


  private void testLogin(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(
        post("/auth/login")
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


  /**
   * ChangePw, old password missing
   */
  @Test
  public void testChangePw1() throws Exception {
    LoginInputDto input = new LoginInputDto();

    RisultatoDto oracolo = new RisultatoDto("la vecchia password non può essere vuota");
    testChangePw(input, status().isBadRequest(), oracolo);
  }

  /**
   * ChangePw, new password missing
   */
  @Test
  public void testChangePw2() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setPassword("password");

    RisultatoDto oracolo = new RisultatoDto("la nuova password non può essere vuota");
    testChangePw(input, status().isBadRequest(), oracolo);
  }

  /**
   * ChangePw, password uguali
   */
  @Test
  public void testChangePw3() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setPassword("password");
    input.setNewPassword("password");

    RisultatoDto oracolo = new RisultatoDto("la nuova password non può essere uguale alla vecchia password");
    testChangePw(input, status().isBadRequest(), oracolo);
  }

  /**
   * ChangePw, nuova password troppo corta
   */
  @Test
  public void testChangePw4() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setPassword("password");
    input.setNewPassword("pass");
    RisultatoDto oracolo = new RisultatoDto("La lunghezza della nuova password non è valida");
    testChangePw(input, status().isBadRequest(), oracolo);
  }

  /**
   * ChangePw, email presente, password troppo lunga
   */
  @Test
  public void testChangePw5() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setPassword("password");
    input.setNewPassword("1234567890123456789012345678901234567890");
    RisultatoDto oracolo = new RisultatoDto("La lunghezza della nuova password non è valida");
    testChangePw(input, status().isBadRequest(), oracolo);
  }

  /**
   * ChangePw: ok
   */
  @Test
  public void testChangePw6() throws Exception {
    LoginInputDto input = new LoginInputDto();
    input.setPassword("password");
    input.setNewPassword("12345678");
    RisultatoDto oracolo = new RisultatoDto("Modifica effettuata con successo");
    testChangePw(input, status().isOk(), oracolo);
  }


  private void testChangePw(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {
    when(utils.getAuthentication()).thenReturn(createSecurityContext(new Admin()));
    performSync(
        post("/auth/changePw")
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
