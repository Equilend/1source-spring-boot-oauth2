package com.os.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractProposalForm {

	@JsonProperty("myParty")
	private String myParty = null;
	@JsonProperty("counterparty")
	private String counterparty = null;
	@JsonProperty("instrument")
	private String instrument = null;
	@JsonProperty("rateType")
	private String rateType = null;
	@JsonProperty("rate")
	private String rate = null;
	@JsonProperty("benchmark")
	private String benchmark = null;
	@JsonProperty("quantity")
	private String quantity = null;
	@JsonProperty("price")
	private String price = null;
	@JsonProperty("settlmentBic")
	private String settlmentBic = null;
	@JsonProperty("localAgentBic")
	private String localAgentBic = null;
	@JsonProperty("localAgentName")
	private String localAgentName = null;
	@JsonProperty("localAgentAcct")
	private String localAgentAcct = null;
	@JsonProperty("localFieldName1")
	private String localFieldName1 = null;
	@JsonProperty("localFieldValue1")
	private String localFieldValue1 = null;
	@JsonProperty("localFieldName2")
	private String localFieldName2 = null;
	@JsonProperty("localFieldValue2")
	private String localFieldValue2 = null;

	public String getMyParty() {
		return myParty;
	}

	public void setMyParty(String myParty) {
		this.myParty = myParty;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSettlmentBic() {
		return settlmentBic;
	}

	public void setSettlmentBic(String settlmentBic) {
		this.settlmentBic = settlmentBic;
	}

	public String getLocalAgentBic() {
		return localAgentBic;
	}

	public void setLocalAgentBic(String localAgentBic) {
		this.localAgentBic = localAgentBic;
	}

	public String getLocalAgentName() {
		return localAgentName;
	}

	public void setLocalAgentName(String localAgentName) {
		this.localAgentName = localAgentName;
	}

	public String getLocalAgentAcct() {
		return localAgentAcct;
	}

	public void setLocalAgentAcct(String localAgentAcct) {
		this.localAgentAcct = localAgentAcct;
	}

	public String getLocalFieldName1() {
		return localFieldName1;
	}

	public void setLocalFieldName1(String localFieldName1) {
		this.localFieldName1 = localFieldName1;
	}

	public String getLocalFieldValue1() {
		return localFieldValue1;
	}

	public void setLocalFieldValue1(String localFieldValue1) {
		this.localFieldValue1 = localFieldValue1;
	}

	public String getLocalFieldName2() {
		return localFieldName2;
	}

	public void setLocalFieldName2(String localFieldName2) {
		this.localFieldName2 = localFieldName2;
	}

	public String getLocalFieldValue2() {
		return localFieldValue2;
	}

	public void setLocalFieldValue2(String localFieldValue2) {
		this.localFieldValue2 = localFieldValue2;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(benchmark, counterparty, instrument, localAgentAcct, localAgentBic, localAgentName,
				localFieldName1, localFieldName2, localFieldValue1, localFieldValue2, myParty, price, quantity, rate,
				rateType, settlmentBic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractProposalForm other = (ContractProposalForm) obj;
		return Objects.equals(benchmark, other.benchmark) && Objects.equals(counterparty, other.counterparty)
				&& Objects.equals(instrument, other.instrument) && Objects.equals(localAgentAcct, other.localAgentAcct)
				&& Objects.equals(localAgentBic, other.localAgentBic)
				&& Objects.equals(localAgentName, other.localAgentName)
				&& Objects.equals(localFieldName1, other.localFieldName1)
				&& Objects.equals(localFieldName2, other.localFieldName2)
				&& Objects.equals(localFieldValue1, other.localFieldValue1)
				&& Objects.equals(localFieldValue2, other.localFieldValue2) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(price, other.price) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(rate, other.rate) && Objects.equals(rateType, other.rateType)
				&& Objects.equals(settlmentBic, other.settlmentBic);
	}

}
