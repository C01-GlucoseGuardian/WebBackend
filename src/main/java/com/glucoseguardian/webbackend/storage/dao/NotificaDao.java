package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Notifica;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called NotificaDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface NotificaDao extends CrudRepository<Notifica, Long> {

}
