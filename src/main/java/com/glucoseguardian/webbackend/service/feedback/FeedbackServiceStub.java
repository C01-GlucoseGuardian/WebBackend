package com.glucoseguardian.webbackend.service.feedback;

import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the stub.
 */
public class FeedbackServiceStub implements FeedbackServiceInterface {

  @Override
  public FeedbackDto findById(Long id) {
    return new FeedbackDto();
  }

  @Override
  public List<FeedbackDto> findByPaziente(String codiceFiscalePaziente) {
    return new ArrayList<>();
  }

  @Override
  public List<FeedbackDto> findByDottore(String codiceFiscaleDottore) {
    return new ArrayList<>();
  }
}
