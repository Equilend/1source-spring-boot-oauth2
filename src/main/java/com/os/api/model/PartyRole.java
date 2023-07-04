package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets PartyRole
 */
public enum PartyRole {
  BORROWER("BORROWER"),
    LENDER("LENDER"),
    TRIPARTY("TRIPARTY"),
    CCP("CCP");

  private String value;

  PartyRole(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PartyRole fromValue(String text) {
    for (PartyRole b : PartyRole.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
