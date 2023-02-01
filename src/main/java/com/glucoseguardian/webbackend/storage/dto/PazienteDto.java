package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.NumeroTelefono;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Rappresenta l'output delle funzioni di paziente.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PazienteDto extends RisultatoDto implements Serializable {

  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String dataNascita;
  private String indirizzo;
  private String telefono;
  private String email;
  private String sesso;
  private String tipoDiabete;
  private String comorbilita;
  private String farmaciAssunti;
  private Integer periodoMonitoraggio;

  private List<NumeroTelefonoDto> numeriUtili;

  private TerapiaDto terapia;


  public PazienteDto() {
  }

  /**
   * Costruttore completo.
   */
  public PazienteDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email, String sesso, String tipoDiabete,
      String comorbilita, String farmaciAssunti, Integer periodoMonitoraggio,
      List<NumeroTelefonoDto> numeriUtili, TerapiaDto terapia) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.sesso = sesso;
    this.tipoDiabete = tipoDiabete;
    this.comorbilita = comorbilita;
    this.farmaciAssunti = farmaciAssunti;
    this.periodoMonitoraggio = periodoMonitoraggio;
    this.numeriUtili = numeriUtili;
    this.terapia = terapia;
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

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
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

  public Integer getPeriodoMonitoraggio() {
    return periodoMonitoraggio;
  }

  public void setPeriodoMonitoraggio(Integer periodoMonitoraggio) {
    this.periodoMonitoraggio = periodoMonitoraggio;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<NumeroTelefonoDto> getNumeriUtili() {
    return numeriUtili;
  }

  public void setNumeriUtili(List<NumeroTelefonoDto> numeriUtili) {
    this.numeriUtili = numeriUtili;
  }

  public void setNumeriUtiliFromEntity(List<NumeroTelefono> numeriUtili) {
    this.numeriUtili = numeriUtili.stream().map(NumeroTelefonoDto::valueOf).toList();
  }

  /**
   * Costruisce un PazienteDto a partire da un {@link Paziente}, il campo password non viene
   * popolato.
   */
  public static PazienteDto valueOf(Paziente paziente) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String dataNascitaPazienteDto = dateFormat.format(paziente.getDataNascita());
    PazienteDto pazienteDto = new PazienteDto();

    pazienteDto.setCodiceFiscale(paziente.getCodiceFiscale());
    pazienteDto.setNome(paziente.getNome());
    pazienteDto.setCognome(paziente.getCognome());
    pazienteDto.setDataNascita(dataNascitaPazienteDto);
    pazienteDto.setIndirizzo(paziente.getIndirizzo());
    pazienteDto.setTelefono(paziente.getTelefono());
    pazienteDto.setEmail(paziente.getEmail());
    pazienteDto.setSesso(paziente.getSesso() + "");
    pazienteDto.setTipoDiabete(paziente.getTipoDiabete());
    pazienteDto.setComorbilita(paziente.getComorbilita());
    pazienteDto.setFarmaciAssunti(paziente.getFarmaciAssunti());
    pazienteDto.setPeriodoMonitoraggio(pazienteDto.getPeriodoMonitoraggio());
    pazienteDto.setNumeriUtiliFromEntity(paziente.getNumeriUtili());
    return pazienteDto;
  }

  public TerapiaDto getTerapia() {
    return terapia;
  }

  public void setTerapia(TerapiaDto terapia) {
    this.terapia = terapia;
  }
}
