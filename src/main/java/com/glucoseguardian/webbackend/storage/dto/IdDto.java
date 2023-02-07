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

  private long id;

  public IdDto() {
  }

  public IdDto(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  /**
   *  validazione dell'id.
   */
  public void validate() {
    Validate.notNull(id, "l'id non puo essere null");
  }
}
