package com.glucoseguardian.webbackend.storage.entity;

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
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

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
  @JoinTable(name = "pazienteTutore", joinColumns = @JoinColumn(name = "paziente"),
      inverseJoinColumns = @JoinColumn(name = "tutore"))
  List<Tutore> profiliTutore;

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
  private String sesso;
  private String totpKey;
  @Column(length = 10, nullable = false)
  private String tipoDiabete;
  @Column(length = 100, nullable = false)
  private String comorbilita;
  @Column(length = 100, nullable = false)
  private String farmaciAssunti;
  @Column(nullable = false)
  private int periodoDiMonitoraggio;

  /**
   * costruttore Paziente.
   */
  public Paziente(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, String sesso,
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

  public List<Tutore> getProfiliTutore() {
    return profiliTutore;
  }

  public void setProfiliTutore(
      List<Tutore> profiliTutore) {
    this.profiliTutore = profiliTutore;
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

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
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
    return periodoDiMonitoraggio == paziente.periodoDiMonitoraggio && Objects.equals(numeriUtili,
        paziente.numeriUtili) && Objects.equals(notificheInvio,
        paziente.notificheInvio) && Objects.equals(notifiche,
        paziente.notifiche) && Objects.equals(glicemie, paziente.glicemie)
        && Objects.equals(feedbacks, paziente.feedbacks) && Objects.equals(
        terapia, paziente.terapia) && Objects.equals(dottore, paziente.dottore)
        && Objects.equals(profiliTutore, paziente.profiliTutore)
        && Objects.equals(codiceFiscale, paziente.codiceFiscale)
        && Objects.equals(nome, paziente.nome) && Objects.equals(cognome,
        paziente.cognome) && Objects.equals(dataNascita, paziente.dataNascita)
        && Objects.equals(indirizzo, paziente.indirizzo) && Objects.equals(
        telefono, paziente.telefono) && Objects.equals(email, paziente.email)
        && Objects.equals(password, paziente.password) && Objects.equals(sesso,
        paziente.sesso) && Objects.equals(totpKey, paziente.totpKey)
        && Objects.equals(tipoDiabete, paziente.tipoDiabete) && Objects.equals(
        comorbilita, paziente.comorbilita) && Objects.equals(farmaciAssunti,
        paziente.farmaciAssunti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeriUtili, notificheInvio, notifiche, glicemie, feedbacks,
        terapia, dottore, profiliTutore, codiceFiscale, nome, cognome, dataNascita, indirizzo,
        telefono, email, password, sesso, totpKey, tipoDiabete, comorbilita, farmaciAssunti,
        periodoDiMonitoraggio);
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


}
