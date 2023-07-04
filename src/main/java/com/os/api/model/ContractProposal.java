package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ContractProposal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class ContractProposal   {
  @JsonProperty("trade")
  private TradeAgreement trade = null;

  @JsonProperty("settlement")
  @Valid
  private List<Settlement> settlement = null;

  public ContractProposal trade(TradeAgreement trade) {
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

  public ContractProposal settlement(List<Settlement> settlement) {
    this.settlement = settlement;
    return this;
  }

  public ContractProposal addSettlementItem(Settlement settlementItem) {
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
    ContractProposal contractProposal = (ContractProposal) o;
    return Objects.equals(this.trade, contractProposal.trade) &&
        Objects.equals(this.settlement, contractProposal.settlement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trade, settlement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractProposal {\n");
    
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
