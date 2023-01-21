package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta l'entita assunzione farmaco.
 */
@JsonInclude(Include.NON_ABSENT)
public class AssunzioneFarmacoDto implements Serializable {

  private long id;
  private long idFarmaco;
  private int dosaggio;
  private String orarioAssunzione;
  private String viaDiSomministrazione;
  private String noteAggiuntive;

  /**
   * Costruttore completo.
   */
  public AssunzioneFarmacoDto(long id, long idFarmaco, int dosaggio, String orarioAssunzione,
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIdFarmaco() {
    return idFarmaco;
  }

  public void setIdFarmaco(long idFarmaco) {
    this.idFarmaco = idFarmaco;
  }

  public int getDosaggio() {
    return dosaggio;
  }

  public void setDosaggio(int dosaggio) {
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
}
