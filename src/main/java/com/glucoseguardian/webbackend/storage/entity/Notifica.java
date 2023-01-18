package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entity notifica.
 */
@Entity
public class Notifica implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "UNISGNED INT", nullable = false)
  private long id;

  @Column(columnDefinition = "UNISGNED INT", length = 1024, nullable = false)
  private String messaggio;

  @Column(nullable = false)
  private Date data;

  @Column(nullable = false)
  private Time ora;

  @Column(columnDefinition = "UNSIGNED INT(1)", nullable = false)
  private int stato;

  public Notifica() {

  }

  /**
   * Costruttore predefinito dell'entity Notifica.
   */
  public Notifica(long id, String messaggio, Date data, Time ora, int stato) {
    this.id = id;
    this.messaggio = messaggio;
    this.data = data;
    this.ora = ora;
    this.stato = stato;
  }

  @Override
  public String toString() {
    return "Notifica{"
        + "id=" + id
        + ", messaggio='" + messaggio + '\''
        + ", data=" + data
        + ", ora=" + ora + ", stato=" + stato
        + '}';
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public int getStato() {
    return stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }

  public Time getOra() {
    return ora;
  }

  public void setOra(Time ora) {
    this.ora = ora;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notifica notifica = (Notifica) o;
    return id == notifica.id && stato == notifica.stato && messaggio.equals(notifica.messaggio)
        && data.equals(notifica.data) && ora.equals(notifica.ora);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, messaggio, data, ora, stato);
  }
}
