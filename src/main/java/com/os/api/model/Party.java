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
/**
 * Party
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-24T20:38:16.660246147Z[GMT]")

public class Party {
  @SerializedName("partyId")
  private String partyId = null;

  @SerializedName("partyName")
  private String partyName = null;

  @SerializedName("gleifLei")
  private String gleifLei = null;

  @SerializedName("internalPartyId")
  private String internalPartyId = null;

  public Party partyId(String partyId) {
    this.partyId = partyId;
    return this;
  }

   /**
   * Get partyId
   * @return partyId
  **/
  @Schema(description = "")
  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public Party partyName(String partyName) {
    this.partyName = partyName;
    return this;
  }

   /**
   * Get partyName
   * @return partyName
  **/
  @Schema(description = "")
  public String getPartyName() {
    return partyName;
  }

  public void setPartyName(String partyName) {
    this.partyName = partyName;
  }

  public Party gleifLei(String gleifLei) {
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

  public Party internalPartyId(String internalPartyId) {
    this.internalPartyId = internalPartyId;
    return this;
  }

   /**
   * Get internalPartyId
   * @return internalPartyId
  **/
  @Schema(description = "")
  public String getInternalPartyId() {
    return internalPartyId;
  }

  public void setInternalPartyId(String internalPartyId) {
    this.internalPartyId = internalPartyId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Party party = (Party) o;
    return Objects.equals(this.partyId, party.partyId) &&
        Objects.equals(this.partyName, party.partyName) &&
        Objects.equals(this.gleifLei, party.gleifLei) &&
        Objects.equals(this.internalPartyId, party.internalPartyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, partyName, gleifLei, internalPartyId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Party {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    partyName: ").append(toIndentedString(partyName)).append("\n");
    sb.append("    gleifLei: ").append(toIndentedString(gleifLei)).append("\n");
    sb.append("    internalPartyId: ").append(toIndentedString(internalPartyId)).append("\n");
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
