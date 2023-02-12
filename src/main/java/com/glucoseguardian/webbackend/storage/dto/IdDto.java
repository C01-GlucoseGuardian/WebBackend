package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'entity admin.
 */
@JsonInclude(Include.NON_ABSENT)
public class IdDto extends RisultatoDto implements Serializable {

  private Long id;

  public IdDto() {
  }

  public IdDto(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   *  validazione dell'id.
   */
  public void validate() {
    Validate.notNull(id, "L'id non puo essere assente");
    Validate.isTrue(id >= 0, "L'id non è valido");

  }
}
