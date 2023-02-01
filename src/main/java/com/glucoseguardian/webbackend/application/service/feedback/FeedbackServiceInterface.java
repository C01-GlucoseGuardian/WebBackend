package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.List;

/**
 * This is a FeedbackService Interface.
 */
public interface FeedbackServiceInterface {

  FeedbackDto findById(Long id) throws UserNotFoundException;

  ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;

  ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore) throws UserNotFoundException;

  boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      Paziente paziente);

}
