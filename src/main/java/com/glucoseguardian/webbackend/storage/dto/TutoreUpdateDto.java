package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'output dell'entità tutore.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TutoreUpdateDto extends RisultatoDto implements Serializable {

  private String codiceFiscaleDto;
  private List<CodiceFiscaleDto> codiceFiscaleTutori;

  public TutoreUpdateDto(String codiceFiscaleDto,
      List<CodiceFiscaleDto> codiceFiscaleTutori) {
    this.codiceFiscaleDto = codiceFiscaleDto;
    this.codiceFiscaleTutori = codiceFiscaleTutori;
  }

  public TutoreUpdateDto() {
  }

  public String getCodiceFiscaleDto() {
    return codiceFiscaleDto;
  }

  public void setCodiceFiscaleDto(
     String codiceFiscaleDto) {
    this.codiceFiscaleDto = codiceFiscaleDto;
  }

  public List<CodiceFiscaleDto> getCodiceFiscaleTutori() {
    return codiceFiscaleTutori;
  }

  public void setCodiceFiscaleTutori(
      List<CodiceFiscaleDto> codiceFiscaleTutori) {
    this.codiceFiscaleTutori = codiceFiscaleTutori;
  }
  public void validate(){
    Validate.notNull(codiceFiscaleDto, "Il codice fiscale non può essere vuoto");
    Validate.isTrue(codiceFiscaleDto.length() == 16,
        "La lunghezza del codice fiscale deve essere di 16 caratteri");
    for (CodiceFiscaleDto tutore:codiceFiscaleTutori){
      Validate.notNull(tutore, "Il codice fiscale non può essere vuoto");
      Validate.isTrue(tutore.getCodiceFiscale().length() == 16,
          "La lunghezza del codice fiscale deve essere di 16 caratteri");
    }
  }
}
