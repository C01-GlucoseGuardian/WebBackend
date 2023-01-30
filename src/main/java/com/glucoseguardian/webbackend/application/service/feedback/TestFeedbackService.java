package com.glucoseguardian.webbackend.application.service.feedback;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class FeedbackService for testing.
 */
@Service
public class TestFeedbackService extends AbstractFeedbackService {

  @Override
  public FeedbackServiceInterface getImplementation() {
    return new FeedbackServiceConcrete();
  }
}
