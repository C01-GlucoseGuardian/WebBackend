package com.glucoseguardian.webbackend.service.assunzionefarmaco;

import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import java.util.List;

/**
 * This is a AssunzioneFarmacoService Interface.
 */
public interface AssunzioneFarmacoServiceInterface {

  AssunzioneFarmacoDto findById(Long idAssunzioneFarmaco);

  List<AssunzioneFarmacoDto> findByTerapia(Long idTeraoia);

  List<AssunzioneFarmacoDto> findByPaziente(String codifceFiscalePaziente);
}
