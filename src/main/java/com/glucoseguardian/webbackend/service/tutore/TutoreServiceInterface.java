package com.glucoseguardian.webbackend.service.tutore;

import com.glucoseguardian.webbackend.storage.dto.TutoreDto;
import java.util.List;

/**
 * This is a TutoreService Interface.
 */
public interface TutoreServiceInterface {

  TutoreDto findByCodiceFiscale(String codiceFiscaleTutore);

  List<TutoreDto> findByPaziente(String codiceFiscalePaziente);

}
