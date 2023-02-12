package com.glucoseguardian.webbackend.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalFeedbackService extends AbstractFeedbackService {
  @Autowired
  FeedbackServiceInterface feedbackService;

  @Override
  public FeedbackServiceInterface getImplementation() {
    return feedbackService;
  }
}
