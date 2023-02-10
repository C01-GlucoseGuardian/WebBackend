package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.NumeroTelefono;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;

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
  private Integer periodoDiMonitoraggio;

  private String idDottore;

  private List<NumeroTelefonoDto> numeriUtili;

  private TerapiaDto terapia;


  public PazienteDto() {
  }

  /**
   * Costruttore completo.
   */
  public PazienteDto(String codiceFiscale, String nome, String cognome, String dataNascita,
      String indirizzo, String telefono, String email, String sesso, String tipoDiabete,
      String comorbilita, String farmaciAssunti, Integer periodoDiMonitoraggio, String idDottore,
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
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
    this.idDottore = idDottore;
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

  public Integer getPeriodoDiMonitoraggio() {
    return periodoDiMonitoraggio;
  }

  public void setPeriodoDiMonitoraggio(Integer periodoDiMonitoraggio) {
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
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

  public void setIdDottore(String idDottore) {
    this.idDottore = idDottore;
  }

  public String getIdDottore() {
    return idDottore;
  }

  /**
   * Costruisce un PazienteDto a partire da un {@link Paziente}, il campo password non viene
   * popolato.
   */
  public static PazienteDto valueOf(Paziente paziente) {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
    pazienteDto.setPeriodoDiMonitoraggio(pazienteDto.getPeriodoDiMonitoraggio());
    pazienteDto.setNumeriUtiliFromEntity(paziente.getNumeriUtili());
    pazienteDto.setIdDottore(paziente.getDottore().getCodiceFiscale());
    return pazienteDto;
  }

  /**
   *  validazione del paziente.
   */

  public void validate() throws IllegalArgumentException {
    Validate.notNull(codiceFiscale, "Il codice fiscale non può essere vuoto");
    Validate.isTrue(codiceFiscale.length() == 16,
        "La lunghezza del codice fiscale deve essere di 16 caratteri");

    Validate.notNull(nome, "Il nome non puo essere vuoto");
    Validate.isTrue(nome.length() <= 30 && nome.length() > 3,
        "La lunghezza del campo nome non è valida");

    Validate.notNull(cognome, "Il cognome non puo essere vuoto");
    Validate.isTrue(cognome.length() <= 30 && cognome.length() > 2,
        "La lunghezza del cognome non è valida");

    Validate.notNull(sesso, "il sesso non puo' essere vuoto");
    Pattern pattern = Pattern.compile("^M|F$");
    Validate.isTrue(pattern.matcher(sesso).matches(), "il sesso non è valido");

    Validate.notNull(dataNascita, "la data di nascita non puo essere vuota");
    Pattern pattern1 = Pattern.compile(
        "^(0[1-9]|[1-2]\\d|3[01])\\/(0[1-9]|1[0-2])\\/\\d\\d\\d\\d$");
    Validate.isTrue(pattern1.matcher(dataNascita).matches(),
        "la data nascita inserita non è valida");

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    try {
      long diff = dateFormat.parse(dataNascita).getTime() - new java.util.Date().getTime();
      Validate.isTrue(diff < 0, "La data di nascita è nel futuro");
    } catch (ParseException ex) {
      throw new IllegalArgumentException("la data nascita inserita non è valida");
    }

    Validate.notNull(email, "la mail non puo essere assente");
    Pattern pattern2 = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+@(?:[a-zA-Z0-9-\\.]+)\\w$");
    Validate.isTrue(pattern2.matcher(email).matches(), "L'email non è valida");

    Validate.notNull(telefono, "telefono non puo essere assente");
    Pattern pattern3 = Pattern.compile("^\\+?\\d{5,15}$");
    Validate.isTrue(pattern3.matcher(telefono).matches(),
        "il campo numero di telefono non rispetta il formato");

    Validate.notNull(indirizzo, "l'indirizzo non puo' essere vuoto");
    Validate.isTrue(indirizzo.length() <= 50 && indirizzo.length() >= 4,
        "La lunghezza dell'indirizzo non è valida");

    Validate.notNull(numeriUtili, "indirizzo non puo essere assente");
    for (NumeroTelefonoDto numeroTelefonoDto : numeriUtili) {
      Validate.isTrue((pattern3.matcher(numeroTelefonoDto.getNumero())).matches());
    }

    Validate.notNull(tipoDiabete, "tipo diabete non puo' essere assente");
    Validate.isTrue(tipoDiabete.length() <= 10 && tipoDiabete.length() >= 1,
        "La lunghezza del tipo diabete non è valida");

    Validate.notNull(comorbilita, "commorbilità non puo' essere assente");
    Validate.isTrue(comorbilita.length() <= 100, "La lunghezza della commorbilità non è valida");

    Validate.notNull(farmaciAssunti, "farmaci assunti non puo' essere vuoto");
    Validate.isTrue(farmaciAssunti.length() <= 100, "la lunghezza di farmaci assunti non è valida");

    Validate.notNull(periodoDiMonitoraggio, "periodo di monitoraggio non puo essere assente");
  }

  public TerapiaDto getTerapia() {
    return terapia;
  }

  public void setTerapia(TerapiaDto terapia) {
    this.terapia = terapia;
  }
}
