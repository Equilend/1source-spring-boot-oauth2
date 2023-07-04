package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Instrument
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Instrument   {
  @JsonProperty("ticker")
  private String ticker = null;

  @JsonProperty("cusip")
  private String cusip = null;

  @JsonProperty("isin")
  private String isin = null;

  @JsonProperty("sedol")
  private String sedol = null;

  @JsonProperty("quick")
  private String quick = null;

  @JsonProperty("figi")
  private String figi = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("price")
  private Price price = null;

  public Instrument ticker(String ticker) {
    this.ticker = ticker;
    return this;
  }

  /**
   * Get ticker
   * @return ticker
   **/
  @Schema(description = "")
  
    public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Instrument cusip(String cusip) {
    this.cusip = cusip;
    return this;
  }

  /**
   * Get cusip
   * @return cusip
   **/
  @Schema(description = "")
  
    public String getCusip() {
    return cusip;
  }

  public void setCusip(String cusip) {
    this.cusip = cusip;
  }

  public Instrument isin(String isin) {
    this.isin = isin;
    return this;
  }

  /**
   * Get isin
   * @return isin
   **/
  @Schema(description = "")
  
    public String getIsin() {
    return isin;
  }

  public void setIsin(String isin) {
    this.isin = isin;
  }

  public Instrument sedol(String sedol) {
    this.sedol = sedol;
    return this;
  }

  /**
   * Get sedol
   * @return sedol
   **/
  @Schema(description = "")
  
    public String getSedol() {
    return sedol;
  }

  public void setSedol(String sedol) {
    this.sedol = sedol;
  }

  public Instrument quick(String quick) {
    this.quick = quick;
    return this;
  }

  /**
   * Get quick
   * @return quick
   **/
  @Schema(description = "")
  
    public String getQuick() {
    return quick;
  }

  public void setQuick(String quick) {
    this.quick = quick;
  }

  public Instrument figi(String figi) {
    this.figi = figi;
    return this;
  }

  /**
   * Get figi
   * @return figi
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getFigi() {
    return figi;
  }

  public void setFigi(String figi) {
    this.figi = figi;
  }

  public Instrument description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(description = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instrument price(Price price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
   **/
  @Schema(description = "")
  
    @Valid
    public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Instrument instrument = (Instrument) o;
    return Objects.equals(this.ticker, instrument.ticker) &&
        Objects.equals(this.cusip, instrument.cusip) &&
        Objects.equals(this.isin, instrument.isin) &&
        Objects.equals(this.sedol, instrument.sedol) &&
        Objects.equals(this.quick, instrument.quick) &&
        Objects.equals(this.figi, instrument.figi) &&
        Objects.equals(this.description, instrument.description) &&
        Objects.equals(this.price, instrument.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticker, cusip, isin, sedol, quick, figi, description, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Instrument {\n");
    
    sb.append("    ticker: ").append(toIndentedString(ticker)).append("\n");
    sb.append("    cusip: ").append(toIndentedString(cusip)).append("\n");
    sb.append("    isin: ").append(toIndentedString(isin)).append("\n");
    sb.append("    sedol: ").append(toIndentedString(sedol)).append("\n");
    sb.append("    quick: ").append(toIndentedString(quick)).append("\n");
    sb.append("    figi: ").append(toIndentedString(figi)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
