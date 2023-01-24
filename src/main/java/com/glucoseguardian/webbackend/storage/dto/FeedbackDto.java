package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Rappresenta l'entity feedback.
 * TODO: to correct...
 */
@JsonInclude(Include.NON_ABSENT)
public class FeedbackDto {

  private long id;
  private String oreSonno;
  private String statoSalute;
  private String dolori;
  private String svenimenti;
  private String data;
  private String ora;
  private String idPaziente;
  private String idDottore;


  /**
   * Costruttore completo.
   */
  public FeedbackDto(long id, String oreSonno, String statoSalute, String dolori, String svenimenti,
      String data, String ora, String idPaziente, String idDottore) {
    this.id = id;
    this.oreSonno = oreSonno;
    this.statoSalute = statoSalute;
    this.dolori = dolori;
    this.svenimenti = svenimenti;
    this.data = data;
    this.ora = ora;
    this.idPaziente = idPaziente;
    this.idDottore = idDottore;
  }

  public FeedbackDto() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getOreSonno() {
    return oreSonno;
  }

  public void setOreSonno(String oreSonno) {
    this.oreSonno = oreSonno;
  }

  public String getStatoSalute() {
    return statoSalute;
  }

  public void setStatoSalute(String statoSalute) {
    this.statoSalute = statoSalute;
  }

  public String getDolori() {
    return dolori;
  }

  public void setDolori(String dolori) {
    this.dolori = dolori;
  }

  public String getSvenimenti() {
    return svenimenti;
  }

  public void setSvenimenti(String svenimenti) {
    this.svenimenti = svenimenti;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getOra() {
    return ora;
  }

  public void setOra(String ora) {
    this.ora = ora;
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
}