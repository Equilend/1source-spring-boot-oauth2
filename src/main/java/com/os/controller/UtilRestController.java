package com.os.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.AcceptContractForm;
import com.os.api.AgreementForm;
import com.os.api.ContractFromAgreementProposalForm;
import com.os.api.ContractProposalForm;
import com.os.api.NameValuePair;
import com.os.api.SearchInstrument;
import com.os.api.SearchParty;
import com.os.util.LedgerInstrumentRepository;
import com.os.util.LedgerPartyRepository;

import com.os.api.model.*;

@RestController
public class UtilRestController {

	private static final Logger logger = LoggerFactory.getLogger(UtilRestController.class);

	private String defaultCutoffTime = "18:00";
			
	@Autowired
	private LedgerInstrumentRepository ledgerInstrumentRepository;

	@Autowired
	private LedgerPartyRepository ledgerPartyRepository;

	@GetMapping("/util/ping")
	public ResponseEntity<String> getPing(HttpServletRequest request,
			@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

		String ping = "ping " + LocalDateTime.now();

		if (authorizedClient != null) {
			ping += " token expires " + authorizedClient.getAccessToken().getExpiresAt();
		}

		return new ResponseEntity<>(ping, HttpStatus.OK);
	}

	@GetMapping("/util/instruments")
	public ResponseEntity<List<SearchInstrument>> getInstruments(HttpServletRequest request) {

		List<SearchInstrument> instruments = ledgerInstrumentRepository.getInstruments();

		return new ResponseEntity<>(instruments, HttpStatus.OK);
	}

	@GetMapping("/util/instrumentsq")
	public ResponseEntity<List<SearchInstrument>> getInstrumentsQ(HttpServletRequest request) {

		List<SearchInstrument> instruments = new ArrayList<>();

		String query = request.getParameter("q");
		if (query != null && !"".equals(query.trim())) {
			instruments.addAll(ledgerInstrumentRepository.getInstrumentsMatching(query));
		}

		return new ResponseEntity<>(instruments, HttpStatus.OK);
	}

