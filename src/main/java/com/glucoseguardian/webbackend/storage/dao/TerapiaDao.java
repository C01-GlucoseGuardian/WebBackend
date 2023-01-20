package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Terapia;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called TerapiaDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface TerapiaDao extends CrudRepository<Terapia, Long> {

}
