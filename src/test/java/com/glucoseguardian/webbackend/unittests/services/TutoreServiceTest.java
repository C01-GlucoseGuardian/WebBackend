package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import com.glucoseguardian.webbackend.tutore.service.TutoreServiceConcrete;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class TutoreServiceTest {

  @Mock
  TutoreDao tutoreDao;
  @Mock
  UtenteDao utenteDao;
  @Mock
  MailService mailService;
  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  TutoreServiceConcrete tutoreServiceConcrete;

  /**
   * Duplicated CF
   */
  @Test
  public void testSave1() {
    when(tutoreDao.existsById(any(String.class))).thenReturn(true);
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    assertThrows(DuplicatedEntityException.class, () -> tutoreServiceConcrete.save(tutore));
  }

  /**
   * Duplicated email
   */
  @Test
  public void testSave2() {
    when(utenteDao.existsByEmail(any(String.class))).thenReturn(true);

    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    assertThrows(DuplicatedEntityException.class, () -> tutoreServiceConcrete.save(tutore));
  }

  /**
   * Invalid date
   */
  @Test
  public void testSave3() {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("1007/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    assertThrows(IllegalArgumentException.class, () -> tutoreServiceConcrete.save(tutore));
  }

  /**
   * Save failure
   */
  @Test
  public void testSave4() throws UserNotFoundException, DuplicatedEntityException {
    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    assertFalse(tutoreServiceConcrete.save(tutore));
  }

  /**
   * Save ok
   */
  @Test
  public void testSave5() throws UserNotFoundException, DuplicatedEntityException {
    final int[] counter = {0};
    when(tutoreDao.existsById(any(String.class))).then(invocation -> counter[0]++ > 0);

    TutoreDto tutore = new TutoreDto();
    tutore.setNome("Vito");
    tutore.setCognome("Piegari");
    tutore.setCodiceFiscale("PGRVTT06G22H501E");
    tutore.setSesso("M");
    tutore.setDataNascita("10/07/2001");
    tutore.setEmail("vito@piegari.it");
    tutore.setTelefono("3663212456");
    tutore.setIndirizzo("C.so Garibaldi, 12");

    assertTrue(tutoreServiceConcrete.save(tutore));
  }


}
