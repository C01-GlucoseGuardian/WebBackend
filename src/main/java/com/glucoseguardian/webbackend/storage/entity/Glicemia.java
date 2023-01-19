package com.glucoseguardian.webbackend.storage.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Classe che rappresenta un'istanza dell'entity Glicemia.
 */
@Entity
public class Glicemia implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private long id;
  @Column(nullable = false)
  private int livelloGlucosio;
  @Column(nullable = false)
  private Date data;
  @Column(nullable = false)
  private Time ora;

  @ManyToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getLivelloGlucosio() {
    return livelloGlucosio;
  }

  public void setLivelloGlucosio(int livelloGlucosio) {
    this.livelloGlucosio = livelloGlucosio;
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

  /**
   * Costruttore predefinito della classe Glicemia.
   */
  public Glicemia(int livelloGlucosio, Date data, Time ora) {
    this.livelloGlucosio = livelloGlucosio;
    this.data = data;
    this.ora = ora;
  }

  public Glicemia() {
  }


  @Override
  public String toString() {
    return "Glicemia{"
        + "id=" + id
        + ", livelloGlucosio=" + livelloGlucosio
        + ", data=" + data
        + ", ora=" + ora
        + ", paziente=" + paziente
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Glicemia glicemia = (Glicemia) o;
    return id == glicemia.id && livelloGlucosio == glicemia.livelloGlucosio
        && Objects.equals(data, glicemia.data) && Objects.equals(ora,
        glicemia.ora) && Objects.equals(paziente, glicemia.paziente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, livelloGlucosio, data, ora, paziente);
  }


}
