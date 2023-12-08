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
/**
 * LocalVenueField
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-12-07T14:42:49.871457665Z[GMT]")

public class LocalVenueField {
  @SerializedName("localFieldName")
  private String localFieldName = null;

  @SerializedName("localFieldValue")
  private String localFieldValue = null;

  public LocalVenueField localFieldName(String localFieldName) {
    this.localFieldName = localFieldName;
    return this;
  }

   /**
   * Local market field Name
   * @return localFieldName
  **/
  @Schema(description = "Local market field Name")
  public String getLocalFieldName() {
    return localFieldName;
  }

  public void setLocalFieldName(String localFieldName) {
    this.localFieldName = localFieldName;
  }

  public LocalVenueField localFieldValue(String localFieldValue) {
    this.localFieldValue = localFieldValue;
    return this;
  }

   /**
   * Local market field value
   * @return localFieldValue
  **/
  @Schema(description = "Local market field value")
  public String getLocalFieldValue() {
    return localFieldValue;
  }

  public void setLocalFieldValue(String localFieldValue) {
    this.localFieldValue = localFieldValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalVenueField localVenueField = (LocalVenueField) o;
    return Objects.equals(this.localFieldName, localVenueField.localFieldName) &&
        Objects.equals(this.localFieldValue, localVenueField.localFieldValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localFieldName, localFieldValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalVenueField {\n");
    
    sb.append("    localFieldName: ").append(toIndentedString(localFieldName)).append("\n");
    sb.append("    localFieldValue: ").append(toIndentedString(localFieldValue)).append("\n");
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
