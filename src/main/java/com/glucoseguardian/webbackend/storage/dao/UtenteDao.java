package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
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

  /**
   * Ritorna true se esiste un utente con una email uguale a quella data in input.
   * Ricerca nell'ordine Admin, Dottore, Paziente, Tutore
   */
  public boolean existsByEmail(@NonNull String email) {
    return adminDao.existsByEmail(email)
        || dottoreDao.existsByEmail(email)
        || pazienteDao.existsByEmail(email)
        || tutoreDao.existsByEmail(email);
  }

  /**
   * Salva i dati di un {@link Utente} nel database.
   */
  public @NonNull Utente save(@NonNull Utente utente) {
    if (utente instanceof Dottore) {
      return dottoreDao.save((Dottore) utente);
    }
    if (utente instanceof Paziente) {
      return pazienteDao.save((Paziente) utente);
    }
    if (utente instanceof Tutore) {
      return tutoreDao.save((Tutore) utente);
    }
    if (utente instanceof Admin) {
      return adminDao.save((Admin) utente);
    }
    throw new RuntimeException("Tipo Utente non trovato");
  }
}
