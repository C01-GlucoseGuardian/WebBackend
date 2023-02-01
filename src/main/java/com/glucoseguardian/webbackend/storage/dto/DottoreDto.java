package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Rappresenta l'entity dottore.
 */
@JsonInclude(Include.NON_ABSENT)
public class DottoreDto extends RisultatoDto implements Serializable {

  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String dataNascita;
  private String indirizzo;
  private String telefono;
  private String email;
  private String password;
  private String sesso;
  private String specializzazione;
  private String codiceAlbo;
  private String nomeStruttura;
  private String indirizzoStruttura;
  private Integer stato = 0;

  /**
   * Costruttore completo.
   */
  public DottoreDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email, String password, String sesso,
      String specializzazione, String codiceAlbo, String nomeStruttura, String indirizzoStruttura,
      Integer stato) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
    this.specializzazione = specializzazione;
    this.codiceAlbo = codiceAlbo;
    this.nomeStruttura = nomeStruttura;
    this.indirizzoStruttura = indirizzoStruttura;
    this.stato = stato;
  }

  /**
   * Costruttore di Dottore DTO senza password.
   */
  public DottoreDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email,  String sesso,
      String specializzazione, String codiceAlbo, String nomeStruttura, String indirizzoStruttura,
      Integer stato) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.sesso = sesso;
    this.specializzazione = specializzazione;
    this.codiceAlbo = codiceAlbo;
    this.nomeStruttura = nomeStruttura;
    this.indirizzoStruttura = indirizzoStruttura;
    this.stato = stato;
  }


  public DottoreDto() {
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

  public String getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(String dataNascita) {
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

  public Integer getStato() {
    return stato;
  }

  public void setStato(Integer stato) {
    this.stato = stato;
  }

  /**
   *  Costruisce un DottoreDto a partire da un {@link Dottore}.
   *  Il campo password non viene popolato.
   */
  public static DottoreDto valueOf(Dottore dottore) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String dataNascitaDottoreDto = dateFormat.format(dottore.getDataNascita());
    DottoreDto dottoreDto = new DottoreDto();

    dottoreDto.setCodiceFiscale(dottore.getCodiceFiscale());
    dottoreDto.setNome(dottore.getNome());
    dottoreDto.setCognome(dottore.getCognome());
    dottoreDto.setDataNascita(dataNascitaDottoreDto);
    dottoreDto.setIndirizzo(dottore.getIndirizzo());
    dottoreDto.setTelefono(dottore.getTelefono());
    dottoreDto.setEmail(dottore.getEmail());
    dottoreDto.setSesso(dottore.getSesso() + "");
    dottoreDto.setSpecializzazione(dottore.getSpecializzazione());
    dottoreDto.setCodiceAlbo(dottore.getCodiceAlbo());
    dottoreDto.setNomeStruttura(dottore.getNomeStruttura());
    dottoreDto.setIndirizzoStruttura(dottore.getIndirizzoStruttura());
    dottoreDto.setStato(dottore.getStato());
    return dottoreDto;
  }
}