package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Feedback;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FeedbackDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface FeedbackDao extends CrudRepository<Feedback, Long> {

}
