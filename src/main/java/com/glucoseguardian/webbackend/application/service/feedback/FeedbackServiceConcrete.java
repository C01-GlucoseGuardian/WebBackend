package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.FeedbackDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Feedback;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of FarmacoServiceInterface.
 */
@Service
public class FeedbackServiceConcrete implements FeedbackServiceInterface {

  @Autowired
  FeedbackDao feedbackDao;
  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  DottoreDao dottoreDao;

  @Override
  public FeedbackDto findById(Long id) {
    Feedback result = feedbackDao.findById(id).orElse(null);
    if (result != null) {
      return FeedbackDto.valueOf(result);
    } else {
      throw new RuntimeException("Feedback non trovato.");
    }
  }

  @Override
  public List<FeedbackDto> findByPaziente(String codiceFiscalePaziente) {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      List<FeedbackDto> list = new ArrayList<>();
      for (Feedback feedback : result.getFeedbacks()) {
        list.add(FeedbackDto.valueOf(feedback));
      }
      return list;
    } else {
      throw new RuntimeException("Paziente non trovato.");
    }
  }

  @Override
  public List<FeedbackDto> findByDottore(String codiceFiscaleDottore) {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      List<FeedbackDto> list = new ArrayList<>();
      for (Feedback feedback : result.getFeedbacks()) {
        list.add(FeedbackDto.valueOf(feedback));
      }
      return list;
    } else {
      throw new RuntimeException("Dottore non trovato.");
    }
  }

  @Override
  public boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      Paziente paziente) {

    Feedback feedback = new Feedback(statoSalute, oreSonno, dolori, svenimenti,
        new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), paziente);

    feedbackDao.saveAndFlush(feedback);

    return feedbackDao.existsById(feedback.getId());
  }
}
