package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Agreement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Agreement   {
  @JsonProperty("agreementId")
  private String agreementId = null;

  @JsonProperty("lastUpdateDatetime")
  private OffsetDateTime lastUpdateDatetime = null;

  @JsonProperty("trade")
  private TradeAgreement trade = null;

  public Agreement agreementId(String agreementId) {
    this.agreementId = agreementId;
    return this;
  }

  /**
   * The unique identifier of a trade agreement - UUID
   * @return agreementId
   **/
  @Schema(required = true, description = "The unique identifier of a trade agreement - UUID")
      @NotNull

    public String getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(String agreementId) {
    this.agreementId = agreementId;
  }

  public Agreement lastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
    this.lastUpdateDatetime = lastUpdateDatetime;
    return this;
  }

  /**
   * Get lastUpdateDatetime
   * @return lastUpdateDatetime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getLastUpdateDatetime() {
    return lastUpdateDatetime;
  }

  public void setLastUpdateDatetime(OffsetDateTime lastUpdateDatetime) {
    this.lastUpdateDatetime = lastUpdateDatetime;
  }

  public Agreement trade(TradeAgreement trade) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Agreement agreement = (Agreement) o;
    return Objects.equals(this.agreementId, agreement.agreementId) &&
        Objects.equals(this.lastUpdateDatetime, agreement.lastUpdateDatetime) &&
        Objects.equals(this.trade, agreement.trade);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agreementId, lastUpdateDatetime, trade);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Agreement {\n");
    
    sb.append("    agreementId: ").append(toIndentedString(agreementId)).append("\n");
    sb.append("    lastUpdateDatetime: ").append(toIndentedString(lastUpdateDatetime)).append("\n");
    sb.append("    trade: ").append(toIndentedString(trade)).append("\n");
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
