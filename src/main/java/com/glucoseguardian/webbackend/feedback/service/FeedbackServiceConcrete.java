package com.glucoseguardian.webbackend.feedback.service;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.FeedbackDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Feedback;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of FeedbackServiceInterface.
 */
@Service
@Primary
public class FeedbackServiceConcrete implements FeedbackServiceInterface {

  @Autowired
  private FeedbackDao feedbackDao;
  @Autowired
  private PazienteDao pazienteDao;

  @Override
  public FeedbackDto findById(Long id) throws EntityNotFoundException {
    Feedback result = feedbackDao.findById(id).orElse(null);
    if (result != null) {
      return FeedbackDto.valueOf(result);
    } else {
      throw new EntityNotFoundException("Feedback non trovato.");
    }
  }

  @Override
  public ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente) {
    List<Feedback> result = feedbackDao.findByPaziente_codiceFiscale(codiceFiscalePaziente);
    List<FeedbackDto> list = new ArrayList<>();
    for (Feedback feedback : result) {
      list.add(FeedbackDto.valueOf(feedback));
    }
    ListDto<FeedbackDto> listDto = new ListDto<>(list);
    return listDto;
  }

  @Override
  public ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore) {
    List<Feedback> result = feedbackDao.findByDottore_codiceFiscale(codiceFiscaleDottore);
    List<FeedbackDto> list = new ArrayList<>();
    for (Feedback feedback : result) {
      list.add(FeedbackDto.valueOf(feedback));
    }
    ListDto<FeedbackDto> listDto = new ListDto<>(list);
    return listDto;
  }

  @Override
  public boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      String codiceFiscalePaziente) throws UserNotFoundException {
    Paziente paziente = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (paziente != null) {
      Feedback feedback = new Feedback(statoSalute, oreSonno, dolori, svenimenti,
          new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), paziente);
      feedback.setDottore(paziente.getDottore());
      feedbackDao.save(feedback);
      return feedbackDao.existsById(feedback.getId());
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }
}
