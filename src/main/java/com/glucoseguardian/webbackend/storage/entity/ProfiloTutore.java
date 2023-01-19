package com.glucoseguardian.webbackendstorage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Questa classe rappresenta l'entità ProfiloTutore.
 */
@Entity
public class ProfiloTutore implements Serializable {

  @Id
  @Column(columnDefinition = "CHAR(16)")
  private String codiceFiscale;

  @Column(columnDefinition = "VARCHAR(30)", nullable = false)
  private String nome;
  @Column(columnDefinition = "VARCHAR(30)", nullable = false)

  private String cognome;
  @Column(nullable = false)
  private Date dataNascita;
  @Column(columnDefinition = "VARCHAR(50)", nullable = false)
  private String indirizzo;
  @Column(columnDefinition = "VARCHAR(15)", nullable = false)
  private String telefono;
  @Column(columnDefinition = "VARCHAR(255)", nullable = false)
  private String email;
  @Column(columnDefinition = "VARCHAR(255)", nullable = false)
  private String password;
  @Column(columnDefinition = "CHAR(1)", nullable = false)
  private char sesso;

  @Column(columnDefinition = "VARCHAR(255)", nullable = false)
  private String totpKey;

  @Column(columnDefinition = "VARCHAR(255)", nullable = false)
  private String relazioneDiParentela;

  public ProfiloTutore() {
  }

  /**
   * Costruttore di default della classe ProfiloTutore.
   */
  public ProfiloTutore(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, char sesso, String totpKey,
      String relazioneDiParentela) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
    this.totpKey = totpKey;
    this.relazioneDiParentela = relazioneDiParentela;
  }

  @Override
  public String toString() {
    return "ProfiloTutore{"
        + "codiceFiscale='" + codiceFiscale + '\''
        + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\''
        + ", dataNascita=" + dataNascita
        + ", indirizzo='" + indirizzo + '\''
        + ", telefono='" + telefono + '\''
        + ", email='" + email + '\''
        + ", password='" + password + '\''
        + ", sesso=" + sesso
        + ", totpKey='" + totpKey + '\''
        + ", relazioneDiParentela='" + relazioneDiParentela + '\''
        + '}';
  }
}