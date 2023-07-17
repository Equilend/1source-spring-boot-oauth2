package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contract
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Contract   {
  @JsonProperty("contractId")
  private String contractId = null;

  @JsonProperty("lastEventId")
  private Integer lastEventId = null;

  @JsonProperty("contractStatus")
  private ContractStatus contractStatus = null;

  @JsonProperty("settlementStatus")
  private SettlementStatus settlementStatus = null;

  @JsonProperty("lastUpdatePartyId")
  private String lastUpdatePartyId = null;

  @JsonProperty("lastUpdateDatetime")
  private OffsetDateTime lastUpdateDatetime = null;

  @JsonProperty("trade")
  private TradeAgreement trade = null;

  @JsonProperty("settlement")
  @Valid
  private List<Settlement> settlement = null;

  public Contract contractId(String contractId) {
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

  public Contract lastEventId(Integer lastEventId) {
    this.lastEventId = lastEventId;
    return this;
  }

  /**
   * The unique identifier of an event - UUID
   * @return lastEventId
   **/
  @Schema(description = "The unique identifier of an event - UUID")
  
    public Integer getLastEventId() {
    return lastEventId;
  }

  public void setLastEventId(Integer lastEventId) {
    this.lastEventId = lastEventId;
  }

  public Contract contractStatus(ContractStatus contractStatus) {
    this.contractStatus = contractStatus;
    return this;
  }

  /**
   * Get contractStatus
   * @return contractStatus
   **/
  @Schema(description = "")
  
    @Valid
    public ContractStatus getContractStatus() {
    return contractStatus;
  }

  public void setContractStatus(ContractStatus contractStatus) {
    this.contractStatus = contractStatus;
  }

  public Contract settlementStatus(SettlementStatus settlementStatus) {
    this.settlementStatus = settlementStatus;
    return this;
  }

  /**
   * Get settlementStatus
   * @return settlementStatus
   **/
  @Schema(description = "")
  
    @Valid
    public SettlementStatus getSettlementStatus() {
    return settlementStatus;
  }

  public void setSettlementStatus(SettlementStatus settlementStatus) {
    this.settlementStatus = settlementStatus;
  }

  public Contract lastUpdatePartyId(String lastUpdatePartyId) {
    this.lastUpdatePartyId = lastUpdatePartyId;
    return this;
  }

  /**
   * The unique identifier of a party - UUID
   * @return lastUpdatePartyId
   **/
  @Schema(description = "The unique identifier of a party - UUID")
  
    public String getLastUpdatePartyId() {
    return lastUpdatePartyId;
  }

  public void setLastUpdatePartyId(String lastUpdatePartyId) {
    this.lastUpdatePartyId = lastUpdatePartyId;
  }

  public Contract lastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
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

  public Contract trade(TradeAgreement trade) {
    this.trade = trade;
    return this;
  }

  /**
   * Get trade
   * @return trade
   **/
  @Schema(description = "")
  
    @Valid
    public TradeAgreement getTrade() {
    return trade;
  }

  public void setTrade(TradeAgreement trade) {
    this.trade = trade;
  }

  public Contract settlement(List<Settlement> settlement) {
    this.settlement = settlement;
    return this;
  }

  public Contract addSettlementItem(Settlement settlementItem) {
    if (this.settlement == null) {
      this.settlement = new ArrayList<Settlement>();
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
    public List<Settlement> getSettlement() {
    return settlement;
  }

  public void setSettlement(List<Settlement> settlement) {
    this.settlement = settlement;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contract contract = (Contract) o;
    return Objects.equals(this.contractId, contract.contractId) &&
        Objects.equals(this.lastEventId, contract.lastEventId) &&
        Objects.equals(this.contractStatus, contract.contractStatus) &&
        Objects.equals(this.settlementStatus, contract.settlementStatus) &&
        Objects.equals(this.lastUpdatePartyId, contract.lastUpdatePartyId) &&
        Objects.equals(this.lastUpdateDatetime, contract.lastUpdateDatetime) &&
        Objects.equals(this.trade, contract.trade) &&
        Objects.equals(this.settlement, contract.settlement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractId, lastEventId, contractStatus, settlementStatus, lastUpdatePartyId, lastUpdateDatetime, trade, settlement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contract {\n");
    
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    lastEventId: ").append(toIndentedString(lastEventId)).append("\n");
    sb.append("    contractStatus: ").append(toIndentedString(contractStatus)).append("\n");
    sb.append("    settlementStatus: ").append(toIndentedString(settlementStatus)).append("\n");
    sb.append("    lastUpdatePartyId: ").append(toIndentedString(lastUpdatePartyId)).append("\n");
    sb.append("    lastUpdateDatetime: ").append(toIndentedString(lastUpdateDatetime)).append("\n");
    sb.append("    trade: ").append(toIndentedString(trade)).append("\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
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
