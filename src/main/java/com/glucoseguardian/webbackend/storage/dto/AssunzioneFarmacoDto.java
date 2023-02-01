package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.io.Serializable;

/**
 * Rappresenta l'entita assunzione farmaco.
 */
@JsonInclude(Include.NON_ABSENT)
public class AssunzioneFarmacoDto extends RisultatoDto implements Serializable {

  private Long id;
  private Long idFarmaco;
  private String dosaggio;
  private String orarioAssunzione;
  private String viaDiSomministrazione;
  private String noteAggiuntive;

  /**
   * Costruttore completo.
   */
  public AssunzioneFarmacoDto(Long id, Long idFarmaco, String dosaggio, String orarioAssunzione,
      String viaDiSomministrazione, String noteAggiuntive) {
    this.id = id;
    this.idFarmaco = idFarmaco;
    this.dosaggio = dosaggio;
    this.orarioAssunzione = orarioAssunzione;
    this.viaDiSomministrazione = viaDiSomministrazione;
    this.noteAggiuntive = noteAggiuntive;
  }

  public AssunzioneFarmacoDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdFarmaco() {
    return idFarmaco;
  }

  public void setIdFarmaco(Long idFarmaco) {
    this.idFarmaco = idFarmaco;
  }

  public String getDosaggio() {
    return dosaggio;
  }

  public void setDosaggio(String dosaggio) {
    this.dosaggio = dosaggio;
  }

  public String getOrarioAssunzione() {
    return orarioAssunzione;
  }

  public void setOrarioAssunzione(String orarioAssunzione) {
    this.orarioAssunzione = orarioAssunzione;
  }

  public String getViaDiSomministrazione() {
    return viaDiSomministrazione;
  }

  public void setViaDiSomministrazione(String viaDiSomministrazione) {
    this.viaDiSomministrazione = viaDiSomministrazione;
  }

  public String getNoteAggiuntive() {
    return noteAggiuntive;
  }

  public void setNoteAggiuntive(String noteAggiuntive) {
    this.noteAggiuntive = noteAggiuntive;
  }

  /**
   *  Costruisce un {@link AssunzioneFarmacoDto} a partire da un {@link AssunzioneFarmaco}.
   */
  public static AssunzioneFarmacoDto valueOf(AssunzioneFarmaco assunzioneFarmaco) {
    String timeString = assunzioneFarmaco.getOrarioAssunzione().toString();

    AssunzioneFarmacoDto assunzioneFarmacoDto = new AssunzioneFarmacoDto();
    assunzioneFarmacoDto.setIdFarmaco(assunzioneFarmaco.getFarmaco().getId());
    assunzioneFarmacoDto.setId(assunzioneFarmaco.getId());
    assunzioneFarmacoDto.setDosaggio(assunzioneFarmaco.getDosaggio());
    assunzioneFarmacoDto.setOrarioAssunzione(timeString);
    assunzioneFarmacoDto.setViaDiSomministrazione(assunzioneFarmaco.getViaDiSomministrazione());
    assunzioneFarmacoDto.setNoteAggiuntive(assunzioneFarmacoDto.getNoteAggiuntive());
    return assunzioneFarmacoDto;
  }
}
