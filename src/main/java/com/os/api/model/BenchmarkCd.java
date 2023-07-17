package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets BenchmarkCd
 */
public enum BenchmarkCd {
  EFFR("EFFR"),
    OBFR("OBFR"),
    TGCR("TGCR"),
    BGCR("BGCR"),
    SOFR("SOFR");

  private String value;

  BenchmarkCd(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BenchmarkCd fromValue(String text) {
    for (BenchmarkCd b : BenchmarkCd.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
