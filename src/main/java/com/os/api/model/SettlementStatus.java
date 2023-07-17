package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets SettlementStatus
 */
public enum SettlementStatus {
  NONE("NONE"),
    PENDING("PENDING"),
    MADE("MADE"),
    DROPPED("DROPPED"),
    KILLED("KILLED");

  private String value;

  SettlementStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SettlementStatus fromValue(String text) {
    for (SettlementStatus b : SettlementStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
