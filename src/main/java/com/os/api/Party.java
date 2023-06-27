package com.os.api;

import java.util.List;

public class Party {

	String partyId;
	String gleifLei;
	List<String> users;

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getGleifLei() {
		return gleifLei;
	}

	public void setGleifLei(String gleifLei) {
		this.gleifLei = gleifLei;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", gleifLei=" + gleifLei + ", users=" + users + "]";
	}

}
