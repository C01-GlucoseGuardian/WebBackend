package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class TerapiaDto extends RisultatoDto implements Serializable {

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

  public String getIdDottore() {
    return idDottore;
  }

  public void setIdDottore(String idDottore) {
    this.idDottore = idDottore;
  }

  public String getDataInizio() {
    return dataInizio;
  }

  public void setDataInizio(String dataInizio) {
    this.dataInizio = dataInizio;
  }

  public List<AssunzioneFarmacoDto> getFarmaci() {
    return farmaci;
  }

  public void setFarmaci(
      List<AssunzioneFarmacoDto> farmaci) {
    this.farmaci = farmaci;
  }

  /**
   *  Costruisce un {@link TerapiaDto} a partire da un {@link Terapia}.
   */
  public static TerapiaDto valueOf(Terapia terapia) {
    List<AssunzioneFarmacoDto> list = new ArrayList<>();
    for (AssunzioneFarmaco assunzioneFarmaco : terapia.getAssunzioneFarmacos()) {
      list.add(AssunzioneFarmacoDto.valueOf(assunzioneFarmaco));
    }
    TerapiaDto terapiaDto = new TerapiaDto();
    terapiaDto.setId(terapia.getId());
    terapiaDto.setIdPaziente(terapia.getPaziente().getCodiceFiscale());
    terapiaDto.setIdDottore(terapia.getDottore().getCodiceFiscale());
    terapiaDto.setFarmaci(list);

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dataInizioTerapia = dateFormat.format(terapia.getDataInizio());
    terapiaDto.setDataInizio(dataInizioTerapia);

    return terapiaDto;
  }
  public void validate(){
    Validate.notNull(id,"l'id non può essere assente");
    for (AssunzioneFarmacoDto assunzioneFarmacoDto:farmaci){
      assunzioneFarmacoDto.validate();
    }
  }
}