	@PostMapping(path = "/util/agreementgen", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TradeAgreement> postAgreementGen(AgreementForm agreementForm) {

		TradeAgreement trade = new TradeAgreement();

		SearchParty myParty = ledgerPartyRepository.getParty(agreementForm.getMyParty());
		SearchParty counterparty = ledgerPartyRepository.getParty(agreementForm.getCounterparty());

		if (myParty != null && counterparty != null) {

			TransactingParties transactingParties = new TransactingParties();
			TransactingParty borrowerTransactingParty = new TransactingParty();
			borrowerTransactingParty.setPartyRole(PartyRole.BORROWER);
			borrowerTransactingParty.setParty(PartyRole.BORROWER.toString().equals(agreementForm.getPartyRole()) ? myParty.toParty() : counterparty.toParty());
			transactingParties.add(borrowerTransactingParty);

			TransactingParty lenderTransactingParty = new TransactingParty();
			lenderTransactingParty.setPartyRole(PartyRole.LENDER);
			lenderTransactingParty.setParty(PartyRole.LENDER.toString().equals(agreementForm.getPartyRole())  ? myParty.toParty() : counterparty.toParty());
			transactingParties.add(lenderTransactingParty);

			trade.setTransactingParties(transactingParties);

			Venue venue = new Venue();
			venue.setType(VenueType.OFFPLATFORM);

			VenueParties venueParties = new VenueParties();
			venue.setVenueParties(venueParties);
			
			VenueParty borrowerVenueParty = new VenueParty();
			borrowerVenueParty.setPartyRole(PartyRole.BORROWER);
			venueParties.add(borrowerVenueParty);

			VenueParty lenderVenueParty = new VenueParty();
			lenderVenueParty.setPartyRole(PartyRole.LENDER);
			venueParties.add(lenderVenueParty);

			trade.setExecutionVenue(venue);

			Instrument instrument = new Instrument();
			instrument.setTicker(agreementForm.getInstrument());

			Price price = new Price();
			price.setCurrency(CurrencyCd.USD);
			price.setUnit(PriceUnit.SHARE);
			price.setValue(100.00f);

			instrument.setPrice(price);

			trade.setInstrument(instrument);

			LocalDate tradeDate = LocalDate.now();
			
			FeeRate feeRate = new FeeRate();
			FixedRateDef fixedRateDef = new FixedRateDef();
			feeRate.setFee(fixedRateDef);
			fixedRateDef.setBaseRate(Float.parseFloat(agreementForm.getRate()));
			fixedRateDef.setCutoffTime(defaultCutoffTime);
			fixedRateDef.setEffectiveDate(tradeDate);
			fixedRateDef.setEffectiveRate(null);

			trade.setRate(feeRate);

			trade.setQuantity(new BigDecimal(agreementForm.getQuantity()));
			trade.setBillingCurrency(CurrencyCd.USD);
			trade.setDividendRatePct(100f);
			trade.setTradeDate(tradeDate);
			trade.setTermType(TermType.OPEN);
			trade.setTermDate(null);
			trade.setSettlementDate(LocalDate.now().plusDays(2));
			trade.setSettlementType(SettlementType.DVP);

			Collateral collateral = new Collateral();
			BigDecimal contractPrice = BigDecimal.valueOf(trade.getInstrument().getPrice().getValue().doubleValue() * 1.02);
			contractPrice = contractPrice.setScale(2, java.math.RoundingMode.HALF_UP);
			BigDecimal contractValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
			contractValue = contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractValue(contractValue.doubleValue());
			BigDecimal collateralValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * contractPrice.doubleValue());
			collateralValue = collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setCollateralValue(collateralValue.doubleValue());
			collateral.setCurrency(CurrencyCd.USD);
			collateral.setType(CollateralType.CASH);
			collateral.setMargin(102);

			trade.setCollateral(collateral);

			return new ResponseEntity<>(trade, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(path = "/util/contractform", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContractProposal> postContractForm(ContractProposalForm proposalForm) {

		ContractProposal contractProposal = new ContractProposal();

		SearchParty myParty = ledgerPartyRepository.getParty(proposalForm.getMyParty());
		SearchParty counterparty = ledgerPartyRepository.getParty(proposalForm.getCounterparty());

		if (myParty != null && counterparty != null) {

			TradeAgreement trade = new TradeAgreement();

			TransactingParties transactingParties = new TransactingParties();
			TransactingParty borrowerTransactingParty = new TransactingParty();
			borrowerTransactingParty.setPartyRole(PartyRole.BORROWER);
			borrowerTransactingParty.setParty(PartyRole.BORROWER.toString().equals(proposalForm.getPartyRole()) ? myParty.toParty() : counterparty.toParty());
			transactingParties.add(borrowerTransactingParty);

			TransactingParty lenderTransactingParty = new TransactingParty();
			lenderTransactingParty.setPartyRole(PartyRole.LENDER);
			lenderTransactingParty.setParty(PartyRole.LENDER.toString().equals(proposalForm.getPartyRole())  ? myParty.toParty() : counterparty.toParty());
			transactingParties.add(lenderTransactingParty);

			trade.setTransactingParties(transactingParties);

			Venue venue = new Venue();
			venue.setType(VenueType.ONPLATFORM);

			Platform platform = new Platform();
			platform.setGleifLei("X");
			platform.setLegalName("Phone Brokered");
			platform.setVenueName("Phone");
			platform.setVenueRefId("0");
			venue.setPlatform(platform);
			
			trade.setExecutionVenue(venue);

			Instrument instrument = new Instrument();
			SearchInstrument s = ledgerInstrumentRepository.getInstrument(proposalForm.getInstrument());
			if (s != null) {
				instrument.setTicker(s.getValue());
				instrument.setFigi(s.getId());
			}

			Price price = new Price();
			price.setCurrency(CurrencyCd.USD);
			price.setUnit(PriceUnit.SHARE);
			Float f = 50f;
			if (proposalForm.getPrice() != null) {
				try {
					f = Float.parseFloat(proposalForm.getPrice());
				} catch (Exception p) {
					logger.warn("Bad price: {}", proposalForm.getPrice());
				}
			}
			price.setValue(f);

			instrument.setPrice(price);

			trade.setInstrument(instrument);

			LocalDate tradeDate = LocalDate.now();
			
			Float r = 5f;
			if (proposalForm.getRate() != null) {
				try {
					r = Float.parseFloat(proposalForm.getRate());
				} catch (Exception p) {
					logger.warn("Bad rate: {}", proposalForm.getRate());
				}
			}

			CollateralType collateralType = CollateralType.CASH;
			
			if ("RFL".equals(proposalForm.getRateType())) {

				FloatingRateDef floatingRateDef = new FloatingRateDef();
				floatingRateDef.setSpread(r);
				floatingRateDef.setCutoffTime(defaultCutoffTime);
				floatingRateDef.setEffectiveDate(tradeDate);
				floatingRateDef.setEffectiveRate(null);
				floatingRateDef.setBenchmark(BenchmarkCd.fromValue(proposalForm.getBenchmark()));
				
				FloatingRate floatingRate = new FloatingRate();
				floatingRate.setFloating(floatingRateDef);
				
				RebateRate rebateRate = new RebateRate();
				rebateRate.setRebate(floatingRate);

				trade.setRate(rebateRate);

			} else if ("RFI".equals(proposalForm.getRateType())) {
				
				FixedRateDef fixedRateDef = new FixedRateDef();
				fixedRateDef.setBaseRate(r);
				fixedRateDef.setCutoffTime(defaultCutoffTime);
				fixedRateDef.setEffectiveDate(tradeDate);
				fixedRateDef.setEffectiveRate(null);

				FixedRate fixedRate = new FixedRate();
				fixedRate.setFixed(fixedRateDef);
				
				RebateRate rebateRate = new RebateRate();
				rebateRate.setRebate(fixedRate);

				trade.setRate(rebateRate);
				
			} else if ("FEE".equals(proposalForm.getRateType())) {
				FeeRate feeRate = new FeeRate();
				FixedRateDef fixedRateDef = new FixedRateDef();
				feeRate.setFee(fixedRateDef);
				fixedRateDef.setBaseRate(r);
				fixedRateDef.setCutoffTime(defaultCutoffTime);
				fixedRateDef.setEffectiveDate(tradeDate);
				fixedRateDef.setEffectiveRate(null);
				trade.setRate(feeRate);
				
				collateralType = CollateralType.NONCASH;
			}

			BigDecimal q = new BigDecimal(1000);
			if (proposalForm.getQuantity() != null) {
				try {
					q = new BigDecimal(proposalForm.getQuantity());
				} catch (Exception p) {
					logger.warn("Bad quantity: {}", proposalForm.getQuantity());
				}
			}
			
			trade.setQuantity(q);
			trade.setBillingCurrency(CurrencyCd.USD);
			trade.setDividendRatePct(100f);
			trade.setTradeDate(tradeDate);
			trade.setTermType(TermType.OPEN);
			trade.setTermDate(null);
			trade.setSettlementDate(LocalDate.now().plusDays(2));
			trade.setSettlementType(SettlementType.DVP);

			Collateral collateral = new Collateral();
			BigDecimal contractPrice = BigDecimal.valueOf(trade.getInstrument().getPrice().getValue().doubleValue() * 1.02);
			contractPrice = contractPrice.setScale(2, java.math.RoundingMode.HALF_UP);
			BigDecimal contractValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
			contractValue = contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractValue(contractValue.doubleValue());
			BigDecimal collateralValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * contractPrice.doubleValue());
			collateralValue = collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setCollateralValue(collateralValue.doubleValue());
			collateral.setCurrency(CurrencyCd.USD);
			collateral.setType(collateralType);
			collateral.setMargin(102);
			
			//only add rounding rules if proposer is the lender
			if (PartyRole.LENDER.toString().equals(proposalForm.getPartyRole())) {
				collateral.setRoundingRule(10.0f);
				collateral.setRoundingMode(RoundingMode.ALWAYSUP);
			}

			trade.setCollateral(collateral);

			contractProposal.setTrade(trade);

			PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
			partySettlementInstruction.setPartyRole(PartyRole.fromValue(proposalForm.getPartyRole()));

			SettlementInstruction instruction = new SettlementInstruction();
			partySettlementInstruction.setInstruction(instruction);

			List<LocalMarketField> localMarketFields = new ArrayList<>();

			instruction.setSettlementBic(proposalForm.getSettlmentBic());
			instruction.setLocalAgentBic(proposalForm.getLocalAgentBic());
			instruction.setLocalAgentName(proposalForm.getLocalAgentName());
			instruction.setLocalAgentAcct(proposalForm.getLocalAgentAcct());

			if (proposalForm.getLocalFieldName1() != null) {
				LocalMarketField localMarketField1 = new LocalMarketField();
				localMarketField1.setLocalFieldName(proposalForm.getLocalFieldName1());
				localMarketField1.setLocalFieldValue(proposalForm.getLocalFieldValue1());
				localMarketFields.add(localMarketField1);
			}

			if (proposalForm.getLocalFieldName2() != null) {
				LocalMarketField localMarketField2 = new LocalMarketField();
				localMarketField2.setLocalFieldName(proposalForm.getLocalFieldName2());
				localMarketField2.setLocalFieldValue(proposalForm.getLocalFieldValue2());
				localMarketFields.add(localMarketField2);
			}

			if (!localMarketFields.isEmpty()) {
				instruction.setLocalMarketFields(localMarketFields);
			}

			contractProposal.setSettlement(Collections.singletonList(partySettlementInstruction));

			return new ResponseEntity<>(contractProposal, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(path = "/util/contractproposalgen", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContractProposal> postContractProposalGen(
			@Valid @RequestBody ContractFromAgreementProposalForm proposalForm,
			@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

		ContractProposal contractProposal = new ContractProposal();

		TradeAgreement tradeAgreement = proposalForm.getTrade();
		Collateral collateral = tradeAgreement.getCollateral();
		if (collateral != null) {
			if (collateral.getMargin() == null) {
				collateral.setMargin(102);
			}
			if (collateral.getRoundingRule() == null) {
				collateral.setRoundingRule(10.0f);
			}
			if (collateral.getRoundingMode() == null) {
				collateral.setRoundingMode(RoundingMode.ALWAYSUP);
			}
		}
		contractProposal.setTrade(tradeAgreement);
		
		if (tradeAgreement.getDividendRatePct() == null) {
			tradeAgreement.setDividendRatePct(100f);
		}

		List<NameValuePair> settlmentNameValuePairs = proposalForm.getSettlement();

		PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
		partySettlementInstruction.setPartyRole(PartyRole.LENDER);

		SettlementInstruction instruction = new SettlementInstruction();
		partySettlementInstruction.setInstruction(instruction);

		List<LocalMarketField> localMarketFields = new ArrayList<>();

		LocalMarketField localMarketField1 = null;
		LocalMarketField localMarketField2 = null;

		for (NameValuePair pair : settlmentNameValuePairs) {
			if ("settlmentBic".equals(pair.getName())) {
				instruction.setSettlementBic(pair.getValue());
			} else if ("localAgentBic".equals(pair.getName())) {
				instruction.setLocalAgentBic(pair.getValue());
			} else if ("localAgentName".equals(pair.getName())) {
				instruction.setLocalAgentName(pair.getValue());
			} else if ("localAgentAcct".equals(pair.getName())) {
				instruction.setLocalAgentAcct(pair.getValue());
			} else if ("localFieldName1".equals(pair.getName())) {
				if (localMarketField1 == null) {
					localMarketField1 = new LocalMarketField();
					localMarketFields.add(localMarketField1);
				}
				localMarketField1.setLocalFieldName(pair.getValue());
			} else if ("localFieldValue1".equals(pair.getName())) {
				if (localMarketField1 == null) {
					localMarketField1 = new LocalMarketField();
					localMarketFields.add(localMarketField1);
				}
				localMarketField1.setLocalFieldValue(pair.getValue());
			} else if ("localFieldName2".equals(pair.getName())) {
				if (localMarketField2 == null) {
					localMarketField2 = new LocalMarketField();
					localMarketFields.add(localMarketField2);
				}
				localMarketField2.setLocalFieldName(pair.getValue());
			} else if ("localFieldValue2".equals(pair.getName())) {
				if (localMarketField2 == null) {
					localMarketField2 = new LocalMarketField();
					localMarketFields.add(localMarketField2);
				}
				localMarketField2.setLocalFieldValue(pair.getValue());
			}
		}

		if (!localMarketFields.isEmpty()) {
			instruction.setLocalMarketFields(localMarketFields);
		}

		contractProposal.setSettlement(Collections.singletonList(partySettlementInstruction));

		return new ResponseEntity<>(contractProposal, HttpStatus.OK);

	}

	@PostMapping(path = "/util/acceptform", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementInstructionUpdate> postAcceptForm(AcceptContractForm acceptForm) {

		SettlementInstructionUpdate settlementInstructionUpdate = new SettlementInstructionUpdate();

		SearchParty myParty = ledgerPartyRepository.getParty(acceptForm.getMyParty());
		SearchParty counterparty = ledgerPartyRepository.getParty(acceptForm.getCounterparty());

		if (myParty != null && counterparty != null) {

			PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
			partySettlementInstruction.setPartyRole(PartyRole.fromValue(acceptForm.getPartyRole()));

			//only add rounding rules if proposer is the lender
			if (PartyRole.LENDER.toString().equals(acceptForm.getPartyRole())) {
				settlementInstructionUpdate.setRoundingRule(10);
				settlementInstructionUpdate.setRoundingMode(RoundingMode.ALWAYSUP);
			}

			SettlementInstruction instruction = new SettlementInstruction();
			partySettlementInstruction.setInstruction(instruction);

			List<LocalMarketField> localMarketFields = new ArrayList<>();

			instruction.setSettlementBic(acceptForm.getSettlmentBic());
			instruction.setLocalAgentBic(acceptForm.getLocalAgentBic());
			instruction.setLocalAgentName(acceptForm.getLocalAgentName());
			instruction.setLocalAgentAcct(acceptForm.getLocalAgentAcct());

			if (acceptForm.getLocalFieldName1() != null) {
				LocalMarketField localMarketField1 = new LocalMarketField();
				localMarketField1.setLocalFieldName(acceptForm.getLocalFieldName1());
				localMarketField1.setLocalFieldValue(acceptForm.getLocalFieldValue1());
				localMarketFields.add(localMarketField1);
			}

			if (acceptForm.getLocalFieldName2() != null) {
				LocalMarketField localMarketField2 = new LocalMarketField();
				localMarketField2.setLocalFieldName(acceptForm.getLocalFieldName2());
				localMarketField2.setLocalFieldValue(acceptForm.getLocalFieldValue2());
				localMarketFields.add(localMarketField2);
			}

			if (!localMarketFields.isEmpty()) {
				instruction.setLocalMarketFields(localMarketFields);
			}

			settlementInstructionUpdate.setSettlement(partySettlementInstruction);

			return new ResponseEntity<>(settlementInstructionUpdate, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}