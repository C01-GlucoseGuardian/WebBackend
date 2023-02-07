package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called AssunzioneFarmacoDao.
 */
public interface AssunzioneFarmacoDao extends JpaRepository<AssunzioneFarmaco, Long> {

  List<AssunzioneFarmaco> findByTerapia_Paziente_CodiceFiscale(@NonNull String codiceFiscale);

  List<AssunzioneFarmaco> findByTerapia_Id(@NonNull Long id);

}
