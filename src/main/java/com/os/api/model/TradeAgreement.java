package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TradeAgreement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class TradeAgreement   {
  @JsonProperty("executionVenue")
  private Venue executionVenue = null;

  @JsonProperty("instrument")
  private Instrument instrument = null;

  @JsonProperty("rate")
  private Rate rate = null;

  @JsonProperty("quantity")
  private BigDecimal quantity = null;

  @JsonProperty("billingCurrency")
  private CurrencyCd billingCurrency = null;

  @JsonProperty("dividendRatePct")
  private Float dividendRatePct = null;

  @JsonProperty("tradeDate")
  private LocalDate tradeDate = null;

  @JsonProperty("termType")
  private TermType termType = null;

  @JsonProperty("termDate")
  private LocalDate termDate = null;

  @JsonProperty("settlementDate")
  private LocalDate settlementDate = null;

  @JsonProperty("settlementType")
  private SettlementType settlementType = null;

  @JsonProperty("collateral")
  private Collateral collateral = null;

  @JsonProperty("transactingParties")
  private TransactingParties transactingParties = null;

  public TradeAgreement executionVenue(Venue executionVenue) {
    this.executionVenue = executionVenue;
    return this;
  }

  /**
   * Get executionVenue
   * @return executionVenue
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Venue getExecutionVenue() {
    return executionVenue;
  }

  public void setExecutionVenue(Venue executionVenue) {
    this.executionVenue = executionVenue;
  }

  public TradeAgreement instrument(Instrument instrument) {
    this.instrument = instrument;
    return this;
  }

  /**
   * Get instrument
   * @return instrument
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Instrument getInstrument() {
    return instrument;
  }

  public void setInstrument(Instrument instrument) {
    this.instrument = instrument;
  }

  public TradeAgreement rate(Rate rate) {
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

  public TradeAgreement quantity(BigDecimal quantity) {
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

  public TradeAgreement billingCurrency(CurrencyCd billingCurrency) {
    this.billingCurrency = billingCurrency;
    return this;
  }

  /**
   * Get billingCurrency
   * @return billingCurrency
   **/
  @Schema(description = "")
  
    @Valid
    public CurrencyCd getBillingCurrency() {
    return billingCurrency;
  }

  public void setBillingCurrency(CurrencyCd billingCurrency) {
    this.billingCurrency = billingCurrency;
  }

  public TradeAgreement dividendRatePct(Float dividendRatePct) {
    this.dividendRatePct = dividendRatePct;
    return this;
  }

  /**
   * Get dividendRatePct
   * @return dividendRatePct
   **/
  @Schema(description = "")
  
    public Float getDividendRatePct() {
    return dividendRatePct;
  }

  public void setDividendRatePct(Float dividendRatePct) {
    this.dividendRatePct = dividendRatePct;
  }

  public TradeAgreement tradeDate(LocalDate tradeDate) {
    this.tradeDate = tradeDate;
    return this;
  }

  /**
   * Get tradeDate
   * @return tradeDate
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LocalDate getTradeDate() {
    return tradeDate;
  }

  public void setTradeDate(LocalDate tradeDate) {
    this.tradeDate = tradeDate;
  }

  public TradeAgreement termType(TermType termType) {
    this.termType = termType;
    return this;
  }

  /**
   * Get termType
   * @return termType
   **/
  @Schema(description = "")
  
    @Valid
    public TermType getTermType() {
    return termType;
  }

  public void setTermType(TermType termType) {
    this.termType = termType;
  }

  public TradeAgreement termDate(LocalDate termDate) {
    this.termDate = termDate;
    return this;
  }

  /**
   * Get termDate
   * @return termDate
   **/
  @Schema(description = "")
  
    @Valid
    public LocalDate getTermDate() {
    return termDate;
  }

  public void setTermDate(LocalDate termDate) {
    this.termDate = termDate;
  }

  public TradeAgreement settlementDate(LocalDate settlementDate) {
    this.settlementDate = settlementDate;
    return this;
  }

  /**
   * Get settlementDate
   * @return settlementDate
   **/
  @Schema(description = "")
  
    @Valid
    public LocalDate getSettlementDate() {
    return settlementDate;
  }

  public void setSettlementDate(LocalDate settlementDate) {
    this.settlementDate = settlementDate;
  }

  public TradeAgreement settlementType(SettlementType settlementType) {
    this.settlementType = settlementType;
    return this;
  }

  /**
   * Get settlementType
   * @return settlementType
   **/
  @Schema(description = "")
  
    @Valid
    public SettlementType getSettlementType() {
    return settlementType;
  }

  public void setSettlementType(SettlementType settlementType) {
    this.settlementType = settlementType;
  }

  public TradeAgreement collateral(Collateral collateral) {
    this.collateral = collateral;
    return this;
  }

  /**
   * Get collateral
   * @return collateral
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Collateral getCollateral() {
    return collateral;
  }

  public void setCollateral(Collateral collateral) {
    this.collateral = collateral;
  }

  public TradeAgreement transactingParties(TransactingParties transactingParties) {
    this.transactingParties = transactingParties;
    return this;
  }

  /**
   * Get transactingParties
   * @return transactingParties
   **/
  @Schema(description = "")
  
    @Valid
    public TransactingParties getTransactingParties() {
    return transactingParties;
  }

  public void setTransactingParties(TransactingParties transactingParties) {
    this.transactingParties = transactingParties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TradeAgreement tradeAgreement = (TradeAgreement) o;
    return Objects.equals(this.executionVenue, tradeAgreement.executionVenue) &&
        Objects.equals(this.instrument, tradeAgreement.instrument) &&
        Objects.equals(this.rate, tradeAgreement.rate) &&
        Objects.equals(this.quantity, tradeAgreement.quantity) &&
        Objects.equals(this.billingCurrency, tradeAgreement.billingCurrency) &&
        Objects.equals(this.dividendRatePct, tradeAgreement.dividendRatePct) &&
        Objects.equals(this.tradeDate, tradeAgreement.tradeDate) &&
        Objects.equals(this.termType, tradeAgreement.termType) &&
        Objects.equals(this.termDate, tradeAgreement.termDate) &&
        Objects.equals(this.settlementDate, tradeAgreement.settlementDate) &&
        Objects.equals(this.settlementType, tradeAgreement.settlementType) &&
        Objects.equals(this.collateral, tradeAgreement.collateral) &&
        Objects.equals(this.transactingParties, tradeAgreement.transactingParties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(executionVenue, instrument, rate, quantity, billingCurrency, dividendRatePct, tradeDate, termType, termDate, settlementDate, settlementType, collateral, transactingParties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TradeAgreement {\n");
    
    sb.append("    executionVenue: ").append(toIndentedString(executionVenue)).append("\n");
    sb.append("    instrument: ").append(toIndentedString(instrument)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    billingCurrency: ").append(toIndentedString(billingCurrency)).append("\n");
    sb.append("    dividendRatePct: ").append(toIndentedString(dividendRatePct)).append("\n");
    sb.append("    tradeDate: ").append(toIndentedString(tradeDate)).append("\n");
    sb.append("    termType: ").append(toIndentedString(termType)).append("\n");
    sb.append("    termDate: ").append(toIndentedString(termDate)).append("\n");
    sb.append("    settlementDate: ").append(toIndentedString(settlementDate)).append("\n");
    sb.append("    settlementType: ").append(toIndentedString(settlementType)).append("\n");
    sb.append("    collateral: ").append(toIndentedString(collateral)).append("\n");
    sb.append("    transactingParties: ").append(toIndentedString(transactingParties)).append("\n");
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
