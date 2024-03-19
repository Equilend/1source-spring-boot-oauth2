package com.os.api;

import java.util.Objects;

public class AgreementForm {

	private String venueParty;
	private String venueName;
	private String venueRefKey;
	private String partyRole;
	private String internalRefId;
	private String instrument;
	private String rate;
	private String quantity;
	private String myParty;
	private String counterparty;

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
		return Objects.hash(counterparty, instrument, internalRefId, myParty, partyRole, quantity, rate, venueName,
				venueParty, venueRefKey);
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
				&& Objects.equals(internalRefId, other.internalRefId) && Objects.equals(myParty, other.myParty)
				&& Objects.equals(partyRole, other.partyRole) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(rate, other.rate) && Objects.equals(venueName, other.venueName)
				&& Objects.equals(venueParty, other.venueParty) && Objects.equals(venueRefKey, other.venueRefKey);
	}

}
