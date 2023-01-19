package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

/**
 * classe entity AssunzioneFarmaco.
 */
@Entity
public class AssunzioneFarmaco implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "UNSIGNED INT", nullable = false)
  private long id;
  @ManyToOne
  @JoinColumn(name = "farmaco")
  private Farmaco farmaco;
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

  @ManyToOne
  @JoinColumn(name = "dottore")
  private Dottore dottore;

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

  public Dottore getDottore() {
    return dottore;
  }

  public void setDottore(Dottore dottore) {
    this.dottore = dottore;
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
        && Objects.equals(noteAggiuntive, that.noteAggiuntive) && Objects.equals(
        dottore, that.dottore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, farmaco, dosaggio, orarioAssunzione, viaDiSomministrazione,
        noteAggiuntive, dottore);
  }
}
