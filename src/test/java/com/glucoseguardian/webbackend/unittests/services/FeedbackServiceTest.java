package com.glucoseguardian.webbackend.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.feedback.service.FeedbackServiceConcrete;
import com.glucoseguardian.webbackend.storage.dao.FeedbackDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

  @Mock
  FeedbackDao feedbackDao;

  @Mock
  PazienteDao pazienteDao;

  @InjectMocks
  FeedbackServiceConcrete feedbackService;


  /**
   * Utente non trovato
   */
  @Test
  public void testSend1() {
    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Tutto Bene");
    input.setOreSonno("Ieri notte ho dormito poche ore");
    input.setDolori("Nessun dolore avvertito");
    input.setSvenimenti("Non sono mai svenuto negli ultimi giorni");
    input.setIdPaziente("0");

    assertThrows(UserNotFoundException.class,
        () -> feedbackService.send(input.getStatoSalute(), input.getOreSonno(), input.getDolori(),
            input.getSvenimenti(), input.getIdPaziente()));
  }

  /**
   * User found, save fails
   */
  @Test
  public void testSend2() throws UserNotFoundException {
    when(pazienteDao.findById(any(String.class))).thenReturn(Optional.of(new Paziente()));

    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Tutto Bene");
    input.setOreSonno("Ieri notte ho dormito poche ore");
    input.setDolori("Nessun dolore avvertito");
    input.setSvenimenti("Non sono mai svenuto negli ultimi giorni");
    input.setIdPaziente("0");

    boolean result = feedbackService.send(input.getStatoSalute(), input.getOreSonno(),
        input.getDolori(), input.getSvenimenti(), input.getIdPaziente());
    assertFalse(result);
  }

  /**
   * Everything ok
   */
  @Test
  public void testSend3() throws UserNotFoundException {
    when(pazienteDao.findById(any(String.class))).thenReturn(Optional.of(new Paziente()));
    when(feedbackDao.existsById(any(Long.class))).thenReturn(true);

    FeedbackDto input = new FeedbackDto();
    input.setStatoSalute("Tutto Bene");
    input.setOreSonno("Ieri notte ho dormito poche ore");
    input.setDolori("Nessun dolore avvertito");
    input.setSvenimenti("Non sono mai svenuto negli ultimi giorni");
    input.setIdPaziente("0");

    boolean result = feedbackService.send(input.getStatoSalute(), input.getOreSonno(),
        input.getDolori(), input.getSvenimenti(), input.getIdPaziente());
    assertTrue(result);
  }
}