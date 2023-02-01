package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Rappresenta l'entity admin.
 */
@JsonInclude(Include.NON_ABSENT)
public class AdminDto extends RisultatoDto implements Serializable {

  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String dataNascita;
  private String indirizzo;
  private String telefono;
  private String email;
  private String password;
  private String sesso;

  /**
   * Costruttore completo.
   */
  public AdminDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email, String password, String sesso) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
  }

  public AdminDto() {

  }
  /**
   *  Costruisce un AdminDto a partire da un {@link Admin}.
   *  Il campo password non viene popolato.
   */

  public static AdminDto valueOf(Admin admin) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String dataNascitaDottoreDto = dateFormat.format(admin.getDataNascita());
    AdminDto adminDto = new AdminDto();

    adminDto.setCodiceFiscale(admin.getCodiceFiscale());
    adminDto.setNome(admin.getNome());
    adminDto.setCognome(admin.getCognome());
    adminDto.setDataNascita(dataNascitaDottoreDto);
    adminDto.setIndirizzo(admin.getIndirizzo());
    adminDto.setTelefono(admin.getTelefono());
    adminDto.setEmail(admin.getEmail());
    adminDto.setSesso(admin.getSesso() + "");
    return adminDto;
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
}
