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
import java.util.Objects;

/**
 * Questa classe rappresenta l'entity NumeroTelefono.
 */
@Entity
public class NumeroTelefono implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 15)
  @NotNull
  private String numero;
  @ManyToOne
  @JoinColumn(name = "paziente")
  private Paziente paziente;

  public NumeroTelefono() {

  }

  /**
   * Costruttore della entity Numero di telefono.
   */

  public NumeroTelefono(String numero, Paziente paziente) {
    this.numero = numero;
    this.paziente = paziente;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
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
    NumeroTelefono that = (NumeroTelefono) o;
    return id == that.id && Objects.equals(numero, that.numero) && Objects.equals(
        paziente, that.paziente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numero, paziente);
  }

  @Override
  public String toString() {
    return "NumeroTelefono{" + "id=" + id + ", numero='" + numero + '\'' + ", paziente=" + paziente
        + '}';
  }


}
