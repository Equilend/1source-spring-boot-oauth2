package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * InternalReference
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class InternalReference   {
  @JsonProperty("brokerCd")
  private String brokerCd = null;

  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("internalRefId")
  private String internalRefId = null;

  public InternalReference brokerCd(String brokerCd) {
    this.brokerCd = brokerCd;
    return this;
  }

  /**
   * Get brokerCd
   * @return brokerCd
   **/
  @Schema(description = "")
  
    public String getBrokerCd() {
    return brokerCd;
  }

  public void setBrokerCd(String brokerCd) {
    this.brokerCd = brokerCd;
  }

  public InternalReference accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
   **/
  @Schema(description = "")
  
    public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public InternalReference internalRefId(String internalRefId) {
    this.internalRefId = internalRefId;
    return this;
  }

  /**
   * Get internalRefId
   * @return internalRefId
   **/
  @Schema(description = "")
  
    public String getInternalRefId() {
    return internalRefId;
  }

  public void setInternalRefId(String internalRefId) {
    this.internalRefId = internalRefId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InternalReference internalReference = (InternalReference) o;
    return Objects.equals(this.brokerCd, internalReference.brokerCd) &&
        Objects.equals(this.accountId, internalReference.accountId) &&
        Objects.equals(this.internalRefId, internalReference.internalRefId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brokerCd, accountId, internalRefId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InternalReference {\n");
    
    sb.append("    brokerCd: ").append(toIndentedString(brokerCd)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    internalRefId: ").append(toIndentedString(internalRefId)).append("\n");
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
