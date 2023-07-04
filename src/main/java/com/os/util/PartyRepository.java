package com.os.util;

import java.util.List;

import com.os.api.model.Party;

public interface PartyRepository {

	Party getParty(String partyId);
	List<Party> getPartiesByUser(String username);
}
