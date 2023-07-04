package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Collateral
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Collateral   {
  @JsonProperty("contractPrice")
  private Double contractPrice = null;

  @JsonProperty("contractValue")
  private Double contractValue = null;

  @JsonProperty("collateralValue")
  private Double collateralValue = null;

  @JsonProperty("currency")
  private CurrencyCd currency = null;

  @JsonProperty("type")
  private CollateralType type = null;

  @JsonProperty("descriptionCd")
  private CollateralDescription descriptionCd = null;

  @JsonProperty("margin")
  private Integer margin = null;

  public Collateral contractPrice(Double contractPrice) {
    this.contractPrice = contractPrice;
    return this;
  }

  /**
   * Get contractPrice
   * @return contractPrice
   **/
  @Schema(description = "")
  
    public Double getContractPrice() {
    return contractPrice;
  }

  public void setContractPrice(Double contractPrice) {
    this.contractPrice = contractPrice;
  }

  public Collateral contractValue(Double contractValue) {
    this.contractValue = contractValue;
    return this;
  }

  /**
   * Get contractValue
   * @return contractValue
   **/
  @Schema(description = "")
  
    public Double getContractValue() {
    return contractValue;
  }

  public void setContractValue(Double contractValue) {
    this.contractValue = contractValue;
  }

  public Collateral collateralValue(Double collateralValue) {
    this.collateralValue = collateralValue;
    return this;
  }

  /**
   * Get collateralValue
   * @return collateralValue
   **/
  @Schema(description = "")
  
    public Double getCollateralValue() {
    return collateralValue;
  }

  public void setCollateralValue(Double collateralValue) {
    this.collateralValue = collateralValue;
  }

  public Collateral currency(CurrencyCd currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CurrencyCd getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyCd currency) {
    this.currency = currency;
  }

  public Collateral type(CollateralType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CollateralType getType() {
    return type;
  }

  public void setType(CollateralType type) {
    this.type = type;
  }

  public Collateral descriptionCd(CollateralDescription descriptionCd) {
    this.descriptionCd = descriptionCd;
    return this;
  }

  /**
   * Get descriptionCd
   * @return descriptionCd
   **/
  @Schema(description = "")
  
    @Valid
    public CollateralDescription getDescriptionCd() {
    return descriptionCd;
  }

  public void setDescriptionCd(CollateralDescription descriptionCd) {
    this.descriptionCd = descriptionCd;
  }

  public Collateral margin(Integer margin) {
    this.margin = margin;
    return this;
  }

  /**
   * Get margin
   * @return margin
   **/
  @Schema(description = "")
  
    public Integer getMargin() {
    return margin;
  }

  public void setMargin(Integer margin) {
    this.margin = margin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Collateral collateral = (Collateral) o;
    return Objects.equals(this.contractPrice, collateral.contractPrice) &&
        Objects.equals(this.contractValue, collateral.contractValue) &&
        Objects.equals(this.collateralValue, collateral.collateralValue) &&
        Objects.equals(this.currency, collateral.currency) &&
        Objects.equals(this.type, collateral.type) &&
        Objects.equals(this.descriptionCd, collateral.descriptionCd) &&
        Objects.equals(this.margin, collateral.margin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractPrice, contractValue, collateralValue, currency, type, descriptionCd, margin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Collateral {\n");
    
    sb.append("    contractPrice: ").append(toIndentedString(contractPrice)).append("\n");
    sb.append("    contractValue: ").append(toIndentedString(contractValue)).append("\n");
    sb.append("    collateralValue: ").append(toIndentedString(collateralValue)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    descriptionCd: ").append(toIndentedString(descriptionCd)).append("\n");
    sb.append("    margin: ").append(toIndentedString(margin)).append("\n");
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
