package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CollateralType
 */
public enum CollateralType {
  CASH("CASH"),
    NONCASH("NONCASH"),
    CASHPOOL("CASHPOOL"),
    TRIPARTY("TRIPARTY");

  private String value;

  CollateralType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CollateralType fromValue(String text) {
    for (CollateralType b : CollateralType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
