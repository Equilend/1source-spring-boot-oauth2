package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets AgreementStatus
 */
public enum AgreementStatus {
  PENDING("PENDING"),
    CONFIRMED("CONFIRMED");

  private String value;

  AgreementStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AgreementStatus fromValue(String text) {
    for (AgreementStatus b : AgreementStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
