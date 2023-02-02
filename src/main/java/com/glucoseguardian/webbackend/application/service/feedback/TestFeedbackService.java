package com.glucoseguardian.webbackend.application.service.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class FeedbackService for testing.
 */
@Service
public class TestFeedbackService extends AbstractFeedbackService {
  @Autowired
  @Qualifier("FeedbackServiceStub")
  FeedbackServiceInterface feedbackService;

  @Override
  public FeedbackServiceInterface getImplementation() {
    return feedbackService;
  }
}
