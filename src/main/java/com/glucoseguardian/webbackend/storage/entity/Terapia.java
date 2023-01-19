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
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entità Terapia.
 */
@Entity
public class Terapia implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "UNSIGNED INT", nullable = false)
  private int id;
  @Column
  private Date dataInizio;
  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Feedback> feedbacks;

  @OneToMany(mappedBy = "dottore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AssunzioneFarmaco> assunzioneFarmacos;

  @ManyToOne
  @JoinColumn(name = "dottore")
  private Dottore dottore;

  @OneToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDataInizio() {
    return dataInizio;
  }

  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(
      List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public List<AssunzioneFarmaco> getAssunzioneFarmacos() {
    return assunzioneFarmacos;
  }

  public void setAssunzioneFarmacos(
      List<AssunzioneFarmaco> assunzioneFarmacos) {
    this.assunzioneFarmacos = assunzioneFarmacos;
  }

  public Dottore getDottore() {
    return dottore;
  }

  public void setDottore(Dottore dottore) {
    this.dottore = dottore;
  }

  public Paziente getPaziente() {
    return paziente;
  }

  public void setPaziente(Paziente paziente) {
    this.paziente = paziente;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Terapia terapia = (Terapia) o;
    return id == terapia.id && Objects.equals(dataInizio, terapia.dataInizio)
        && Objects.equals(feedbacks, terapia.feedbacks) && Objects.equals(
        assunzioneFarmacos, terapia.assunzioneFarmacos) && Objects.equals(dottore,
        terapia.dottore) && Objects.equals(paziente, terapia.paziente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataInizio, feedbacks, assunzioneFarmacos, dottore, paziente);
  }

  @Override
  public String toString() {
    return "Terapia{" + "id=" + id
        + ", dataInizio=" + dataInizio
        + ", feedbacks=" + feedbacks
        + ", pazientes=" + paziente
        + ", assunzioneFarmacos=" + assunzioneFarmacos
        + ", dottore=" + dottore + '}';
  }
}
