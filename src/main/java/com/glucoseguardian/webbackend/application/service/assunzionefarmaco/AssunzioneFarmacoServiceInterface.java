package com.glucoseguardian.webbackend.application.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import java.util.List;

/**
 * This is a AssunzioneFarmacoService Interface.
 */
public interface AssunzioneFarmacoServiceInterface {

  AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco);

  List<AssunzioneFarmacoDto> findByTerapia(Long idTerapia);

  List<AssunzioneFarmacoDto> findByPaziente(String codiceFiscalePaziente);
}
