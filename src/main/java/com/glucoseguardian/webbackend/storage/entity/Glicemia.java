package com.glucoseguardian.webbackend.storage.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Classe che rappresenta un'istanza dell'entity Glicemia.
 */
@Entity
public class Glicemia implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private int livelloGlucosio;
  private @NotNull Timestamp dataOra;

  @ManyToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;

  /**
   * Costruttore predefinito della classe Glicemia.
   */
  public Glicemia(Integer livelloGlucosio, @NotNull Timestamp dataOra) {
    this.livelloGlucosio = livelloGlucosio;
    this.dataOra = dataOra;
  }

  public Glicemia() {
  }

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

  public @NotNull Timestamp getDataOra() {
    return dataOra;
  }

  public void setDataOra(@NotNull Timestamp dataOra) {
    this.dataOra = dataOra;
  }

  public Paziente getPaziente() {
    return paziente;
  }

  public void setPaziente(Paziente paziente) {
    this.paziente = paziente;
  }

  @Override
  public String toString() {
    return "Glicemia{" + "id=" + id + ", livelloGlucosio=" + livelloGlucosio + ", data=" + dataOra
        + ", paziente=" + paziente + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Glicemia) {
      return Objects.equals(id, ((Glicemia) o).id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }


}
