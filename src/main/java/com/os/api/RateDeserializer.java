package com.os.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.os.api.model.BenchmarkCd;
import com.os.api.model.FeeRate;
import com.os.api.model.FixedRate;
import com.os.api.model.FixedRateDef;
import com.os.api.model.FloatingRate;
import com.os.api.model.FloatingRateDef;
import com.os.api.model.OneOfTradeAgreementRate;
import com.os.api.model.RebateRate;

public class RateDeserializer extends StdDeserializer<OneOfTradeAgreementRate> {

	private static final long serialVersionUID = 9024714383266337284L;

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
		
		JsonNode nodeRate = p.readValueAsTree();
		
		JsonNode nodeRateRebate = nodeRate.get("rebate");
		if (nodeRateRebate != null) {
			RebateRate rebateRate = new RebateRate();
			
			JsonNode nodeRateRebateFixed = nodeRateRebate.get("fixed");
			if (nodeRateRebateFixed != null) {
				FixedRate fixedRate = new FixedRate();
				rebateRate.setRebate(fixedRate);
				
				FixedRateDef fixedRateDef = new FixedRateDef();
				fixedRate.setFixed(fixedRateDef);
				
				JsonNode nodeBaseRate = nodeRateRebateFixed.get("baseRate");
				if (nodeBaseRate != null) {
					fixedRateDef.setBaseRate(nodeBaseRate.doubleValue());
				}

				JsonNode nodeEffectiveRate = nodeRateRebateFixed.get("effectiveRate");
				if (nodeEffectiveRate != null) {
					fixedRateDef.setEffectiveRate(nodeEffectiveRate.doubleValue());
				}

				JsonNode nodeEffectiveDate = nodeRateRebateFixed.get("effectiveDate");
				if (nodeEffectiveDate != null) {
					fixedRateDef.setEffectiveDate(LocalDate.parse(nodeEffectiveDate.asText(), DateTimeFormatter.ISO_LOCAL_DATE));
				}

				JsonNode nodeCutoffTime = nodeRateRebateFixed.get("cutoffTime");
				if (nodeCutoffTime != null) {
					fixedRateDef.setCutoffTime(nodeCutoffTime.asText());
				}

			} else {
				JsonNode nodeRateRebateFloating = nodeRateRebate.get("floating");
				if (nodeRateRebateFloating != null) {
					FloatingRate floatingRate = new FloatingRate();
					rebateRate.setRebate(floatingRate);

					FloatingRateDef floatingRateDef = new FloatingRateDef();
					floatingRate.setFloating(floatingRateDef);
					
					JsonNode nodeBenchmark = nodeRateRebateFloating.get("benchmark");
					if (nodeBenchmark != null) {
						floatingRateDef.setBenchmark(BenchmarkCd.fromValue(nodeBenchmark.asText()));
					}
					
					JsonNode nodeBaseRate = nodeRateRebateFloating.get("baseRate");
					if (nodeBaseRate != null) {
						floatingRateDef.setBaseRate(nodeBaseRate.doubleValue());
					}

					JsonNode nodeSpread = nodeRateRebateFloating.get("spread");
					if (nodeSpread != null) {
						floatingRateDef.setSpread(nodeSpread.doubleValue());
					}

					JsonNode nodeIsAutoRerate = nodeRateRebateFloating.get("isAutoRerate");
					if (nodeIsAutoRerate != null) {
						floatingRateDef.setIsAutoRerate(nodeIsAutoRerate.asBoolean());
					}

					JsonNode nodeEffectiveDateDelay = nodeRateRebateFloating.get("effectiveDateDelay");
					if (nodeEffectiveDateDelay != null) {
						floatingRateDef.setEffectiveDateDelay(nodeEffectiveDateDelay.decimalValue());
					}

					JsonNode nodeEffectiveRate = nodeRateRebateFloating.get("effectiveRate");
					if (nodeEffectiveRate != null) {
						floatingRateDef.setEffectiveRate(nodeEffectiveRate.doubleValue());
					}

					JsonNode nodeEffectiveDate = nodeRateRebateFloating.get("effectiveDate");
					if (nodeEffectiveDate != null) {
						floatingRateDef.setEffectiveDate(LocalDate.parse(nodeEffectiveDate.asText(), DateTimeFormatter.ISO_LOCAL_DATE));
					}

					JsonNode nodeCutoffTime = nodeRateRebateFloating.get("cutoffTime");
					if (nodeCutoffTime != null) {
						floatingRateDef.setCutoffTime(nodeCutoffTime.asText());
					}
				}
			}
			
			impl = rebateRate;
		} else {
			JsonNode nodeRateFee = nodeRate.get("fee");
			if (nodeRateFee != null) {
				FeeRate feeRate = new FeeRate();
				impl = feeRate;
				
				FixedRateDef fixedRateDef = new FixedRateDef();
				feeRate.setFee(fixedRateDef);
				
				JsonNode nodeBaseRate = nodeRateFee.get("baseRate");
				if (nodeBaseRate != null) {
					fixedRateDef.setBaseRate(nodeBaseRate.doubleValue());
				}

				JsonNode nodeEffectiveRate = nodeRateFee.get("effectiveRate");
				if (nodeEffectiveRate != null) {
					fixedRateDef.setEffectiveRate(nodeEffectiveRate.doubleValue());
				}

				JsonNode nodeEffectiveDate = nodeRateFee.get("effectiveDate");
				if (nodeEffectiveDate != null) {
					fixedRateDef.setEffectiveDate(LocalDate.parse(nodeEffectiveDate.asText(), DateTimeFormatter.ISO_LOCAL_DATE));
				}

				JsonNode nodeCutoffTime = nodeRateFee.get("cutoffTime");
				if (nodeCutoffTime != null) {
					fixedRateDef.setCutoffTime(nodeCutoffTime.asText());
				}

			}
		}

		return impl;
	}
}
