package com.glucoseguardian.webbackend.storage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entità Terapia.
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        columnNames = {"productId", "serial"})
})
public class Terapia implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Nullable
  private Date dataInizio;

  @OneToMany(mappedBy = "terapia", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<AssunzioneFarmaco> assunzioneFarmacos;

  @ManyToOne
  @JoinColumn(name = "dottore")
  private Dottore dottore;

  @OneToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDataInizio() {
    return dataInizio;
  }

  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  public List<AssunzioneFarmaco> getAssunzioneFarmacos() {
    return assunzioneFarmacos;
  }

  public void setAssunzioneFarmacos(List<AssunzioneFarmaco> assunzioneFarmacos) {
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
    return id == terapia.id && Objects.equals(dataInizio, terapia.dataInizio) && Objects.equals(
        assunzioneFarmacos, terapia.assunzioneFarmacos) && Objects.equals(dottore, terapia.dottore)
        && Objects.equals(paziente, terapia.paziente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Terapia{" + "id=" + id + ", dataInizio=" + dataInizio + ", pazientes=" + paziente
        + ", assunzioneFarmacos=" + assunzioneFarmacos + ", dottore=" + dottore + '}';
  }
}
