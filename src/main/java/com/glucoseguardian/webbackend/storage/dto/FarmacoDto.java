package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta l'output delle funzioni di Farmaco.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class FarmacoDto implements Serializable {

  private Long id;
  private String nomeFarmaco;
  private String principioAttivo;
  private String confezione;

  public FarmacoDto() {
  }

  /**
   * Costruttore completo.
   */
  public FarmacoDto(Long id, String nomeFarmaco, String principioAttivo, String confezione) {
    this.id = id;
    this.nomeFarmaco = nomeFarmaco;
    this.principioAttivo = principioAttivo;
    this.confezione = confezione;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeFarmaco() {
    return nomeFarmaco;
  }

  public void setNomeFarmaco(String nomeFarmaco) {
    this.nomeFarmaco = nomeFarmaco;
  }

  public String getPrincipioAttivo() {
    return principioAttivo;
  }

  public void setPrincipioAttivo(String principioAttivo) {
    this.principioAttivo = principioAttivo;
  }

  public String getConfezione() {
    return confezione;
  }

  public void setConfezione(String confezione) {
    this.confezione = confezione;
  }
  public static FarmacoDto valueOf(Farmaco farmaco) {
    FarmacoDto farmacoDto = new FarmacoDto();
    farmacoDto.setId(farmaco.getId());
    farmacoDto.setNomeFarmaco(farmaco.getNomeFarmaco());
    farmacoDto.setConfezione(farmaco.getConfezione());
    farmacoDto.setPrincipioAttivo(farmaco.getPrincipioAttivo());
    return farmacoDto;
  }
}
