package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.List;

/**
 * This is a FeedbackService Interface.
 */
public interface FeedbackServiceInterface {

  FeedbackDto findById(Long id);

  ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente);

  ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore);

  boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      Paziente paziente);

}
