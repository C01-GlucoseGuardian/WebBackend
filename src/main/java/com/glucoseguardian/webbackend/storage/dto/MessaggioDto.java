package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta un messaggio di errore.
 */
@JsonInclude(Include.NON_ABSENT)
public class MessaggioDto implements Serializable {

  private String msg;

  public MessaggioDto(String msg) {
    this.msg = msg;
  }

  public MessaggioDto() {
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
