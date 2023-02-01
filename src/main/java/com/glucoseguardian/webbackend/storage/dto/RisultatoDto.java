package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta un generico DTO, contiene solo un attributo "msg".
 */
@JsonInclude(Include.NON_ABSENT)
public class RisultatoDto implements Serializable {

  private String msg;

  public RisultatoDto(String msg) {
    this.msg = msg;
  }

  public RisultatoDto() {
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
