package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Buyin
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Buyin   {
  @JsonProperty("buyinId")
  private String buyinId = null;

  @JsonProperty("contractId")
  private String contractId = null;

  @JsonProperty("status")
  private ContractStatus status = null;

  @JsonProperty("quantity")
  private BigDecimal quantity = null;

  @JsonProperty("lastUpdateDatetime")
  private OffsetDateTime lastUpdateDatetime = null;

  public Buyin buyinId(String buyinId) {
    this.buyinId = buyinId;
    return this;
  }

  /**
   * The unique identifier of a buyin - UUID
   * @return buyinId
   **/
  @Schema(required = true, description = "The unique identifier of a buyin - UUID")
      @NotNull

    public String getBuyinId() {
    return buyinId;
  }

  public void setBuyinId(String buyinId) {
    this.buyinId = buyinId;
  }

  public Buyin contractId(String contractId) {
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

  public Buyin status(ContractStatus status) {
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

  public Buyin quantity(BigDecimal quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public Buyin lastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
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
    Buyin buyin = (Buyin) o;
    return Objects.equals(this.buyinId, buyin.buyinId) &&
        Objects.equals(this.contractId, buyin.contractId) &&
        Objects.equals(this.status, buyin.status) &&
        Objects.equals(this.quantity, buyin.quantity) &&
        Objects.equals(this.lastUpdateDatetime, buyin.lastUpdateDatetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyinId, contractId, status, quantity, lastUpdateDatetime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Buyin {\n");
    
    sb.append("    buyinId: ").append(toIndentedString(buyinId)).append("\n");
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
