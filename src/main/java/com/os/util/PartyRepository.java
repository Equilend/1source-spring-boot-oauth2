package com.os.util;

import java.util.List;

import com.os.api.Party;

public interface PartyRepository {

	List<Party> getPartiesByUser(String username);
}
