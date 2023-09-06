package com.os.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.os.api.model.FeeRate;
import com.os.api.model.OneOfTradeAgreementRate;
import com.os.api.model.RebateRate;

public class RateDeserializer extends StdDeserializer<OneOfTradeAgreementRate> {

	public RateDeserializer() {
		this(null);
	}

	protected RateDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public OneOfTradeAgreementRate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		OneOfTradeAgreementRate impl = null;
		
		JsonNode node = p.readValueAsTree();
		
		JsonNode rf = node.get("rebate");
		if (rf != null) {
			RebateRate rebateRate = new RebateRate();
			impl = rebateRate;
		} else {
			rf = node.get("fee");
			if (rf != null) {
				FeeRate feeRate = new FeeRate();
				feeRate.setFee(null);
				impl = feeRate;
			}
		}

		return impl;
	}
}
