package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.dottore.service.DottoreServiceConcrete;
import com.glucoseguardian.webbackend.exceptions.DuplicatedEntityException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.notifica.service.FirebaseService;
import com.glucoseguardian.webbackend.notifica.service.MailService;
import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class DottoreServiceTest {

  @Mock
  DottoreDao dottoreDao;
  @Mock
  UtenteDao utenteDao;
  @Mock
  AdminDao adminDao;
  @Mock
  MailService mailService;
  @Mock
  FirebaseService firebaseService;
  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  DottoreServiceConcrete dottoreServiceConcrete;


  /**
   * Duplicated CF
   */
  @Test
  public void testSave1() {
    when(dottoreDao.existsById(any(String.class))).thenReturn(true);

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    assertThrows(DuplicatedEntityException.class, () -> dottoreServiceConcrete.save(input));
  }

  /**
   * Duplicated Email
   */
  @Test
  public void testSave2() {
    when(utenteDao.existsByEmail(any(String.class))).thenReturn(true);

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    assertThrows(DuplicatedEntityException.class, () -> dottoreServiceConcrete.save(input));
  }

  /**
   * Invalid Date
   */
  @Test
  public void testSave3() {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09--06--2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    assertThrows(IllegalArgumentException.class, () -> dottoreServiceConcrete.save(input));
  }

  /**
   * Save failure
   */
  @Test
  public void testSave4() throws DuplicatedEntityException {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    assertFalse(dottoreServiceConcrete.save(input));
  }

  /**
   * Everything ok
   */
  @Test
  public void testSave5() throws DuplicatedEntityException {
    final int[] counter = {0};
    when(dottoreDao.existsById(any(String.class))).then(invocation -> counter[0]++ > 0);

    when(utenteDao.existsByEmail(any(String.class))).thenReturn(false);

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    assertTrue(dottoreServiceConcrete.save(input));
  }

  /**
   * updateStato: dottore non trovato
   */
  @Test
  public void testUpdateStato1() {
    assertThrows(UserNotFoundException.class, () ->
        dottoreServiceConcrete.updateStato("BNCLDA72E17A535F", 0, ""));
  }

  /**
   * updateStato: all ok
   */
  @Test
  public void testUpdateStato2() throws UserNotFoundException {
    when(dottoreDao.findById("BNCLDA72E17A535H")).thenReturn(Optional.of(new Dottore()));
    assertTrue(dottoreServiceConcrete.updateStato("BNCLDA72E17A535H", 0, "RSSMRA80A01F205X"));
  }


}


