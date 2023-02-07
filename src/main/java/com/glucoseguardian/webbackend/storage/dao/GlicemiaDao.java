package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called GlicemiaDao.
 */
public interface GlicemiaDao extends JpaRepository<Glicemia, Long> {

  Optional<Glicemia> findTopByPaziente_codiceFiscaleOrderByDataOraDesc(String codiceFiscale);

  List<Glicemia> findByPaziente_codiceFiscaleAndDataOraBetweenOrderByDataOraDesc(String codiceFiscale,
      Timestamp start, Timestamp end);


}
