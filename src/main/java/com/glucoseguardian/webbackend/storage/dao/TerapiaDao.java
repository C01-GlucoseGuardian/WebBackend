package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Terapia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called TerapiaDao.
 */
public interface TerapiaDao extends JpaRepository<Terapia, Long> {

  Optional<Terapia> findByPaziente_codiceFiscale(String codiceFiscale);
}
