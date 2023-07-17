package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CollateralDescription
 */
public enum CollateralDescription {
  NONUSAGENCIES("NONUSAGENCIES"),
    AGENCIES("AGENCIES"),
    CANADIANBONDS("CANADIANBONDS"),
    CANADIANPROVINCIALS("CANADIANPROVINCIALS"),
    CORPORATES("CORPORATES"),
    DEBT("DEBT"),
    EMUDEBTAAA("EMUDEBTAAA"),
    EMUDEBT("EMUDEBT"),
    AUSTRALIANEQUITIES("AUSTRALIANEQUITIES"),
    EQUITIES("EQUITIES"),
    GOVTDEBT105("GOVTDEBT105"),
    G10DEBT("G10DEBT"),
    G3DEBT("G3DEBT"),
    G8DEBT("G8DEBT"),
    UKGILTS("UKGILTS"),
    GOVERNMENTISSUES("GOVERNMENTISSUES"),
    GOVERNMENTISSUESAAA("GOVERNMENTISSUESAAA"),
    HIGHGRADEEQUITIES("HIGHGRADEEQUITIES"),
    INVESTMENTGRADECORPORATES("INVESTMENTGRADECORPORATES"),
    JAPANESEGOVERNMENTBONDS("JAPANESEGOVERNMENTBONDS");

  private String value;

  CollateralDescription(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CollateralDescription fromValue(String text) {
    for (CollateralDescription b : CollateralDescription.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
