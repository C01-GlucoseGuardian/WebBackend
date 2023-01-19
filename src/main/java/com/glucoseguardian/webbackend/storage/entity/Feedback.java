package com.glucoseguardian.webbackend.storage.entity;

import com.glucoseguardian.webbackendstorage.entity.Paziente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entità Feedback.
 */
@Entity
public class Feedback implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(length = 300, nullable = false)
  private String statoSalute;

  @Column(length = 300, nullable = false)
  private String oreSonno;

  @Column(length = 300, nullable = false)
  private String dolori;

  @Column(length = 300, nullable = false)
  private String svenimenti;

  @Column(nullable = false)
  private Date data;

  @Column(nullable = false)
  private Time ora;
  @ManyToOne
  @JoinColumn(name = "codiceFiscale")
  private com.glucoseguardian.webbackendstorage.entity.Paziente paziente;

  public Feedback() {

  }

  public Feedback(int id, String statoSalute, String oreSonno, String dolori, String svenimenti,
      Date data, Time ora, Paziente paziente) {
    this.id = id;
    this.statoSalute = statoSalute;
    this.oreSonno = oreSonno;
    this.dolori = dolori;
    this.svenimenti = svenimenti;
    this.data = data;
    this.ora = ora;
    this.paziente = paziente;
  }

  /**
   * Costruttore di default della classe Feedback.
   */


  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  @Override
  public int hashCode() {
    return Objects.hash(id, statoSalute, oreSonno, dolori, svenimenti, data, ora);
  }

  @Override
  public String toString() {
    return "Feedback{" + "id=" + id + ", statoSalute='" + statoSalute + '\'' + ", oreSonno='"
        + oreSonno + '\'' + ", dolori='" + dolori + '\'' + ", svenimenti='" + svenimenti + '\''
        + ", data=" + data + ", ora=" + ora + ", paziente=" + paziente + '}';
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
    return id == feedback.id && Objects.equals(statoSalute, feedback.statoSalute) && Objects.equals(
        oreSonno, feedback.oreSonno) && Objects.equals(dolori, feedback.dolori) && Objects.equals(
        svenimenti, feedback.svenimenti) && Objects.equals(data, feedback.data) && Objects.equals(
        ora, feedback.ora) && Objects.equals(paziente, feedback.paziente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, statoSalute, oreSonno, dolori, svenimenti, data, ora, paziente);
  }
}
