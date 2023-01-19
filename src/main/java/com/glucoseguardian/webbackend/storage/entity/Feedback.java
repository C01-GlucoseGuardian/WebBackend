package com.glucoseguardian.webbackendstorage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Questa classe rappresenta l'entità Feedback.
 */
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

  public Feedback() {

  }

  /**
   * Costruttore di default della classe Feedback.
   */
  public Feedback(String statoSalute, String oreSonno, String dolori, String svenimenti,
      Date data, Time ora) {
    this.statoSalute = statoSalute;
    this.oreSonno = oreSonno;
    this.dolori = dolori;
    this.svenimenti = svenimenti;
    this.data = data;
    this.ora = ora;
  }

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

  @Override
  public String toString() {
    return "Feedback{"
        + "id=" + id
        + ", statoSalute='" + statoSalute + '\''
        + ", oreSonno='" + oreSonno + '\''
        + ", dolori='" + dolori + '\''
        + ", svenimenti='" + svenimenti + '\''
        + ", data=" + data
        + ", ora=" + ora
        + '}';
  }
}
