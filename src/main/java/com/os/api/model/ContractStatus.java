package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ContractStatus
 */
public enum ContractStatus {
  PROPOSED("PROPOSED"),
    APPROVED("APPROVED"),
    CANCELED("CANCELED"),
    DECLINED("DECLINED");

  private String value;

  ContractStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ContractStatus fromValue(String text) {
    for (ContractStatus b : ContractStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
