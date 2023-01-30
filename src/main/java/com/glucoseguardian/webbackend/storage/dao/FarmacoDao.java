package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called FarmacoDao.
 */
public interface FarmacoDao extends JpaRepository<Farmaco, Long> {

  List<Farmaco> findBynomeFarmacoContainingIgnoreCase(String ricerca);

  List<Farmaco> findByprincipioAttivoContainingIgnoreCase(String ricerca);

  List<Farmaco> findByconfezioneContainingIgnoreCase(String ricerca);
}
