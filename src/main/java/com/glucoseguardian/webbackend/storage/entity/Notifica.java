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
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Questa classe rappresenta l'entity notifica.
 */
@Entity
public class Notifica implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 1024)
  @NotNull
  private String messaggio;

  @NotNull
  private Date data;

  @NotNull
  private Time ora;

  private int stato = 0;
  @ManyToOne
  @JoinColumn(name = "pazienteOggetto")
  private Paziente pazienteOggetto;

  @ManyToOne
  @JoinColumn(name = "pazienteDestinatario")
  private Paziente pazienteDestinatario;

  @ManyToOne
  @JoinColumn(name = "dottoreDestinatario")
  private Dottore dottoreDestinatario;

  @ManyToOne
  @JoinColumn(name = "tutoreDestinatario")
  private Tutore tutoreDestinatario;

  @ManyToOne
  @JoinColumn(name = "adminDestinatario")
  private Admin adminDestinatario;

  public Notifica() {

  }

  /**
   * Costruttore predefinito dell'entity Notifica.
   */
  public Notifica(String messaggio, Date data, Time ora, int stato) {
    this.messaggio = messaggio;
    this.data = data;
    this.ora = ora;
    this.stato = stato;
  }
  /**
   * Questo è il costruttore della entity Notifica.
   */

  public Notifica(long id, String messaggio, Date data, Time ora, int stato,
      Paziente pazienteOggetto, Paziente pazienteDestinatario, Dottore dottoreDestinatario,
      Tutore tutoreDestinatario, Admin adminDestinatario) {
    this.id = id;
    this.messaggio = messaggio;
    this.data = data;
    this.ora = ora;
    this.stato = stato;
    this.pazienteOggetto = pazienteOggetto;
    this.pazienteDestinatario = pazienteDestinatario;
    this.dottoreDestinatario = dottoreDestinatario;
    this.tutoreDestinatario = tutoreDestinatario;
    this.adminDestinatario = adminDestinatario;
  }

  @Override
  public String toString() {
    return "Notifica{"
        + "id=" + id
        + ", messaggio='" + messaggio + '\''
        + ", data=" + data
        + ", ora=" + ora + ", stato=" + stato
        + '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Time getOra() {
    return ora;
  }

  public void setOra(Time ora) {
    this.ora = ora;
  }

  public int getStato() {
    return stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  public Paziente getPazienteOggetto() {
    return pazienteOggetto;
  }

  public void setPazienteOggetto(Paziente pazienteOggetto) {
    this.pazienteOggetto = pazienteOggetto;
  }

  public Paziente getPazienteDestinatario() {
    return pazienteDestinatario;
  }

  public void setPazienteDestinatario(
      Paziente pazienteDestinatario) {
    this.pazienteDestinatario = pazienteDestinatario;
  }

  public Dottore getDottoreDestinatario() {
    return dottoreDestinatario;
  }

  public void setDottoreDestinatario(
      Dottore dottoreDestinatario) {
    this.dottoreDestinatario = dottoreDestinatario;
  }

  public Tutore getTutoreDestinatario() {
    return tutoreDestinatario;
  }

  public void setTutoreDestinatario(
      Tutore tutoreDestinatario) {
    this.tutoreDestinatario = tutoreDestinatario;
  }

  public Admin getAdminDestinatario() {
    return adminDestinatario;
  }

  public void setAdminDestinatario(Admin adminDestinatario) {
    this.adminDestinatario = adminDestinatario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notifica notifica = (Notifica) o;
    return id == notifica.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }


}
