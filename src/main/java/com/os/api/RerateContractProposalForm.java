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
	@JsonProperty("benchmarkRate")
	private String benchmarkRate = null;

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

	public String getBenchmarkRate() {
		return benchmarkRate;
	}

	public void setBenchmarkRate(String benchmarkRate) {
		this.benchmarkRate = benchmarkRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(benchmark, benchmarkRate, contractId, counterparty, myParty, partyRole, rate, rateType);
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
		return Objects.equals(benchmark, other.benchmark) && Objects.equals(benchmarkRate, other.benchmarkRate)
				&& Objects.equals(contractId, other.contractId) && Objects.equals(counterparty, other.counterparty)
				&& Objects.equals(myParty, other.myParty) && Objects.equals(partyRole, other.partyRole)
				&& Objects.equals(rate, other.rate) && Objects.equals(rateType, other.rateType);
	}

}