package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta l'output dell'entità tutore.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TutoreDto extends RisultatoDto implements Serializable {

  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String dataNascita;
  private String indirizzo;
  private String telefono;
  private String email;
  private String sesso;
  private String relazioneDiParentela;
  private List<PazienteDto> pazienteList;

  public TutoreDto() {
  }

  /**
   * Construttore predefinito di TutoreDto.
   */
  public TutoreDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email, String sesso, String relazioneDiParentela,
      List<PazienteDto> pazienteList) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.sesso = sesso;
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

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  public String getRelazioneDiParentela() {
    return relazioneDiParentela;
  }

  public void setRelazioneDiParentela(String relazioneDiParentela) {
    this.relazioneDiParentela = relazioneDiParentela;
  }

  public List<PazienteDto> getPazienteList() {
    return pazienteList;
  }

  public void setPazienteList(List<PazienteDto> pazienteList) {
    this.pazienteList = pazienteList;
  }

  /**
   *  Costruisce un TutoreDto a partire da un {@link Tutore},
   *  il campo password non viene popolato.
   */
  public static TutoreDto valueOf(Tutore tutore) {
    List<PazienteDto> list = new ArrayList<>();
    for (Paziente paziente : tutore.getPazienteList()) {
      list.add(PazienteDto.valueOf(paziente));
    }
    TutoreDto tutoreDto = new TutoreDto();
    tutoreDto.setCodiceFiscale(tutore.getCodiceFiscale());
    tutoreDto.setNome(tutore.getNome());
    tutoreDto.setCognome(tutore.getCognome());
    tutoreDto.setIndirizzo(tutore.getIndirizzo());
    tutoreDto.setTelefono(tutore.getTelefono());
    tutoreDto.setEmail(tutore.getEmail());
    tutoreDto.setSesso(tutore.getSesso() + "");
    tutoreDto.setRelazioneDiParentela(tutore.getRelazioneDiParentela());
    tutoreDto.setPazienteList(list);

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String dataNascitaPazienteDto = dateFormat.format(tutore.getDataNascita());
    tutoreDto.setDataNascita(dataNascitaPazienteDto);

    return tutoreDto;
  }
}
