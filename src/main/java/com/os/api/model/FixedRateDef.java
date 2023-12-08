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

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
/**
 * FixedRateDef
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-12-07T14:42:49.871457665Z[GMT]")

public class FixedRateDef {
  @SerializedName("baseRate")
  private Double baseRate = null;

  @SerializedName("effectiveRate")
  private Double effectiveRate = null;

  @SerializedName("effectiveDate")
  private LocalDate effectiveDate = null;

  @SerializedName("cutoffTime")
  private String cutoffTime = null;

  public FixedRateDef baseRate(Double baseRate) {
    this.baseRate = baseRate;
    return this;
  }

   /**
   * Get baseRate
   * @return baseRate
  **/
  @Schema(required = true, description = "")
  public Double getBaseRate() {
    return baseRate;
  }

  public void setBaseRate(Double baseRate) {
    this.baseRate = baseRate;
  }

  public FixedRateDef effectiveRate(Double effectiveRate) {
    this.effectiveRate = effectiveRate;
    return this;
  }

   /**
   * Get effectiveRate
   * @return effectiveRate
  **/
  @Schema(description = "")
  public Double getEffectiveRate() {
    return effectiveRate;
  }

  public void setEffectiveRate(Double effectiveRate) {
    this.effectiveRate = effectiveRate;
  }

  public FixedRateDef effectiveDate(LocalDate effectiveDate) {
    this.effectiveDate = effectiveDate;
    return this;
  }

   /**
   * Get effectiveDate
   * @return effectiveDate
  **/
  @Schema(description = "")
  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(LocalDate effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public FixedRateDef cutoffTime(String cutoffTime) {
    this.cutoffTime = cutoffTime;
    return this;
  }

   /**
   * Get cutoffTime
   * @return cutoffTime
  **/
  @Schema(description = "")
  public String getCutoffTime() {
    return cutoffTime;
  }

  public void setCutoffTime(String cutoffTime) {
    this.cutoffTime = cutoffTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FixedRateDef fixedRateDef = (FixedRateDef) o;
    return Objects.equals(this.baseRate, fixedRateDef.baseRate) &&
        Objects.equals(this.effectiveRate, fixedRateDef.effectiveRate) &&
        Objects.equals(this.effectiveDate, fixedRateDef.effectiveDate) &&
        Objects.equals(this.cutoffTime, fixedRateDef.cutoffTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseRate, effectiveRate, effectiveDate, cutoffTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FixedRateDef {\n");
    
    sb.append("    baseRate: ").append(toIndentedString(baseRate)).append("\n");
    sb.append("    effectiveRate: ").append(toIndentedString(effectiveRate)).append("\n");
    sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
    sb.append("    cutoffTime: ").append(toIndentedString(cutoffTime)).append("\n");
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
