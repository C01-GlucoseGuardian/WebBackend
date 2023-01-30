package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called DottoreDao.
 */
public interface DottoreDao extends JpaRepository<Dottore, String> {

  Optional<Dottore> findByEmail(String email);

  List<Dottore> findByStato(int stato);
}
