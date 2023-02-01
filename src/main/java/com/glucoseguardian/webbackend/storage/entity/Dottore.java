package com.glucoseguardian.webbackend.storage.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * Classe che rappresenta un'istanza dell'entity Dottore.
 */
@Entity
public class Dottore implements Serializable, Utente {

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
  @Column(length = 100)
  @NotNull
  private String specializzazione;
  @Column(length = 50)
  @NotNull
  private String codiceAlbo;
  @Column(length = 100)
  @NotNull
  private String nomeStruttura;
  @Column(length = 100)
  @NotNull
  private String indirizzoStruttura;
  private int stato = 0;
  @OneToMany(mappedBy = "dottore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Feedback> feedbacks;
  @OneToMany(mappedBy = "dottore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Paziente> pazientes;
  @OneToMany(mappedBy = "dottore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Terapia> terapias;
  @OneToMany(mappedBy = "dottoreDestinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Notifica> notificas;
  @ManyToOne
  @JoinColumn(name = "convalidatoDa")
  private Admin convalidatoDa;

  /**
   * Costruttore predefinito della classe Dottore.
   */
  public Dottore(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, char sesso, String totpKey,
      String specializzazione, String codiceAlbo, String nomeStruttura, String indirizzoStruttura,
      int stato) {
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
    this.specializzazione = specializzazione;
    this.codiceAlbo = codiceAlbo;
    this.nomeStruttura = nomeStruttura;
    this.indirizzoStruttura = indirizzoStruttura;
    this.stato = stato;
  }

  public Dottore() {
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

  public String getSpecializzazione() {
    return specializzazione;
  }

  public void setSpecializzazione(String specializzazione) {
    this.specializzazione = specializzazione;
  }

  public String getCodiceAlbo() {
    return codiceAlbo;
  }

  public void setCodiceAlbo(String codiceAlbo) {
    this.codiceAlbo = codiceAlbo;
  }

  public String getNomeStruttura() {
    return nomeStruttura;
  }

  public void setNomeStruttura(String nomeStruttura) {
    this.nomeStruttura = nomeStruttura;
  }

  public String getIndirizzoStruttura() {
    return indirizzoStruttura;
  }

  public void setIndirizzoStruttura(String indirizzoStruttura) {
    this.indirizzoStruttura = indirizzoStruttura;
  }

  public int getStato() {
    return stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(
      List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public List<Paziente> getPazientes() {
    return pazientes;
  }

  public void setPazientes(
      List<Paziente> pazientes) {
    this.pazientes = pazientes;
  }

  public List<Terapia> getTerapias() {
    return terapias;
  }

  public void setTerapias(List<Terapia> terapias) {
    this.terapias = terapias;
  }

  public List<Notifica> getNotifiche() {
    return notificas;
  }

  public void setNotifiche(
      List<Notifica> notificas) {
    this.notificas = notificas;
  }

  public Admin getConvalidatoDa() {
    return convalidatoDa;
  }

  public void setConvalidatoDa(Admin convalidatoDa) {
    this.convalidatoDa = convalidatoDa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dottore dottore = (Dottore) o;
    return sesso == dottore.sesso && stato == dottore.stato && Objects.equals(codiceFiscale,
        dottore.codiceFiscale) && Objects.equals(nome, dottore.nome)
        && Objects.equals(cognome, dottore.cognome) && Objects.equals(dataNascita,
        dottore.dataNascita) && Objects.equals(indirizzo, dottore.indirizzo)
        && Objects.equals(telefono, dottore.telefono) && Objects.equals(email,
        dottore.email) && Objects.equals(password, dottore.password)
        && Objects.equals(totpKey, dottore.totpKey) && Objects.equals(
        specializzazione, dottore.specializzazione) && Objects.equals(codiceAlbo,
        dottore.codiceAlbo) && Objects.equals(nomeStruttura, dottore.nomeStruttura)
        && Objects.equals(indirizzoStruttura, dottore.indirizzoStruttura)
        && Objects.equals(feedbacks, dottore.feedbacks) && Objects.equals(
        pazientes, dottore.pazientes) && Objects.equals(terapias, dottore.terapias)
        && Objects.equals(notificas, dottore.notificas) && Objects.equals(
        convalidatoDa, dottore.convalidatoDa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, nome, cognome, dataNascita, indirizzo, telefono, email,
        password, sesso, totpKey, specializzazione, codiceAlbo, nomeStruttura, indirizzoStruttura,
        stato, feedbacks, pazientes, terapias, notificas, convalidatoDa);
  }

  @Override
  public TipoUtente getTipoUtente() {
    return TipoUtente.DOTTORE;
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
    return stato == 1;
  }
}
