package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class TerapiaDto {

  private long id;
  private long idPaziente;
  private long idDottore;
  private String dataInizio;
  private List<AssunzioneFarmacoDto> farmaci;

  /**
   * Costruttore completo.
   */
  public TerapiaDto(long id, long idPaziente, long idDottore, String dataInizio,
      List<AssunzioneFarmacoDto> farmaci) {
    this.id = id;
    this.idPaziente = idPaziente;
    this.idDottore = idDottore;
    this.dataInizio = dataInizio;
    this.farmaci = farmaci;
  }

  public TerapiaDto() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIdPaziente() {
    return idPaziente;
  }

  public void setIdPaziente(long idPaziente) {
    this.idPaziente = idPaziente;
  }

  public List<AssunzioneFarmacoDto> getFarmaci() {
    return farmaci;
  }

  public void setFarmaci(List<AssunzioneFarmacoDto> farmaci) {
    this.farmaci = farmaci;
  }

}
