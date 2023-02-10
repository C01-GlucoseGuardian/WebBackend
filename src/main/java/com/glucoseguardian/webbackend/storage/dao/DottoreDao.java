package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Dottore;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called DottoreDao.
 */
public interface DottoreDao extends JpaRepository<Dottore, String> {

  Optional<Dottore> findByEmail(String email);

  @NotNull
  List<Dottore> findByStato(int stato);

  Optional<Dottore> findByPazientes_codiceFiscale(String codiceFiscale);

  boolean existsByEmail(String email);
}
