package com.os.util;

import java.util.List;

import com.os.api.SearchInstrument;

public interface InstrumentRepository {

	List<SearchInstrument> getInstrumentsMatching(String prefix);
}
