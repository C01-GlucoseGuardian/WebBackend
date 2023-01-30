package com.glucoseguardian.webbackend.service.feedback;

import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import java.util.List;

/**
 * This is a FeedbackService Interface.
 */
public interface FeedbackServiceInterface {

  FeedbackDto findById(Long id);

  List<FeedbackDto> findByPaziente(String codiceFiscalePaziente);

  List<FeedbackDto> findByDottore(String codiceFiscaleDottore);
}
