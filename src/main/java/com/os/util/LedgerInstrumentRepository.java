package com.os.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.api.SearchInstrument;

@Component
public class LedgerInstrumentRepository implements InstrumentRepository {

    private static final Logger logger = LoggerFactory.getLogger(LedgerInstrumentRepository.class);

	private static List<SearchInstrument> instruments = null;

	@Override
	public List<SearchInstrument> getInstrumentsMatching(String prefix) {
		
		List<SearchInstrument> matchedInstruments = new ArrayList<>();
		
		if (instruments == null) {
			try {
				loadInstruments();
			} catch (Exception e) {
				logger.error("Error loading instruments", e);
			}
		}
		
		if (instruments != null) {
			for (SearchInstrument instrument : instruments) {
				if (instrument.getValue().toLowerCase().startsWith(prefix.toLowerCase())) {
					matchedInstruments.add(instrument);
					if (matchedInstruments.size() == 10) {
						break;
					}
				}
			}
		}
		
		return matchedInstruments;
	}

	private void loadInstruments() throws Exception {

		File file = new ClassPathResource("instruments.json").getFile();
		ObjectMapper objectMapper = new ObjectMapper();
		instruments = Arrays.asList(objectMapper.readValue(file, SearchInstrument[].class));	
		
		logger.info("Loaded " + instruments.size() + " instruments.");
	}

}
