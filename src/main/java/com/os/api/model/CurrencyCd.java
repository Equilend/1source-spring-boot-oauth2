/*
 * 1Source Ledger API
 * 1Source Ledger API provides client access to the 1Source Ledger. You can find out more about 1Source at [https://equilend.com](https://equilend.com).  This specification is work in progress. The design is meant to model the securities lending life cycle in as clean a way as possible while being robust enough to easily translate to ISLA CDM workflows and data model.  API specification is the intellectual property of EquiLend LLC and should not be copied or disseminated in any way. 
 *
 * OpenAPI spec version: 1.0.4
 * Contact: 1source_help@equilend.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.os.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets CurrencyCd
 */
@JsonAdapter(CurrencyCd.Adapter.class)
public enum CurrencyCd {
  @SerializedName("USD")
  USD("USD"),
  @SerializedName("EUR")
  EUR("EUR"),
  @SerializedName("GBP")
  GBP("GBP"),
  @SerializedName("JPY")
  JPY("JPY"),
  @SerializedName("AUD")
  AUD("AUD"),
  @SerializedName("HKD")
  HKD("HKD"),
  @SerializedName("CAD")
  CAD("CAD"),
  @SerializedName("CHF")
  CHF("CHF"),
  @SerializedName("SEK")
  SEK("SEK"),
  @SerializedName("SGD")
  SGD("SGD"),
  @SerializedName("NOK")
  NOK("NOK"),
  @SerializedName("DKK")
  DKK("DKK");

  private String value;

  CurrencyCd(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static CurrencyCd fromValue(String input) {
    for (CurrencyCd b : CurrencyCd.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<CurrencyCd> {
    @Override
    public void write(final JsonWriter jsonWriter, final CurrencyCd enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public CurrencyCd read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return CurrencyCd.fromValue((String)(value));
    }
  }
}
