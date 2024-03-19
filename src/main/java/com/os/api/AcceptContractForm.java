package com.os.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AcceptContractForm {

	@JsonProperty("myParty")
	private String myParty = null;
	@JsonProperty("counterparty")
	private String counterparty = null;
	@JsonProperty("partyRole")
	private String partyRole = null;
	@JsonProperty("internalRefId")
	private String internalRefId = null;
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

//	@JsonProperty("custodianName")
//	private String custodianName = null;
//	@JsonProperty("custodianBic")
//	private String custodianBic = null;
//	@JsonProperty("custodianAcct")
//	private String custodianAcct = null;
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
		return Objects.hash(cdsCustomerUnitId, counterparty, dtcParticipantNumber, internalAcctCd, internalRefId,
				localAgentAcct, localAgentBic, localAgentName, myParty, partyRole, settlementStatus, settlmentBic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcceptContractForm other = (AcceptContractForm) obj;
		return Objects.equals(cdsCustomerUnitId, other.cdsCustomerUnitId)
				&& Objects.equals(counterparty, other.counterparty)
				&& Objects.equals(dtcParticipantNumber, other.dtcParticipantNumber)
				&& Objects.equals(internalAcctCd, other.internalAcctCd)
				&& Objects.equals(internalRefId, other.internalRefId)
				&& Objects.equals(localAgentAcct, other.localAgentAcct)
				&& Objects.equals(localAgentBic, other.localAgentBic)
				&& Objects.equals(localAgentName, other.localAgentName) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(partyRole, other.partyRole)
				&& Objects.equals(settlementStatus, other.settlementStatus)
				&& Objects.equals(settlmentBic, other.settlmentBic);
	}

}