package com.os.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractProposalForm {

	@JsonProperty("venueParty")
	private String venueParty;
	@JsonProperty("venueName")
	private String venueName;
	@JsonProperty("venueRefKey")
	private String venueRefKey;
	@JsonProperty("myParty")
	private String myParty = null;
	@JsonProperty("counterparty")
	private String counterparty = null;
	@JsonProperty("partyRole")
	private String partyRole = null;
	@JsonProperty("internalRefId")
	private String internalRefId = null;
	@JsonProperty("instrument")
	private String instrument = null;
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
	@JsonProperty("quantity")
	private String quantity = null;
	@JsonProperty("price")
	private String price = null;
	@JsonProperty("settlementStatus")
	private String settlementStatus = null;
	@JsonProperty("internalAcctCd")
	private String internalAcctCd = null;
	@JsonProperty("settlmentBic")
	private String settlmentBic = null;
	@JsonProperty("localAgentBic")
	private String localAgentBic = null;
	@JsonProperty("localAgentName")
	private String localAgentName = null;
	@JsonProperty("localAgentAcct")
	private String localAgentAcct = null;
	@JsonProperty("dtcParticipantNumber")
	private String dtcParticipantNumber = null;
	@JsonProperty("cdsCustomerUnitId")
	private String cdsCustomerUnitId = null;

	public String getVenueParty() {
		return venueParty;
	}

	public void setVenueParty(String venueParty) {
		this.venueParty = venueParty;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenueRefKey() {
		return venueRefKey;
	}

	public void setVenueRefKey(String venueRefKey) {
		this.venueRefKey = venueRefKey;
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

	public String getInternalRefId() {
		return internalRefId;
	}

	public void setInternalRefId(String internalRefId) {
		this.internalRefId = internalRefId;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
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

	public String getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}

	public String getInternalAcctCd() {
		return internalAcctCd;
	}

	public void setInternalAcctCd(String internalAcctCd) {
		this.internalAcctCd = internalAcctCd;
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

	public String getDtcParticipantNumber() {
		return dtcParticipantNumber;
	}

	public void setDtcParticipantNumber(String dtcParticipantNumber) {
		this.dtcParticipantNumber = dtcParticipantNumber;
	}

	public String getCdsCustomerUnitId() {
		return cdsCustomerUnitId;
	}

	public void setCdsCustomerUnitId(String cdsCustomerUnitId) {
		this.cdsCustomerUnitId = cdsCustomerUnitId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autoRerate, baseRate, benchmark, cdsCustomerUnitId, counterparty, dtcParticipantNumber,
				instrument, internalAcctCd, internalRefId, localAgentAcct, localAgentBic, localAgentName, myParty,
				partyRole, price, quantity, rate, rateType, settlementStatus, settlmentBic, venueName, venueParty,
				venueRefKey);
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
		return Objects.equals(autoRerate, other.autoRerate) && Objects.equals(baseRate, other.baseRate)
				&& Objects.equals(benchmark, other.benchmark)
				&& Objects.equals(cdsCustomerUnitId, other.cdsCustomerUnitId)
				&& Objects.equals(counterparty, other.counterparty)
				&& Objects.equals(dtcParticipantNumber, other.dtcParticipantNumber)
				&& Objects.equals(instrument, other.instrument) && Objects.equals(internalAcctCd, other.internalAcctCd)
				&& Objects.equals(internalRefId, other.internalRefId)
				&& Objects.equals(localAgentAcct, other.localAgentAcct)
				&& Objects.equals(localAgentBic, other.localAgentBic)
				&& Objects.equals(localAgentName, other.localAgentName) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(partyRole, other.partyRole) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(rate, other.rate)
				&& Objects.equals(rateType, other.rateType) && Objects.equals(settlementStatus, other.settlementStatus)
				&& Objects.equals(settlmentBic, other.settlmentBic) && Objects.equals(venueName, other.venueName)
				&& Objects.equals(venueParty, other.venueParty) && Objects.equals(venueRefKey, other.venueRefKey);
	}

}
