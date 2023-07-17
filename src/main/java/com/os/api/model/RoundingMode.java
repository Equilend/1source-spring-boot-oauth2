package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets RoundingMode
 */
public enum RoundingMode {
  ALWAYSUP("ALWAYSUP"),
    ALWAYSDOWN("ALWAYSDOWN"),
    STANDARD("STANDARD"),
    EXACT("EXACT");

  private String value;

  RoundingMode(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static RoundingMode fromValue(String text) {
    for (RoundingMode b : RoundingMode.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
