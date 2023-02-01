package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.Feedback;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Rappresenta l'entity feedback.
 */
@JsonInclude(Include.NON_ABSENT)
public class FeedbackDto extends RisultatoDto implements Serializable {

  private Long id;
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
  public FeedbackDto(Long id, String oreSonno, String statoSalute, String dolori, String svenimenti,
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  /**
   *  Costruisce un {@link FeedbackDto} a partire da un {@link Feedback}.
   */
  public static FeedbackDto valueOf(Feedback feedback) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String dataFeedbackDto = dateFormat.format(feedback.getData());

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String timeString = sdf.format(feedback.getOra());

    FeedbackDto feedbackDto = new FeedbackDto();
    feedbackDto.setId(feedbackDto.getId());
    feedbackDto.setOreSonno(feedback.getOreSonno());
    feedbackDto.setStatoSalute(feedback.getStatoSalute());
    feedbackDto.setDolori(feedback.getDolori());
    feedbackDto.setSvenimenti(feedback.getSvenimenti());
    feedbackDto.setData(dataFeedbackDto);
    feedbackDto.setOra(timeString);
    feedbackDto.setIdDottore(feedback.getDottore().getCodiceFiscale());
    feedbackDto.setIdPaziente(feedback.getPaziente().getCodiceFiscale());
    return feedbackDto;
  }
}