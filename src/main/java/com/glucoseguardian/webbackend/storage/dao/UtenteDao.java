package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Dao che lavora su istanze generiche di {@link Utente}, delegando l'accesso al database a
 * {@link AdminDao}, {@link DottoreDao}, {@link PazienteDao} e {@link TutoreDao}.
 */
@Component
public class UtenteDao {
  @Autowired
  AdminDao adminDao;
  @Autowired
  DottoreDao dottoreDao;
  @Autowired
  PazienteDao pazienteDao;
  @Autowired
  TutoreDao tutoreDao;

  /**
   * Ritorna un {@link Utente} con la email uguale a quella data in input.
   * Ricerca nell'ordine Admin, Dottore, Paziente, Tutore
   */
  public @NonNull Optional<? extends Utente> findByEmail(@NonNull String email) {
    Optional<? extends Utente> result = adminDao.findByEmail(email);
    if (result.isPresent()) {
      return result;
    }

    result = dottoreDao.findByEmail(email);
    if (result.isPresent()) {
      return result;
    }

    result = pazienteDao.findByEmail(email);
    if (result.isPresent()) {
      return result;
    }

    return tutoreDao.findByEmail(email);
  }
}
