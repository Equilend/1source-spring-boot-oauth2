package com.os.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.api.SearchParty;
import com.os.api.model.Party;

@Component
public class LedgerPartyRepository implements PartyRepository {

    private static final Logger logger = LoggerFactory.getLogger(LedgerPartyRepository.class);

	private static Map<String, Party> partyMap = null;
	private static Map<String, List<Party>> userPartyMap = null;

	@Override
	public Party getParty(String partyId) {
		return partyMap.get(partyId);
	}
	
	
	@Override
	public List<Party> getPartiesByUser(String username) {
		
		List<Party> parties = null;
		
		if (userPartyMap == null) {
			try {
				loadParties();
			} catch (Exception e) {
				logger.error("Error loading parties", e);
			}
		}
		
		if (userPartyMap != null) {
			parties = userPartyMap.get(username.toLowerCase());
		}

		return parties;
	}

	private void loadParties() throws Exception {

		partyMap = new ConcurrentHashMap<String, Party>();
		userPartyMap = new ConcurrentHashMap<String, List<Party>>();

		List<SearchParty> list = new ArrayList<>();
		File file = new ClassPathResource("parties.json").getFile();
		ObjectMapper objectMapper = new ObjectMapper();
		list = Arrays.asList(objectMapper.readValue(file, SearchParty[].class));
		
		if (list != null) {
			for (SearchParty p : list) {
				partyMap.put(p.getPartyId(), p);
				
				for (String user : p.getUsers()) {
					List<Party> userParties = userPartyMap.get(user.toLowerCase());
					if (userParties == null) {
						userParties = new ArrayList<Party>();
						userPartyMap.put(user.toLowerCase(), userParties);
					}
					userParties.add(p);
					logger.debug("User: " + user + " Party: " + p.getPartyId());
				}
			}
		}
	}
}
