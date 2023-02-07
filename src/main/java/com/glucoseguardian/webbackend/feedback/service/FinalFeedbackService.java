package com.glucoseguardian.webbackend.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFeedbackService extends AbstractFeedbackService {
  @Autowired
  @Qualifier("feedbackServiceConcrete")
  FeedbackServiceInterface feedbackService;

  @Override
  public FeedbackServiceInterface getImplementation() {
    return feedbackService;
  }
}