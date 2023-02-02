package com.glucoseguardian.webbackend.storage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Questa classe rappresenta l'entità ProfiloTutore.
 */
@Entity
public class Tutore implements Serializable, Utente {

  @Id
  @Column(columnDefinition = "CHAR(16)")
  @NotNull
  private String codiceFiscale;

  @Column(length = 30)
  @NotNull
  private String nome;
  @Column(length = 30)
  @NotNull
  private String cognome;
  @NotNull
  private Date dataNascita;
  @Column(length = 50)
  @NotNull
  private String indirizzo;
  @Column(length = 15)
  @NotNull
  private String telefono;
  @Column(unique = true)
  @NotNull
  private String email;
  @NotNull
  private String password;
  private char sesso;
  @Nullable
  private String totpKey;

  @Column(nullable = false)
  private String relazioneDiParentela;

  @ManyToMany(mappedBy = "profiliTutore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Paziente> pazienteList;

  @OneToMany(mappedBy = "tutoreDestinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Notifica> notifiche;

  public Tutore() {
  }

  /**
   * Costruttore di default della classe ProfiloTutore.
   */
  public Tutore(String codiceFiscale, String nome, String cognome, Date dataNascita,
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

  public void setPazienteList(
      List<Paziente> pazienteList) {
    this.pazienteList = pazienteList;
  }

  @Override
  public List<Notifica> getNotifiche() {
    return notifiche;
  }

  @Override
  public void setNotifiche(List<Notifica> notifiche) {
    this.notifiche = notifiche;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tutore that = (Tutore) o;
    return sesso == that.sesso && Objects.equals(codiceFiscale, that.codiceFiscale)
        && Objects.equals(nome, that.nome) && Objects.equals(cognome,
        that.cognome) && Objects.equals(dataNascita, that.dataNascita)
        && Objects.equals(indirizzo, that.indirizzo) && Objects.equals(telefono,
        that.telefono) && Objects.equals(email, that.email) && Objects.equals(
        password, that.password) && Objects.equals(totpKey, that.totpKey)
        && Objects.equals(relazioneDiParentela, that.relazioneDiParentela)
        && Objects.equals(pazienteList, that.pazienteList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale);
  }

  @Override
  public String toString() {
    return "ProfiloTutore{" + "codiceFiscale='" + codiceFiscale + '\'' + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\'' + ", dataNascita=" + dataNascita + ", indirizzo='"
        + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
        + ", password='" + password + '\'' + ", sesso=" + sesso + ", totpKey='" + totpKey + '\''
        + ", relazioneDiParentela='" + relazioneDiParentela + '\'' + '}';
  }

  public TipoUtente getTipoUtente() {
    return TipoUtente.TUTORE;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(getTipoUtente().name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}