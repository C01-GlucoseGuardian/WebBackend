package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called GlicemiaDao.
 */
public interface GlicemiaDao extends JpaRepository<Glicemia, Long> {

  Optional<Glicemia> findTopByPazienteOrderByDataOraDesc(Paziente paziente);

  List<Glicemia> findByPazienteAndDataOraBetweenOrderByDataOraDesc(Paziente paziente,
      Timestamp start, Timestamp end);

}
