package com.os.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.api.SearchParty;

@Component
public class LedgerPartyRepository implements PartyRepository {

    private static final Logger logger = LoggerFactory.getLogger(LedgerPartyRepository.class);

    @Value("classpath:parties.json")
    Resource partiesFile;
    
	private static Map<String, SearchParty> partyMap = null;
	private static Map<String, List<SearchParty>> userPartyMap = null;

	@Override
	public SearchParty getParty(String partyId) {
		return partyMap.get(partyId);
	}
	
	
	@Override
	public List<SearchParty> getPartiesByUser(String username) {
		
		List<SearchParty> parties = null;
		
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

		partyMap = new ConcurrentHashMap<String, SearchParty>();
		userPartyMap = new ConcurrentHashMap<String, List<SearchParty>>();

		List<SearchParty> list = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		list = Arrays.asList(objectMapper.readValue(partiesFile.getInputStream(), SearchParty[].class));
		
		if (list != null) {
			for (SearchParty p : list) {
				partyMap.put(p.getPartyId(), p);
				
				for (String user : p.getUsers()) {
					List<SearchParty> userParties = userPartyMap.get(user.toLowerCase());
					if (userParties == null) {
						userParties = new ArrayList<SearchParty>();
						userPartyMap.put(user.toLowerCase(), userParties);
					}
					userParties.add(p);
					logger.debug("User: " + user + " Party: " + p.getPartyId());
				}
			}
		}
	}
}
