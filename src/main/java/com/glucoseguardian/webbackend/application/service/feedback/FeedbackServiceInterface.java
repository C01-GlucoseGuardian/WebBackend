package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a FeedbackService Interface.
 */
public interface FeedbackServiceInterface {
  @PreAuthorize("hasAuthority('DOTTORE') or hasAuthority('PAZIENTE')")
  FeedbackDto findById(Long id) throws EntityNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE') or hasAuthority('PAZIENTE')")
  ListDto<FeedbackDto> findByPaziente(String codiceFiscalePaziente) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<FeedbackDto> findByDottore(String codiceFiscaleDottore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('PAZIENTE')")
  boolean send(String statoSalute, String oreSonno, String dolori, String svenimenti,
      String codiceFiscalePaziente);

}
