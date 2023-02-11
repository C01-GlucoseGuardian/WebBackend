package com.glucoseguardian.webbackend.storage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
 * Questa classe rappresenta l'entity notifica.
 */
@Entity
public class Admin implements Serializable, Utente {

  @Id
  @Column(length = 16)
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

  @OneToMany(mappedBy = "adminDestinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Notifica> notifiche;
  @OneToMany(mappedBy = "convalidatoDa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Dottore> dottoriConvalidati;

  public Admin() {

  }

  /**
   * Costruttore predefinito della classe Admin.
   */
  public Admin(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, char sesso, String totpKey,
      String specializzazione, String codiceAlbo, String nomeStruttura, String indirizzoStruttura) {
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
  }

  @Override
  public String toString() {
    return "Admin{" + "codiceFiscale='" + codiceFiscale + '\'' + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\'' + ", dataNascita=" + dataNascita + ", indirizzo='"
        + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
        + ", password='" + password + '\'' + ", sesso=" + sesso + ", totpKey='" + totpKey + "'}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Admin admin = (Admin) o;
    return Objects.equals(codiceFiscale, admin.codiceFiscale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale);
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
  
  public List<Notifica> getNotifiche() {
    return notifiche;
  }

  public void setNotifiche(List<Notifica> notifiche) {
    this.notifiche = notifiche;
  }

  public List<Dottore> getDottoriConvalidati() {
    return dottoriConvalidati;
  }

  public void setDottoriConvalidati(List<Dottore> dottoriConvalidati) {
    this.dottoriConvalidati = dottoriConvalidati;
  }

  @Override
  public TipoUtente getTipoUtente() {
    return TipoUtente.ADMIN;
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
