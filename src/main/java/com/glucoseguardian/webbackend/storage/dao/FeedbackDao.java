package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FeedbackDao.
 */
public interface FeedbackDao extends JpaRepository<Feedback, Long> {

  List<Feedback> findByDottore_codiceFiscale(@NonNull String codiceFiscale);

  List<Feedback> findByPaziente_codiceFiscale(@NonNull String codiceFiscale);

}
