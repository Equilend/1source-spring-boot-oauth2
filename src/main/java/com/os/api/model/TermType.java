package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets TermType
 */
public enum TermType {
  OPEN("OPEN"),
    TERM("TERM");

  private String value;

  TermType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TermType fromValue(String text) {
    for (TermType b : TermType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
