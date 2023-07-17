package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets VenueType
 */
public enum VenueType {
  ONPLATFORM("ONPLATFORM"),
    OFFPLATFORM("OFFPLATFORM");

  private String value;

  VenueType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static VenueType fromValue(String text) {
    for (VenueType b : VenueType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
