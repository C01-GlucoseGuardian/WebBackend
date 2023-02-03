package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta un DTO di ricerca, contiene l'attributo query.
 */
@JsonInclude(Include.NON_ABSENT)
public class RicercaDto extends RisultatoDto implements Serializable {

  private String query;

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public RicercaDto(String query) {
    this.query = query;
  }

  public RicercaDto() {
  }
}
