package com.glucoseguardian.webbackend.feedback.service;

import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import org.springframework.stereotype.Service;

/**
 * This is the stub.
 */
@Service
public class FeedbackServiceStub implements FeedbackServiceInterface {

  @Override
  public FeedbackDto findById(Long id) {
    return new FeedbackDto();
  }

  @Override
  public ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente) {
    return new ListDto<>();
  }

  @Override
  public ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore) {
    return new ListDto<>();
  }

  @Override
  public boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      String codiceFiscalePaziente) {
    return true;
  }
}
