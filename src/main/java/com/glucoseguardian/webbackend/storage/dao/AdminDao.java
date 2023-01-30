package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called AdminDao.
 */
public interface AdminDao extends JpaRepository<Admin, String> {

  Optional<Admin> findByEmail(String email);

}
