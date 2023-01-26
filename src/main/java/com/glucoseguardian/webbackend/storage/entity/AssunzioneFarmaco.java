package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

/**
 * classe entity AssunzioneFarmaco.
 */
@Entity
public class AssunzioneFarmaco implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @ManyToOne
  @JoinColumn(name = "farmaco")
  private Farmaco farmaco;
  @NotNull
  private int dosaggio;
  @NotNull
  private Time orarioAssunzione;

  @Column(length = 300)
  @NotNull
  private String viaDiSomministrazione;
  @Column(length = 300)
  @NotNull
  private String noteAggiuntive;
  @ManyToOne
  @JoinColumn(name = "terapia")
  private Terapia terapia;

  /**
   * costruttore entity.
   */
  public AssunzioneFarmaco(long id, Farmaco farmaco, int dosaggio, Time orarioAssunzione,
      String viaDiSomministrazione, String noteAggiuntive) {
    this.id = id;
    this.farmaco = farmaco;
    this.dosaggio = dosaggio;
    this.orarioAssunzione = orarioAssunzione;
    this.viaDiSomministrazione = viaDiSomministrazione;
    this.noteAggiuntive = noteAggiuntive;
  }

  /**
   * costruttore entity senza id.
   */
  public AssunzioneFarmaco(Farmaco farmaco, int dosaggio, Time orarioAssunzione,
      String viaDiSomministrazione, String noteAggiuntive) {
    this.farmaco = farmaco;
    this.dosaggio = dosaggio;
    this.orarioAssunzione = orarioAssunzione;
    this.viaDiSomministrazione = viaDiSomministrazione;
    this.noteAggiuntive = noteAggiuntive;
  }

  public AssunzioneFarmaco() {

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Farmaco getFarmaco() {
    return farmaco;
  }

  public void setFarmaco(Farmaco farmaco) {
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

  public Terapia getTerapia() {
    return terapia;
  }

  public void setTerapia(Terapia terapia) {
    this.terapia = terapia;
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
    return id == that.id && dosaggio == that.dosaggio && Objects.equals(farmaco,
        that.farmaco) && Objects.equals(orarioAssunzione, that.orarioAssunzione)
        && Objects.equals(viaDiSomministrazione, that.viaDiSomministrazione)
        && Objects.equals(noteAggiuntive, that.noteAggiuntive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, farmaco, dosaggio, orarioAssunzione, viaDiSomministrazione,
        noteAggiuntive);
  }
}
