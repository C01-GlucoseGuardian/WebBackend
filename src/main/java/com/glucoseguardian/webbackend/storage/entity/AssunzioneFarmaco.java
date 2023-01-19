package com.glucoseguardian.webbackendstorage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

/**
 * classe entity AssunzioneFarmaco.
 */
@Entity
public class AssunzioneFarmaco implements Serializable {

  @Id
  @GeneratedValue
  @Column(columnDefinition = "UNSIGNED INT", nullable = false)
  private long id;
  @Column(length = 50, nullable = false)
  private String farmaco;
  @Column(nullable = false)
  private int dosaggio;
  @Column(nullable = false)
  private Time orarioAssunzione;
  @Column(length = 300, nullable = false)
  private String viaDiSomministrazione;
  @Column(length = 300)
  private String noteAggiuntive;

  /**
   * costruttore entity.
   */
  public AssunzioneFarmaco(String farmaco, int dosaggio, Time orarioAssunzione,
      String viaDiSomministrazione, String noteAggiuntive) {
    this.farmaco = farmaco;
    this.dosaggio = dosaggio;
    this.orarioAssunzione = orarioAssunzione;
    this.viaDiSomministrazione = viaDiSomministrazione;
    this.noteAggiuntive = noteAggiuntive;
  }

  public AssunzioneFarmaco() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFarmaco() {
    return farmaco;
  }

  public void setFarmaco(String farmaco) {
    this.farmaco = farmaco;
  }

  public int getDosaggio() {
    return dosaggio;
  }

  public void setDosaggio(int dosaggio) {
    this.dosaggio = dosaggio;
  }

  public Time getOrarioAssunzione() {
    return orarioAssunzione;
  }

  public void setOrarioAssunzione(Time orarioAssunzione) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssunzioneFarmaco that = (AssunzioneFarmaco) o;
    return id == that.id && dosaggio == that.dosaggio && farmaco.equals(that.farmaco)
        && orarioAssunzione.equals(that.orarioAssunzione) && viaDiSomministrazione.equals(
        that.viaDiSomministrazione) && Objects.equals(noteAggiuntive, that.noteAggiuntive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, farmaco, dosaggio, orarioAssunzione, viaDiSomministrazione,
        noteAggiuntive);
  }

  @Override
  public String toString() {
    return "AssunzioneFarmaco{"
        + "id=" + id
        + ", farmaco='" + farmaco + '\''
        + ", dosaggio=" + dosaggio
        + ", orario_assunzione=" + orarioAssunzione
        + ", via_di_somministrazione='" + viaDiSomministrazione + '\''
        + ", note_aggiuntive='" + noteAggiuntive + '\''
        + '}';
  }
}
