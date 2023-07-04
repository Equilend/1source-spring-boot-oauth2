package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RerateProposal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class RerateProposal   {
  @JsonProperty("contractId")
  private String contractId = null;

  @JsonProperty("rate")
  private Rate rate = null;

  public RerateProposal contractId(String contractId) {
    this.contractId = contractId;
    return this;
  }

  /**
   * The unique identifier of a contract - UUID
   * @return contractId
   **/
  @Schema(required = true, description = "The unique identifier of a contract - UUID")
      @NotNull

    public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public RerateProposal rate(Rate rate) {
    this.rate = rate;
    return this;
  }

  /**
   * Get rate
   * @return rate
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Rate getRate() {
    return rate;
  }

  public void setRate(Rate rate) {
    this.rate = rate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RerateProposal rerateProposal = (RerateProposal) o;
    return Objects.equals(this.contractId, rerateProposal.contractId) &&
        Objects.equals(this.rate, rerateProposal.rate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractId, rate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RerateProposal {\n");
    
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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
