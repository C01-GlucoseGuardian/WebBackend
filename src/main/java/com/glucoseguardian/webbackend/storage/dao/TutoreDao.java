package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called ProfiloTutoreDao.
 */
public interface TutoreDao extends JpaRepository<Tutore, String> {

  Optional<Tutore> findByEmail(String email);

}
