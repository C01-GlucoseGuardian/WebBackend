package com.GlucoseGuardian.WebBackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Questa classe rappresenta l'entità Farmaco.
 */
@Entity
public class Farmaco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "UNSIGNED INT")
  private int id;

  @Column(columnDefinition = "VARCHAR(50)")
  private String nomeFarmaco;
  @Column(columnDefinition = "VARCHAR(255)")
  private String principioAttivo;
  @Column(columnDefinition = "VARCHAR(255)")
  private String confezione;


  public Farmaco() {
  }

  /**
   * Costruttore di default della classe Farmaco.
   */
  public Farmaco(String nomeFarmaco, String principioAttivo, String confezione) {
    this.nomeFarmaco = nomeFarmaco;
    this.principioAttivo = principioAttivo;
    this.confezione = confezione;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNomeFarmaco() {
    return nomeFarmaco;
  }

  public void setNomeFarmaco(String nomeFarmaco) {
    this.nomeFarmaco = nomeFarmaco;
  }

  public String getPrincipioAttivo() {
    return principioAttivo;
  }

  public void setPrincipioAttivo(String principioAttivo) {
    this.principioAttivo = principioAttivo;
  }

  public String getConfezione() {
    return confezione;
  }

  public void setConfezione(String confezione) {
    this.confezione = confezione;
  }

  @Override
  public String toString() {
    return "Farmaco{"
        + "id=" + id
        + ", nomeFarmaco='" + nomeFarmaco + '\''
        + ", principioAttivo='" + principioAttivo + '\''
        + ", confezione='" + confezione + '\''
        + '}';
  }
}
