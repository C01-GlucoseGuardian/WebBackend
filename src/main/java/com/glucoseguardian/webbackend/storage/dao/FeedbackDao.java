package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FeedbackDao.
 */
public interface FeedbackDao extends JpaRepository<Feedback, Long> {

}
