package com.glucoseguardian.webbackend.service.feedback;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFeedbackService extends AbstractFeedbackService {

  @Override
  public FeedbackServiceInterface getImplementation() {
    return new FeedbackServiceConcrete();
  }
}
