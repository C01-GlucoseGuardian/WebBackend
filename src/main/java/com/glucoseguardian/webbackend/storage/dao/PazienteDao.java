package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Paziente;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called PazienteDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface PazienteDao extends CrudRepository<Paziente, String> {

}
