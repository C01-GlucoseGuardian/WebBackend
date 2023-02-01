package com.glucoseguardian.webbackend.storage.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This class rappresents a list DTO. Its only attribute is a List of T elements.
 **/
public class ListDto<T> extends RisultatoDto implements Serializable {

  private List<T> list;

  public ListDto() {
  }

  public ListDto(List<T> list) {
    this.list = list;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
