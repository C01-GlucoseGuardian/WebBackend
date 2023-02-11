package com.glucoseguardian.webbackend.storage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * classe entity Paziente.
 */
@Entity
public class Paziente implements Serializable, Utente {

  @OneToMany(mappedBy = "paziente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<NumeroTelefono> numeriUtili;
  @OneToMany(mappedBy = "pazienteOggetto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Notifica> notificheInvio;
  @OneToMany(mappedBy = "pazienteDestinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Notifica> notifiche;
  @OneToMany(mappedBy = "paziente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Glicemia> glicemie;
  @OneToMany(mappedBy = "paziente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Feedback> feedbacks;
  @OneToOne(mappedBy = "paziente", cascade = CascadeType.ALL)
  Terapia terapia;
  @ManyToOne
  @JoinColumn(name = "dottore")
  Dottore dottore;
  @ManyToMany
  @JoinTable(
      name = "pazienteTutore",
      joinColumns = @JoinColumn(name = "paziente"),
      inverseJoinColumns = @JoinColumn(name = "tutore"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"paziente", "tutore"})
      }
  )
  List<Tutore> tutori;

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
  @NotNull
  private char sesso;
  @Nullable
  private String totpKey;
  @Column(length = 10)
  @NotNull
  private String tipoDiabete;
  @Column(length = 100)
  @NotNull
  private String comorbilita;
  @Column(length = 100)
  @NotNull
  private String farmaciAssunti;
  @NotNull
  private int periodoDiMonitoraggio;

  /**
   * costruttore Paziente.
   */
  public Paziente(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, char sesso,
      String totpKey, String tipoDiabete, String comorbilita, String farmaciAssunti,
      int periodoDiMonitoraggio) {
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
    this.tipoDiabete = tipoDiabete;
    this.comorbilita = comorbilita;
    this.farmaciAssunti = farmaciAssunti;
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
  }

  public Paziente() {

  }

  public List<NumeroTelefono> getNumeriUtili() {
    return numeriUtili;
  }

  public void setNumeriUtili(
      List<NumeroTelefono> numeriUtili) {
    this.numeriUtili = numeriUtili;
  }

  public List<Notifica> getNotificheInvio() {
    return notificheInvio;
  }

  public void setNotificheInvio(
      List<Notifica> notificheInvio) {
    this.notificheInvio = notificheInvio;
  }

  public List<Notifica> getNotifiche() {
    return notifiche;
  }

  public void setNotifiche(
      List<Notifica> notifiche) {
    this.notifiche = notifiche;
  }

  public List<Glicemia> getGlicemie() {
    return glicemie;
  }

  public void setGlicemie(List<Glicemia> glicemie) {
    this.glicemie = glicemie;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(
      List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public Terapia getTerapia() {
    return terapia;
  }

  public void setTerapia(Terapia terapia) {
    this.terapia = terapia;
  }

  public Dottore getDottore() {
    return dottore;
  }

  public void setDottore(Dottore dottore) {
    this.dottore = dottore;
  }

  public List<Tutore> getTutori() {
    return tutori;
  }

  public void setTutori(
      List<Tutore> tutori) {
    this.tutori = tutori;
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

  public String getTipoDiabete() {
    return tipoDiabete;
  }

  public void setTipoDiabete(String tipoDiabete) {
    this.tipoDiabete = tipoDiabete;
  }

  public String getComorbilita() {
    return comorbilita;
  }

  public void setComorbilita(String comorbilita) {
    this.comorbilita = comorbilita;
  }

  public String getFarmaciAssunti() {
    return farmaciAssunti;
  }

  public void setFarmaciAssunti(String farmaciAssunti) {
    this.farmaciAssunti = farmaciAssunti;
  }

  public int getPeriodoDiMonitoraggio() {
    return periodoDiMonitoraggio;
  }

  public void setPeriodoDiMonitoraggio(int periodoDiMonitoraggio) {
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Paziente paziente = (Paziente) o;
    return Objects.equals(codiceFiscale, paziente.codiceFiscale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale);
  }

  @Override
  public String toString() {
    return "Paziente{" + "codice_fiscale='" + codiceFiscale + '\'' + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\'' + ", data_nascita=" + dataNascita + ", indirizzo='"
        + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
        + ", password='" + password + '\'' + ", sesso='" + sesso + '\'' + ", totp_key='" + totpKey
        + '\'' + ", tipo_diabete='" + tipoDiabete + '\'' + ", comorbilita='" + comorbilita + '\''
        + ", farmaci_assunti='" + farmaciAssunti + '\'' + ", periodo_di_monitoraggio="
        + periodoDiMonitoraggio + '}';
  }

  public TipoUtente getTipoUtente() {
    return TipoUtente.PAZIENTE;
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
