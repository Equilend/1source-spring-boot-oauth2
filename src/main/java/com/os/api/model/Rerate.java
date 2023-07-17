package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Rerate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Rerate   {
  @JsonProperty("rerateId")
  private String rerateId = null;

  @JsonProperty("contractId")
  private String contractId = null;

  @JsonProperty("status")
  private ContractStatus status = null;

  @JsonProperty("rate")
  private Rate rate = null;

  @JsonProperty("rerate")
  private Rate rerate = null;

  @JsonProperty("lastUpdateDatetime")
  private OffsetDateTime lastUpdateDatetime = null;

  public Rerate rerateId(String rerateId) {
    this.rerateId = rerateId;
    return this;
  }

  /**
   * The unique identifier of a rerate - UUID
   * @return rerateId
   **/
  @Schema(required = true, description = "The unique identifier of a rerate - UUID")
      @NotNull

    public String getRerateId() {
    return rerateId;
  }

  public void setRerateId(String rerateId) {
    this.rerateId = rerateId;
  }

  public Rerate contractId(String contractId) {
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

  public Rerate status(ContractStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ContractStatus getStatus() {
    return status;
  }

  public void setStatus(ContractStatus status) {
    this.status = status;
  }

  public Rerate rate(Rate rate) {
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

  public Rerate rerate(Rate rerate) {
    this.rerate = rerate;
    return this;
  }

  /**
   * Get rerate
   * @return rerate
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Rate getRerate() {
    return rerate;
  }

  public void setRerate(Rate rerate) {
    this.rerate = rerate;
  }

  public Rerate lastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
    this.lastUpdateDatetime = lastUpdateDatetime;
    return this;
  }

  /**
   * Get lastUpdateDatetime
   * @return lastUpdateDatetime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getLastUpdateDatetime() {
    return lastUpdateDatetime;
  }

  public void setLastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
    this.lastUpdateDatetime = lastUpdateDatetime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rerate rerate = (Rerate) o;
    return Objects.equals(this.rerateId, rerate.rerateId) &&
        Objects.equals(this.contractId, rerate.contractId) &&
        Objects.equals(this.status, rerate.status) &&
        Objects.equals(this.rate, rerate.rate) &&
        Objects.equals(this.rerate, rerate.rerate) &&
        Objects.equals(this.lastUpdateDatetime, rerate.lastUpdateDatetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rerateId, contractId, status, rate, rerate, lastUpdateDatetime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rerate {\n");
    
    sb.append("    rerateId: ").append(toIndentedString(rerateId)).append("\n");
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    rerate: ").append(toIndentedString(rerate)).append("\n");
    sb.append("    lastUpdateDatetime: ").append(toIndentedString(lastUpdateDatetime)).append("\n");
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
