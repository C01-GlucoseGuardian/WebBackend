package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FarmacoDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface FarmacoDao extends CrudRepository<Farmaco, Long> {

}
