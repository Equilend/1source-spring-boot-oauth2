package com.os.util;

import java.util.List;

import com.os.api.SearchInstrument;

public interface InstrumentRepository {

	SearchInstrument getInstrument(String id);
	List<SearchInstrument> getInstruments();
	List<SearchInstrument> getInstrumentsMatching(String prefix);
}
