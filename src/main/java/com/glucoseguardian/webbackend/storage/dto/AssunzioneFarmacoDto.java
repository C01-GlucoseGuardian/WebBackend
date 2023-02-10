package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'entita assunzione farmaco.
 */
@JsonInclude(Include.NON_ABSENT)
public class AssunzioneFarmacoDto extends RisultatoDto implements Serializable {

  private Long id;
  private Long idFarmaco;
  private String nomeFarmaco;
  private String dosaggio;
  private String orarioAssunzione;
  private String viaDiSomministrazione;
  private String noteAggiuntive;

  /**
   * Costruttore completo.
   */
  public AssunzioneFarmacoDto(Long id, Long idFarmaco, String dosaggio, String orarioAssunzione,
      String viaDiSomministrazione, String noteAggiuntive) {
    this.id = id;
    this.idFarmaco = idFarmaco;
    this.dosaggio = dosaggio;
    this.orarioAssunzione = orarioAssunzione;
    this.viaDiSomministrazione = viaDiSomministrazione;
    this.noteAggiuntive = noteAggiuntive;
  }

  public AssunzioneFarmacoDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdFarmaco() {
    return idFarmaco;
  }

  public void setIdFarmaco(Long idFarmaco) {
    this.idFarmaco = idFarmaco;
  }

  public String getDosaggio() {
    return dosaggio;
  }

  public void setDosaggio(String dosaggio) {
    this.dosaggio = dosaggio;
  }

  public String getOrarioAssunzione() {
    return orarioAssunzione;
  }

  public void setOrarioAssunzione(String orarioAssunzione) {
    this.orarioAssunzione = orarioAssunzione;
  }

  public String getViaDiSomministrazione() {
    return viaDiSomministrazione;
  }

  public void setViaDiSomministrazione(String viaDiSomministrazione) {
    this.viaDiSomministrazione = viaDiSomministrazione;
  }

  public String getNoteAggiuntive() {
    return noteAggiuntive;
  }

  public void setNoteAggiuntive(String noteAggiuntive) {
    this.noteAggiuntive = noteAggiuntive;
  }

  public String getNomeFarmaco() {
    return nomeFarmaco;
  }

  public void setNomeFarmaco(String nomeFarmaco) {
    this.nomeFarmaco = nomeFarmaco;
  }

  /**
   * Costruisce un {@link AssunzioneFarmacoDto} a partire da un {@link AssunzioneFarmaco}.
   */
  public static AssunzioneFarmacoDto valueOf(AssunzioneFarmaco assunzioneFarmaco) {
    String timeString = assunzioneFarmaco.getOrarioAssunzione().toString();

    AssunzioneFarmacoDto assunzioneFarmacoDto = new AssunzioneFarmacoDto();
    assunzioneFarmacoDto.setNomeFarmaco(assunzioneFarmaco.getFarmaco().getNomeFarmaco());
    assunzioneFarmacoDto.setIdFarmaco(assunzioneFarmaco.getFarmaco().getId());
    assunzioneFarmacoDto.setId(assunzioneFarmaco.getId());
    assunzioneFarmacoDto.setDosaggio(assunzioneFarmaco.getDosaggio());
    assunzioneFarmacoDto.setOrarioAssunzione(timeString);
    assunzioneFarmacoDto.setViaDiSomministrazione(assunzioneFarmaco.getViaDiSomministrazione());
    assunzioneFarmacoDto.setNoteAggiuntive(assunzioneFarmacoDto.getNoteAggiuntive());
    return assunzioneFarmacoDto;
  }

  /**
   * Validation of AssunzioneFarmaco.
   */

  public void validate() {
    Validate.notNull(nomeFarmaco, "il nome del farmaco non può essere vuoto");
    Validate.isTrue(nomeFarmaco.length() <= 50 && nomeFarmaco.length() >= 1,
        "il nome del farmaco è della lunghezza errata");
    Validate.notNull(dosaggio, "il dosaggio non può essere vuoto");
    Validate.isTrue(dosaggio.length() <= 1000 && dosaggio.length() >= 1, "il dosaggio non può essere vuoto");
    Validate.notNull(orarioAssunzione, "l'orario di assunzione non può essere vuoto");
    Pattern pattern = Pattern.compile("([0-1][0-9]|2[0-3]):[0-5][0-9]");
    Validate.isTrue(pattern.matcher(orarioAssunzione).matches(),
        "l'orario di assunzione non è valido");
    Validate.notNull(viaDiSomministrazione, "la via di somministrazione non può essere assente");
    Validate.isTrue(viaDiSomministrazione.length() <= 300 && viaDiSomministrazione.length() >= 1,
        "la lunghezza della via di somministrazione è della lunghezza errata");
    if(noteAggiuntive != null) {
      Validate.isTrue(noteAggiuntive.length() <= 300,
          "le note aggiuntive superano la lunghezza consentita");
    }
  }
}
