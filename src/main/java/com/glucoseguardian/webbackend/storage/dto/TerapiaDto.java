package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class TerapiaDto implements Serializable {

  private Long id;
  private String idPaziente;
  private String idDottore;
  private String dataInizio;
  private List<AssunzioneFarmacoDto> farmaci;

  /**
   * Costruttore completo.
   */
  public TerapiaDto(Long id, String idPaziente, String idDottore, String dataInizio,
      List<AssunzioneFarmacoDto> farmaci) {
    this.id = id;
    this.idPaziente = idPaziente;
    this.idDottore = idDottore;
    this.dataInizio = dataInizio;
    this.farmaci = farmaci;
  }

  public TerapiaDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdPaziente() {
    return idPaziente;
  }

  public void setIdPaziente(String idPaziente) {
    this.idPaziente = idPaziente;
  }

  public List<AssunzioneFarmacoDto> getFarmaci() {
    return farmaci;
  }

  public void setFarmaci(List<AssunzioneFarmacoDto> farmaci) {
    this.farmaci = farmaci;
  }

}
