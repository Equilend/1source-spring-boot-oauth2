package com.os.api;

import java.util.List;

import com.os.api.model.Party;

public class SearchParty extends Party {

	List<String> users;

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}
