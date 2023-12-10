/*
 * 1Source Ledger API
 * 1Source Ledger API provides client access to the 1Source Ledger. You can find out more about 1Source at [https://equilend.com](https://equilend.com).  This specification is work in progress. The design is meant to model the securities lending life cycle in as clean a way as possible while being robust enough to easily translate to ISLA CDM workflows and data model.  API specification is the intellectual property of EquiLend LLC and should not be copied or disseminated in any way. 
 *
 * OpenAPI spec version: 1.0.3
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
import java.time.OffsetDateTime;
/**
 * Platform
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-09-07T19:46:14.871947520Z[GMT]")

public class Platform {
  @SerializedName("gleifLei")
  private String gleifLei = null;

  @SerializedName("legalName")
  private String legalName = null;

  @SerializedName("mic")
  private String mic = null;

  @SerializedName("venueName")
  private String venueName = null;

  @SerializedName("venueRefId")
  private String venueRefId = null;

  @SerializedName("transactionDatetime")
  private OffsetDateTime transactionDatetime = null;

  public Platform gleifLei(String gleifLei) {
    this.gleifLei = gleifLei;
    return this;
  }

   /**
   * Get gleifLei
   * @return gleifLei
  **/
  @Schema(required = true, description = "")
  public String getGleifLei() {
    return gleifLei;
  }

  public void setGleifLei(String gleifLei) {
    this.gleifLei = gleifLei;
  }

  public Platform legalName(String legalName) {
    this.legalName = legalName;
    return this;
  }

   /**
   * Get legalName
   * @return legalName
  **/
  @Schema(description = "")
  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public Platform mic(String mic) {
    this.mic = mic;
    return this;
  }

   /**
   * Get mic
   * @return mic
  **/
  @Schema(description = "")
  public String getMic() {
    return mic;
  }

  public void setMic(String mic) {
    this.mic = mic;
  }

  public Platform venueName(String venueName) {
    this.venueName = venueName;
    return this;
  }

   /**
   * Get venueName
   * @return venueName
  **/
  @Schema(description = "")
  public String getVenueName() {
    return venueName;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public Platform venueRefId(String venueRefId) {
    this.venueRefId = venueRefId;
    return this;
  }

   /**
   * Get venueRefId
   * @return venueRefId
  **/
  @Schema(description = "")
  public String getVenueRefId() {
    return venueRefId;
  }

  public void setVenueRefId(String venueRefId) {
    this.venueRefId = venueRefId;
  }

  public Platform transactionDatetime(OffsetDateTime transactionDatetime) {
    this.transactionDatetime = transactionDatetime;
    return this;
  }

   /**
   * Get transactionDatetime
   * @return transactionDatetime
  **/
  @Schema(description = "")
  public OffsetDateTime getTransactionDatetime() {
    return transactionDatetime;
  }

  public void setTransactionDatetime(OffsetDateTime transactionDatetime) {
    this.transactionDatetime = transactionDatetime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Platform platform = (Platform) o;
    return Objects.equals(this.gleifLei, platform.gleifLei) &&
        Objects.equals(this.legalName, platform.legalName) &&
        Objects.equals(this.mic, platform.mic) &&
        Objects.equals(this.venueName, platform.venueName) &&
        Objects.equals(this.venueRefId, platform.venueRefId) &&
        Objects.equals(this.transactionDatetime, platform.transactionDatetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gleifLei, legalName, mic, venueName, venueRefId, transactionDatetime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Platform {\n");
    
    sb.append("    gleifLei: ").append(toIndentedString(gleifLei)).append("\n");
    sb.append("    legalName: ").append(toIndentedString(legalName)).append("\n");
    sb.append("    mic: ").append(toIndentedString(mic)).append("\n");
    sb.append("    venueName: ").append(toIndentedString(venueName)).append("\n");
    sb.append("    venueRefId: ").append(toIndentedString(venueRefId)).append("\n");
    sb.append("    transactionDatetime: ").append(toIndentedString(transactionDatetime)).append("\n");
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
