package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called GlicemiaDao.
 */
public interface GlicemiaDao extends JpaRepository<Glicemia, Long> {

  Optional<Glicemia> findTopByPazienteOrderByDataDescOraDesc(Paziente paziente);

  List<Glicemia> findByPazienteAndDataBetweenAndOraBetween(Paziente paziente, Date dateEnd,
      Date dateStart, Time timeEnd, Time timeStart);

}
