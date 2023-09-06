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
 * InternalReferenceUpdate
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-24T20:38:16.660246147Z[GMT]")

public class InternalReferenceUpdate implements ContractsContractIdBody {
  @SerializedName("internalRef")
  private InternalReference internalRef = null;

  public InternalReferenceUpdate internalRef(InternalReference internalRef) {
    this.internalRef = internalRef;
    return this;
  }

   /**
   * Get internalRef
   * @return internalRef
  **/
  @Schema(required = true, description = "")
  public InternalReference getInternalRef() {
    return internalRef;
  }

  public void setInternalRef(InternalReference internalRef) {
    this.internalRef = internalRef;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InternalReferenceUpdate internalReferenceUpdate = (InternalReferenceUpdate) o;
    return Objects.equals(this.internalRef, internalReferenceUpdate.internalRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(internalRef);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InternalReferenceUpdate {\n");
    
    sb.append("    internalRef: ").append(toIndentedString(internalRef)).append("\n");
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
