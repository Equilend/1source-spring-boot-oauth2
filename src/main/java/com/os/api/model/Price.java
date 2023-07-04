package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Price
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Price   {
  @JsonProperty("value")
  private Float value = null;

  @JsonProperty("currency")
  private CurrencyCd currency = null;

  @JsonProperty("unit")
  private PriceUnit unit = null;

  public Price value(Float value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   **/
  @Schema(description = "")
  
    public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public Price currency(CurrencyCd currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
   **/
  @Schema(description = "")
  
    @Valid
    public CurrencyCd getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyCd currency) {
    this.currency = currency;
  }

  public Price unit(PriceUnit unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
   **/
  @Schema(description = "")
  
    @Valid
    public PriceUnit getUnit() {
    return unit;
  }

  public void setUnit(PriceUnit unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(this.value, price.value) &&
        Objects.equals(this.currency, price.currency) &&
        Objects.equals(this.unit, price.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, currency, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Price {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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
