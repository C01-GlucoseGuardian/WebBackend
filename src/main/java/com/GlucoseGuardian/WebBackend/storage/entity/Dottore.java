package com.GlucoseGuardian.WebBackend.storage.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Dottore implements Serializable {

  @Id
  @Column(length = 16, nullable = false)
  private String codice_fiscale;
  @Column(length = 30, nullable = false)
  private String nome;
  @Column(length = 30, nullable = false)
  private String cognome;
  @Column(nullable = false)
  private Date data_nascita;
  @Column(length = 50, nullable = false)
  private String indirizzo;
  @Column(length = 15, nullable = false)
  private String telefono;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(columnDefinition = "CHAR(1)")
  private char sesso;
  @Column()
  private String totp_key;
  @Column(length = 100, nullable = false)
  private String specializzazione;
  @Column(length = 50, nullable = false)
  private String codice_albo;
  @Column(length = 100, nullable = false)
  private String nome_struttura;
  @Column(length = 100, nullable = false)
  private String indirizzo_struttura;
  @Column(columnDefinition = "UNSIGNED INT(1)")
  private int stato = 0;

  public String getCodice_fiscale() {
    return codice_fiscale;
  }

  public void setCodice_fiscale(String codice_fiscale) {
    this.codice_fiscale = codice_fiscale;
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

  public Date getData_nascita() {
    return data_nascita;
  }

  public void setData_nascita(Date data_nascita) {
    this.data_nascita = data_nascita;
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

  public String getTotp_key() {
    return totp_key;
  }

  public void setTotp_key(String totp_key) {
    this.totp_key = totp_key;
  }

  public String getSpecializzazione() {
    return specializzazione;
  }

  public void setSpecializzazione(String specializzazione) {
    this.specializzazione = specializzazione;
  }

  public String getCodice_albo() {
    return codice_albo;
  }

  public void setCodice_albo(String codice_albo) {
    this.codice_albo = codice_albo;
  }

  public String getNome_struttura() {
    return nome_struttura;
  }

  public void setNome_struttura(String nome_struttura) {
    this.nome_struttura = nome_struttura;
  }

  public String getIndirizzo_struttura() {
    return indirizzo_struttura;
  }

  public void setIndirizzo_struttura(String indirizzo_struttura) {
    this.indirizzo_struttura = indirizzo_struttura;
  }

  public int getStato() {
    return stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  public Dottore(String codice_fiscale, String nome, String cognome, Date data_nascita,
      String indirizzo, String telefono,
      String email, String password, char sesso, String totp_key, String specializzazione,
      String codice_albo, String nome_struttura, String indirizzo_struttura, int stato) {
    this.codice_fiscale = codice_fiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.data_nascita = data_nascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
    this.totp_key = totp_key;
    this.specializzazione = specializzazione;
    this.codice_albo = codice_albo;
    this.nome_struttura = nome_struttura;
    this.indirizzo_struttura = indirizzo_struttura;
    this.stato = stato;
  }

  public Dottore() {
  }

  @Override
  public String toString() {
    return "Dottore{" +
        "codice_fiscale='" + codice_fiscale + '\'' +
        ", nome='" + nome + '\'' +
        ", cognome='" + cognome + '\'' +
        ", data_nascita=" + data_nascita +
        ", indirizzo='" + indirizzo + '\'' +
        ", telefono='" + telefono + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", sesso='" + sesso + '\'' +
        ", totp_key='" + totp_key + '\'' +
        ", specializzazione='" + specializzazione + '\'' +
        ", codice_albo='" + codice_albo + '\'' +
        ", nome_struttura='" + nome_struttura + '\'' +
        ", indirizzo_struttura='" + indirizzo_struttura + '\'' +
        ", stato=" + stato +
        '}';
  }
}
