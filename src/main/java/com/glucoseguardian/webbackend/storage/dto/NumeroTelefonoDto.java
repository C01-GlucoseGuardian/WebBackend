package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.NumeroTelefono;
import java.io.Serializable;

/**
 * Rappresenta l'output delle funzioni di numeroTelefono.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class NumeroTelefonoDto extends RisultatoDto implements Serializable {

  private Long id;
  private String numero;

  public NumeroTelefonoDto() {
  }

  /**
   * Costruttore completo.
   */
  public NumeroTelefonoDto(Long id, String numero) {
    this.id = id;
    this.numero = numero;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public static NumeroTelefonoDto valueOf(NumeroTelefono numeroTelefono) {
    return new NumeroTelefonoDto(numeroTelefono.getId(), numeroTelefono.getNumero());
  }
}