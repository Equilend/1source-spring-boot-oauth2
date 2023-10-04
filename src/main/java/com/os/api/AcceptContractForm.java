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

	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
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

	@Override
	public int hashCode() {
		return Objects.hash(counterparty, localAgentAcct, localAgentBic, localAgentName, localFieldName1,
				localFieldName2, localFieldValue1, localFieldValue2, myParty, partyRole, settlmentBic);
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
		return Objects.equals(counterparty, other.counterparty) && Objects.equals(localAgentAcct, other.localAgentAcct)
				&& Objects.equals(localAgentBic, other.localAgentBic)
				&& Objects.equals(localAgentName, other.localAgentName)
				&& Objects.equals(localFieldName1, other.localFieldName1)
				&& Objects.equals(localFieldName2, other.localFieldName2)
				&& Objects.equals(localFieldValue1, other.localFieldValue1)
				&& Objects.equals(localFieldValue2, other.localFieldValue2) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(partyRole, other.partyRole) && Objects.equals(settlmentBic, other.settlmentBic);
	}

}