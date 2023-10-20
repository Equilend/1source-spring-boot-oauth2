package com.os.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.os.api.model.TradeAgreement;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class ContractFromAgreementProposalForm   {
  @JsonProperty("trade")
  private TradeAgreement trade = null;

  @JsonProperty("settlement")
  @Valid
  private List<NameValuePair> settlement = null;

  public ContractFromAgreementProposalForm trade(TradeAgreement trade) {
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

  public ContractFromAgreementProposalForm settlement(List<NameValuePair> settlement) {
    this.settlement = settlement;
    return this;
  }

  public ContractFromAgreementProposalForm addSettlementItem(NameValuePair settlementItem) {
    if (this.settlement == null) {
      this.settlement = new ArrayList<>();
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
    public List<NameValuePair> getSettlement() {
    return settlement;
  }

  public void setSettlement(List<NameValuePair> settlement) {
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
    ContractFromAgreementProposalForm contractProposal = (ContractFromAgreementProposalForm) o;
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
