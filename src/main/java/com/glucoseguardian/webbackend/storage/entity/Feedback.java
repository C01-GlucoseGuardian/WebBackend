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
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entità Feedback.
 */
@Entity
public class Feedback implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 300)
  @NotNull
  private String statoSalute;

  @Column(length = 300)
  @NotNull
  private String oreSonno;

  @Column(length = 300)
  @NotNull
  private String dolori;

  @Column(length = 300)
  @NotNull
  private String svenimenti;

  @NotNull
  private Date data;

  @NotNull
  private Time ora;
  @ManyToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;
  @ManyToOne
  @JoinColumn(name = "dottore")
  private Dottore dottore;

  public Feedback(String statoSalute, String oreSonno, String dolori, String svenimenti, Date data,
      Time ora, String codiceFiscalePaziente) {

  }

  /**
   * Costruttore di default della classe Feedback.
   */
  public Feedback(String statoSalute, String oreSonno, String dolori, String svenimenti, Date data,
      Time ora, Paziente paziente) {
    this.id = id;
    this.statoSalute = statoSalute;
    this.oreSonno = oreSonno;
    this.dolori = dolori;
    this.svenimenti = svenimenti;
    this.data = data;
    this.ora = ora;
    this.paziente = paziente;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStatoSalute() {
    return statoSalute;
  }

  public void setStatoSalute(String statoSalute) {
    this.statoSalute = statoSalute;
  }

  public String getOreSonno() {
    return oreSonno;
  }

  public void setOreSonno(String oreSonno) {
    this.oreSonno = oreSonno;
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

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Time getOra() {
    return ora;
  }

  public void setOra(Time ora) {
    this.ora = ora;
  }

  public Paziente getPaziente() {
    return paziente;
  }

  public void setPaziente(Paziente paziente) {
    this.paziente = paziente;
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
    Feedback feedback = (Feedback) o;
    return id == feedback.id && Objects.equals(statoSalute, feedback.statoSalute)
        && Objects.equals(oreSonno, feedback.oreSonno) && Objects.equals(dolori,
        feedback.dolori) && Objects.equals(svenimenti, feedback.svenimenti)
        && Objects.equals(data, feedback.data) && Objects.equals(ora,
        feedback.ora);
  }


}
