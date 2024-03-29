package com.os.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RerateContractProposalForm {

	@JsonProperty("contractId")
	private String contractId = null;
	@JsonProperty("myParty")
	private String myParty = null;
	@JsonProperty("counterparty")
	private String counterparty = null;
	@JsonProperty("partyRole")
	private String partyRole = null;
	@JsonProperty("rateType")
	private String rateType = null;
	@JsonProperty("rate")
	private String rate = null;
	@JsonProperty("benchmark")
	private String benchmark = null;
	@JsonProperty("autoRerate")
	private String autoRerate = null;
	@JsonProperty("baseRate")
	private String baseRate = null;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

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

	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getAutoRerate() {
		return autoRerate;
	}

	public void setAutoRerate(String autoRerate) {
		this.autoRerate = autoRerate;
	}

	public String getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(String baseRate) {
		this.baseRate = baseRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autoRerate, baseRate, benchmark, contractId, counterparty, myParty, partyRole, rate,
				rateType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RerateContractProposalForm other = (RerateContractProposalForm) obj;
		return Objects.equals(autoRerate, other.autoRerate) && Objects.equals(baseRate, other.baseRate)
				&& Objects.equals(benchmark, other.benchmark) && Objects.equals(contractId, other.contractId)
				&& Objects.equals(counterparty, other.counterparty) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(partyRole, other.partyRole) && Objects.equals(rate, other.rate)
				&& Objects.equals(rateType, other.rateType);
	}

}