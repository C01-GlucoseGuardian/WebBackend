package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.FeedbackDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NotificaDto;
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
 * This is an implementation of FeedbackServiceInterface.
 */
@Service
public class FeedbackServiceConcrete implements FeedbackServiceInterface {

  @Autowired
  private FeedbackDao feedbackDao;
  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private DottoreDao dottoreDao;

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
  public ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente)
      throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      List<FeedbackDto> list = new ArrayList<>();
      for (Feedback feedback : result.getFeedbacks()) {
        list.add(FeedbackDto.valueOf(feedback));
      }
      ListDto<FeedbackDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }

  @Override
  public ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore)
      throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result != null) {
      List<FeedbackDto> list = new ArrayList<>();
      for (Feedback feedback : result.getFeedbacks()) {
        list.add(FeedbackDto.valueOf(feedback));
      }
      ListDto<FeedbackDto> listDto = new ListDto<>(list);
      return listDto;
    } else {
      throw new UserNotFoundException("Dottore non trovato.");
    }
  }

  @Override
  public boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      String codiceFiscalePaziente) {

    Feedback feedback = new Feedback(statoSalute, oreSonno, dolori, svenimenti,
        new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), codiceFiscalePaziente);

    feedbackDao.saveAndFlush(feedback);

    return feedbackDao.existsById(feedback.getId());
  }
}
