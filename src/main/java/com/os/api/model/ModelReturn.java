package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ModelReturn
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class ModelReturn   {
  @JsonProperty("returnId")
  private String returnId = null;

  @JsonProperty("contractId")
  private String contractId = null;

  @JsonProperty("status")
  private ContractStatus status = null;

  @JsonProperty("quantity")
  private BigDecimal quantity = null;

  @JsonProperty("settlement")
  @Valid
  private List<SettlementInstruction> settlement = null;

  @JsonProperty("lastUpdateDatetime")
  private OffsetDateTime lastUpdateDatetime = null;

  public ModelReturn returnId(String returnId) {
    this.returnId = returnId;
    return this;
  }

  /**
   * The unique identifier of a return - UUID
   * @return returnId
   **/
  @Schema(description = "The unique identifier of a return - UUID")
  
    public String getReturnId() {
    return returnId;
  }

  public void setReturnId(String returnId) {
    this.returnId = returnId;
  }

  public ModelReturn contractId(String contractId) {
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

  public ModelReturn status(ContractStatus status) {
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

  public ModelReturn quantity(BigDecimal quantity) {
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

  public ModelReturn settlement(List<SettlementInstruction> settlement) {
    this.settlement = settlement;
    return this;
  }

  public ModelReturn addSettlementItem(SettlementInstruction settlementItem) {
    if (this.settlement == null) {
      this.settlement = new ArrayList<SettlementInstruction>();
    }
    this.settlement.add(settlementItem);
    return this;
  }

  /**
   * Get settlement
   * @return settlement
   **/
  @Schema(description = "")
      @Valid
    public List<SettlementInstruction> getSettlement() {
    return settlement;
  }

  public void setSettlement(List<SettlementInstruction> settlement) {
    this.settlement = settlement;
  }

  public ModelReturn lastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
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
    ModelReturn _return = (ModelReturn) o;
    return Objects.equals(this.returnId, _return.returnId) &&
        Objects.equals(this.contractId, _return.contractId) &&
        Objects.equals(this.status, _return.status) &&
        Objects.equals(this.quantity, _return.quantity) &&
        Objects.equals(this.settlement, _return.settlement) &&
        Objects.equals(this.lastUpdateDatetime, _return.lastUpdateDatetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(returnId, contractId, status, quantity, settlement, lastUpdateDatetime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelReturn {\n");
    
    sb.append("    returnId: ").append(toIndentedString(returnId)).append("\n");
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
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
