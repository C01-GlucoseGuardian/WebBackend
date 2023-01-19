package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entità ProfiloTutore.
 */
@Entity
public class ProfiloTutore implements Serializable {

  @Id
  @Column(columnDefinition = "CHAR(16)")
  private String codiceFiscale;

  @Column(length = 30, nullable = false)
  private String nome;
  @Column(length = 30, nullable = false)

  private String cognome;
  @Column(nullable = false)
  private Date dataNascita;
  @Column(length = 50, nullable = false)
  private String indirizzo;
  @Column(length = 15, nullable = false)
  private String telefono;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(columnDefinition = "CHAR(1)", nullable = false)
  private char sesso;

  @Column(nullable = false)
  private String totpKey;

  @Column(nullable = false)
  private String relazioneDiParentela;

  @ManyToMany(mappedBy = "Paziente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Paziente> pazienteList;

  public ProfiloTutore() {
  }

  /**
   * Costruttore di default della classe ProfiloTutore.
   */
  public ProfiloTutore(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, char sesso, String totpKey,
      String relazioneDiParentela, List<Paziente> pazienteList) {
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
    this.pazienteList = pazienteList;
  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public Date getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getSesso() {
    return sesso;
  }

  public void setSesso(char sesso) {
    this.sesso = sesso;
  }

  public String getTotpKey() {
    return totpKey;
  }

  public void setTotpKey(String totpKey) {
    this.totpKey = totpKey;
  }

  public String getRelazioneDiParentela() {
    return relazioneDiParentela;
  }

  public void setRelazioneDiParentela(String relazioneDiParentela) {
    this.relazioneDiParentela = relazioneDiParentela;
  }

  public List<Paziente> getPazienteList() {
    return pazienteList;
  }

  public void setPazienteList(List<Paziente> pazienteList) {
    this.pazienteList = pazienteList;
  }

  @Override
  public String toString() {
    return "ProfiloTutore{" + "codiceFiscale='" + codiceFiscale + '\'' + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\'' + ", dataNascita=" + dataNascita + ", indirizzo='"
        + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
        + ", password='" + password + '\'' + ", sesso=" + sesso + ", totpKey='" + totpKey + '\''
        + ", relazioneDiParentela='" + relazioneDiParentela + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfiloTutore that = (ProfiloTutore) o;
    return getCodiceFiscale().equals(that.getCodiceFiscale());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCodiceFiscale());
  }
}