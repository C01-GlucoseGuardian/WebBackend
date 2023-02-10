package com.glucoseguardian.webbackend.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class FeedbackService for testing.
 */
@Service
public class TestFeedbackService extends AbstractFeedbackService {
  @Autowired
  private FeedbackServiceInterface feedbackService;

  @Override
  public FeedbackServiceInterface getImplementation() {
    return feedbackService;
  }
}
