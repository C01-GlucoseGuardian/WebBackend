package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called PazienteDao.
 */
public interface PazienteDao extends JpaRepository<Paziente, String> {

  Optional<Paziente> findByEmail(String email);

  List<Paziente> findByCodiceFiscaleContainingIgnoreCase(String query);

  List<Paziente> findByEmailContainingIgnoreCase(String query);

  List<Paziente> findByNomeContainingIgnoreCase(String query);

  List<Paziente> findByCognomeContainingIgnoreCase(String query);
}
