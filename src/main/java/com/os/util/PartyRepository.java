package com.os.util;

import java.util.List;

import com.os.api.SearchParty;

public interface PartyRepository {

	SearchParty getParty(String partyId);
	List<SearchParty> getPartiesByUser(String username);
}
