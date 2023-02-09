package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta il codice fiscale delle entity.
 */
@JsonInclude(Include.NON_ABSENT)
public class CodiceFiscaleDto extends RisultatoDto implements Serializable {
  private String codiceFiscale;

  public CodiceFiscaleDto() {

  }

  public CodiceFiscaleDto(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   *  validazione del codice fiscale degli utenti.
   */
  public void validate() throws IllegalArgumentException {
    Validate.notNull(codiceFiscale, "il codice fiscale non può essere assente");
    Validate.isTrue(codiceFiscale.length() == 16,
        "La lunghezza del codice fiscale deve essere di 16 caratteri");
  }
}
