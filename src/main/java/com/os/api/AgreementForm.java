package com.os.api;

import java.util.Objects;

public class AgreementForm {

	private String partyRole;
	private String instrument;
	private String rate;
	private String quantity;
	private String myParty;
	private String counterparty;

	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
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

	@Override
	public int hashCode() {
		return Objects.hash(counterparty, instrument, myParty, partyRole, quantity, rate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgreementForm other = (AgreementForm) obj;
		return Objects.equals(counterparty, other.counterparty) && Objects.equals(instrument, other.instrument)
				&& Objects.equals(myParty, other.myParty) && Objects.equals(partyRole, other.partyRole)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(rate, other.rate);
	}

}
