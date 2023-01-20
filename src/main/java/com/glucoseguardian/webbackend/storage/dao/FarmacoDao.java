package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FarmacoDao.
 */
public interface FarmacoDao extends JpaRepository<Farmaco, Long> {

}
