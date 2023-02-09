package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'output dell'entità tutore.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TutoreUpdateDto extends RisultatoDto implements Serializable {

  private String idPaziente;
  private List<CodiceFiscaleDto> list;

  public TutoreUpdateDto(String idPaziente,
      List<CodiceFiscaleDto> list) {
    this.idPaziente = idPaziente;
    this.list = list;
  }

  public TutoreUpdateDto() {
  }

  public String getIdPaziente() {
    return idPaziente;
  }

  public void setIdPaziente(
      String idPaziente) {
    this.idPaziente = idPaziente;
  }

  public List<CodiceFiscaleDto> getList() {
    return list;
  }

  public void setList(
      List<CodiceFiscaleDto> list) {
    this.list = list;
  }

  /**
   * Validazione di TutoreUpdateDto.
   */
  public void validate() {
    Validate.notNull(idPaziente, "Il codice fiscale non può essere vuoto");
    Validate.isTrue(idPaziente.length() == 16,
        "La lunghezza del codice fiscale deve essere di 16 caratteri");
    for (CodiceFiscaleDto tutore : list) {
      tutore.validate();
    }
  }
}
