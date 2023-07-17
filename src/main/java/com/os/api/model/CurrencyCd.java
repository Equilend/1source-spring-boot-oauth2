package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CurrencyCd
 */
public enum CurrencyCd {
  USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    AUD("AUD"),
    HKD("HKD"),
    CAD("CAD"),
    CHF("CHF"),
    SEK("SEK"),
    SGD("SGD"),
    NOK("NOK"),
    DKK("DKK");

  private String value;

  CurrencyCd(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CurrencyCd fromValue(String text) {
    for (CurrencyCd b : CurrencyCd.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
