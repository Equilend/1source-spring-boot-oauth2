package com.os.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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
import com.os.api.RerateContractProposalForm;
import com.os.api.SearchInstrument;
import com.os.api.SearchParty;
import com.os.util.LedgerInstrumentRepository;
import com.os.util.LedgerPartyRepository;

import io.swagger.client.model.*;

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
			borrowerTransactingParty
					.setParty(PartyRole.BORROWER.toString().equals(agreementForm.getPartyRole()) ? myParty.toParty()
							: counterparty.toParty());
			transactingParties.add(borrowerTransactingParty);

			InternalReference borrowerInternalRef = new InternalReference();
			borrowerInternalRef.setAccountId(null);
			borrowerInternalRef.setBrokerCd(null);
			borrowerInternalRef.setInternalRefId(PartyRole.BORROWER.toString().equals(agreementForm.getPartyRole()) ? agreementForm.getInternalRefId() : null);
			
			borrowerTransactingParty.setInternalRef(borrowerInternalRef);

			TransactingParty lenderTransactingParty = new TransactingParty();
			lenderTransactingParty.setPartyRole(PartyRole.LENDER);
			lenderTransactingParty
					.setParty(PartyRole.LENDER.toString().equals(agreementForm.getPartyRole()) ? myParty.toParty()
							: counterparty.toParty());
			transactingParties.add(lenderTransactingParty);

			InternalReference lenderInternalRef = new InternalReference();
			lenderInternalRef.setAccountId(null);
			lenderInternalRef.setBrokerCd(null);
			lenderInternalRef.setInternalRefId(PartyRole.LENDER.toString().equals(agreementForm.getPartyRole()) ? agreementForm.getInternalRefId() : null);
			
			lenderTransactingParty.setInternalRef(lenderInternalRef);

			trade.setTransactingParties(transactingParties);

			Venues venues = new Venues();
			
			Venue venue = new Venue();
			venue.setType(VenueType.OFFPLATFORM);
			venue.setPartyId(agreementForm.getVenueParty());
			venue.setVenueName(agreementForm.getVenueName());
			venue.setVenueRefKey(agreementForm.getVenueRefKey());
			venue.setTransactionDatetime(OffsetDateTime.now());
			
			VenueParties venueParties = new VenueParties();
			venue.setVenueParties(venueParties);

			if (PartyRole.BORROWER.toString().equals(agreementForm.getPartyRole())) {
				VenueParty borrowerVenueParty = new VenueParty();
				borrowerVenueParty.setPartyRole(PartyRole.BORROWER);
				venueParties.add(borrowerVenueParty);

			} else if (PartyRole.LENDER.toString().equals(agreementForm.getPartyRole())) {

				VenueParty lenderVenueParty = new VenueParty();
				lenderVenueParty.setPartyRole(PartyRole.LENDER);
				venueParties.add(lenderVenueParty);
			}

			venues.add(venue);
			
			trade.setVenues(venues);

			Instrument instrument = new Instrument();
			instrument.setTicker(agreementForm.getInstrument());

			Price price = new Price();
			price.setCurrency(CurrencyCd.USD);
			price.setUnit(PriceUnit.SHARE);
			price.setValue(100.00d);

			instrument.setPrice(price);

			trade.setInstrument(instrument);

			LocalDate tradeDate = LocalDate.now();

			FeeRate feeRate = new FeeRate();
			FixedRateDef fixedRateDef = new FixedRateDef();
			feeRate.setFee(fixedRateDef);
			fixedRateDef.setBaseRate(Double.parseDouble(agreementForm.getRate()));
			fixedRateDef.setCutoffTime(defaultCutoffTime);
			fixedRateDef.setEffectiveDate(tradeDate);
			fixedRateDef.setEffectiveRate(null);

			trade.setRate(feeRate);

			trade.setQuantity(new BigDecimal(agreementForm.getQuantity()));
			trade.setBillingCurrency(CurrencyCd.USD);
			trade.setDividendRatePct(100d);
			trade.setTradeDate(tradeDate);
			trade.setTermType(TermType.OPEN);
			trade.setTermDate(null);
			trade.setSettlementDate(LocalDate.now().plusDays(2));
			trade.setSettlementType(SettlementType.DVP);

			Collateral collateral = new Collateral();
			BigDecimal contractPrice = BigDecimal
					.valueOf(trade.getInstrument().getPrice().getValue().doubleValue() * 1.02);
			contractPrice = contractPrice.setScale(2, java.math.RoundingMode.HALF_UP);
			BigDecimal contractValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
			contractValue = contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractValue(contractValue.doubleValue());
			BigDecimal collateralValue = BigDecimal
					.valueOf(trade.getQuantity().doubleValue() * contractPrice.doubleValue());
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
			borrowerTransactingParty
					.setParty(PartyRole.BORROWER.toString().equals(proposalForm.getPartyRole()) ? myParty.toParty()
							: counterparty.toParty());
			transactingParties.add(borrowerTransactingParty);

			InternalReference borrowerInternalRef = new InternalReference();
			borrowerInternalRef.setAccountId(null);
			borrowerInternalRef.setBrokerCd(null);
			borrowerInternalRef.setInternalRefId(PartyRole.BORROWER.toString().equals(proposalForm.getPartyRole()) ? proposalForm.getInternalRefId() : null);
			
			borrowerTransactingParty.setInternalRef(borrowerInternalRef);

			TransactingParty lenderTransactingParty = new TransactingParty();
			lenderTransactingParty.setPartyRole(PartyRole.LENDER);
			lenderTransactingParty
					.setParty(PartyRole.LENDER.toString().equals(proposalForm.getPartyRole()) ? myParty.toParty()
							: counterparty.toParty());
			transactingParties.add(lenderTransactingParty);

			InternalReference lenderInternalRef = new InternalReference();
			lenderInternalRef.setAccountId(null);
			lenderInternalRef.setBrokerCd(null);
			lenderInternalRef.setInternalRefId(PartyRole.LENDER.toString().equals(proposalForm.getPartyRole()) ? proposalForm.getInternalRefId() : null);
			
			lenderTransactingParty.setInternalRef(lenderInternalRef);

			trade.setTransactingParties(transactingParties);

			Venues venues = new Venues();
			
			Venue venue = new Venue();
			venue.setType(VenueType.OFFPLATFORM);
			venue.setPartyId(proposalForm.getVenueParty());
			venue.setVenueName(proposalForm.getVenueName());
			venue.setVenueRefKey(proposalForm.getVenueRefKey());
			venue.setTransactionDatetime(OffsetDateTime.now());

			VenueParties venueParties = new VenueParties();
			venue.setVenueParties(venueParties);

			if (PartyRole.BORROWER.toString().equals(proposalForm.getPartyRole())) {

				VenueParty borrowerVenueParty = new VenueParty();
				borrowerVenueParty.setPartyRole(PartyRole.BORROWER);
				venueParties.add(borrowerVenueParty);

			} else if (PartyRole.LENDER.toString().equals(proposalForm.getPartyRole())) {

				VenueParty lenderVenueParty = new VenueParty();
				lenderVenueParty.setPartyRole(PartyRole.LENDER);
				venueParties.add(lenderVenueParty);

			}

			venues.add(venue);
			
			trade.setVenues(venues);

			Instrument instrument = new Instrument();
			SearchInstrument s = ledgerInstrumentRepository.getInstrument(proposalForm.getInstrument());
			if (s != null) {
				instrument.setTicker(s.getValue());
				instrument.setFigi(s.getId());
			}

			Price price = new Price();
			price.setCurrency(CurrencyCd.USD);
			price.setUnit(PriceUnit.SHARE);
			Double f = 50d;
			if (proposalForm.getPrice() != null) {
				try {
					f = Double.parseDouble(proposalForm.getPrice());
				} catch (Exception p) {
					logger.warn("Bad price: {}", proposalForm.getPrice());
				}
			}
			price.setValue(f);

			instrument.setPrice(price);

			trade.setInstrument(instrument);

			LocalDate tradeDate = LocalDate.now();

			Double r = 5d;
			if (proposalForm.getRate() != null) {
				try {
					r = Double.parseDouble(proposalForm.getRate());
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
				floatingRateDef.setIsAutoRerate("YES".equals(proposalForm.getAutoRerate()));

				if (!floatingRateDef.isIsAutoRerate()) {
					Double b = 5d;
					if (proposalForm.getBaseRate() != null) {
						try {
							b = Double.parseDouble(proposalForm.getBaseRate());
							floatingRateDef.setBaseRate(b);
						} catch (Exception p) {
							logger.warn("Bad base rate: {}", proposalForm.getBaseRate());
						}
					}
				}

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
			trade.setDividendRatePct(100d);
			trade.setTradeDate(tradeDate);
			trade.setTermType(TermType.OPEN);
			trade.setTermDate(null);
			trade.setSettlementDate(LocalDate.now().plusDays(2));
			trade.setSettlementType(SettlementType.DVP);

			Collateral collateral = new Collateral();
			BigDecimal contractPrice = BigDecimal
					.valueOf(trade.getInstrument().getPrice().getValue().doubleValue() * 1.02);
			contractPrice = contractPrice.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractPrice(contractPrice.doubleValue());
			BigDecimal contractValue = BigDecimal.valueOf(
					trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
			contractValue = contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractValue(contractValue.doubleValue());
			BigDecimal collateralValue = BigDecimal
					.valueOf(trade.getQuantity().doubleValue() * contractPrice.doubleValue());
			collateralValue = collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setCollateralValue(collateralValue.doubleValue());
			collateral.setCurrency(CurrencyCd.USD);
			collateral.setType(collateralType);
			collateral.setMargin(102);

			// only add rounding rules if proposer is the lender
			if (PartyRole.LENDER.toString().equals(proposalForm.getPartyRole())) {
				collateral.setRoundingRule(10.0d);
				collateral.setRoundingMode(RoundingMode.ALWAYSUP);
			}

			trade.setCollateral(collateral);

			contractProposal.setTrade(trade);

			PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
			partySettlementInstruction.setPartyRole(PartyRole.fromValue(proposalForm.getPartyRole()));
			partySettlementInstruction
					.setSettlementStatus(SettlementStatus.fromValue(proposalForm.getSettlementStatus()));
			partySettlementInstruction.setInternalAcctCd(proposalForm.getInternalAcctCd());

			SettlementInstruction instruction = new SettlementInstruction();
			partySettlementInstruction.setInstruction(instruction);
			instruction.setSettlementBic(proposalForm.getSettlmentBic());
			instruction.setLocalAgentBic(proposalForm.getLocalAgentBic());
			instruction.setLocalAgentName(proposalForm.getLocalAgentName());
			instruction.setLocalAgentAcct(proposalForm.getLocalAgentAcct());
			instruction.setDtcParticipantNumber(proposalForm.getDtcParticipantNumber());
			instruction.setCdsCustomerUnitId(proposalForm.getCdsCustomerUnitId());

			partySettlementInstruction.setInternalAcctCd(proposalForm.getInternalAcctCd());
			
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

		List<SearchParty> myParties = ledgerPartyRepository.getPartiesByUser(authorizedClient.getPrincipalName());

		ContractProposal contractProposal = new ContractProposal();
		VenueTradeAgreement tradeAgreement = proposalForm.getTrade();

		// Reset all dates to now() to handle proposals from old agreements
		tradeAgreement.setTradeDate(LocalDate.now());
		tradeAgreement.setSettlementDate(LocalDate.now());

		OneOfVenueTradeAgreementRate rate = tradeAgreement.getRate();
		if (rate instanceof FeeRate) {
			FeeRate fee = (FeeRate) rate;
			fee.getFee().setEffectiveDate(LocalDate.now());
		} else if (rate instanceof RebateRate) {
			RebateRate rebate = (RebateRate) rate;
			OneOfRebateRateRebate rebateRate = rebate.getRebate();
			if (rebateRate instanceof FloatingRate) {
				FloatingRate floatingRate = (FloatingRate) rebateRate;
				floatingRate.getFloating().setEffectiveDate(LocalDate.now());
			} else if (rebateRate instanceof FixedRate) {
				FixedRate fixedRate = (FixedRate) rebateRate;
				fixedRate.getFixed().setEffectiveDate(LocalDate.now());
			}
		}

		TransactingParty myParty = null;
		for (TransactingParty t : tradeAgreement.getTransactingParties()) {
			if (myParties.contains(ledgerPartyRepository.getParty(t.getParty().getPartyId()))) {
				myParty = t;
				break;
			}
		}

		if (myParty == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (myParty.getInternalRef() == null || myParty.getInternalRef().getInternalRefId() == null) {
			InternalReference internalRef = new InternalReference();
			internalRef.setInternalRefId("UNKNOWN-" + (PartyRole.BORROWER.equals(myParty.getPartyRole()) ? "B" : "L"));
			myParty.setInternalRef(internalRef);
		}

		Collateral collateral = tradeAgreement.getCollateral();
		if (collateral != null) {
			if (collateral.getMargin() == null) {
				collateral.setMargin(102);
			}

			if (PartyRole.LENDER.equals(myParty.getPartyRole())) {
				if (collateral.getRoundingRule() == null) {
					collateral.setRoundingRule(10.0d);
				}
				if (collateral.getRoundingMode() == null) {
					collateral.setRoundingMode(RoundingMode.ALWAYSUP);
				}
			}
		}
		
		//TODO - remove once we onboard the actual venue parties and set them properly
		tradeAgreement.getExecutionVenue().setPartyId(myParty.getParty().getPartyId());
		
		contractProposal.setTrade(upgradeVenueTradeAgreement(tradeAgreement));

		if (tradeAgreement.getDividendRatePct() == null) {
			tradeAgreement.setDividendRatePct(100d);
		}

		List<NameValuePair> settlmentNameValuePairs = proposalForm.getSettlement();

		PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
		partySettlementInstruction.setPartyRole(myParty.getPartyRole());

		SettlementInstruction instruction = new SettlementInstruction();
		partySettlementInstruction.setInstruction(instruction);

		for (NameValuePair pair : settlmentNameValuePairs) {
			if ("settlmentBic".equals(pair.getName())) {
				instruction.setSettlementBic(pair.getValue());
			} else if ("localAgentBic".equals(pair.getName())) {
				instruction.setLocalAgentBic(pair.getValue());
			} else if ("localAgentName".equals(pair.getName())) {
				instruction.setLocalAgentName(pair.getValue());
			} else if ("localAgentAcct".equals(pair.getName())) {
				instruction.setLocalAgentAcct(pair.getValue());
			} else if ("dtcParticipantNumber".equals(pair.getName())) {
				instruction.setDtcParticipantNumber(pair.getValue());
			} else if ("cdsCustomerUnitId".equals(pair.getName())) {
				instruction.setCdsCustomerUnitId(pair.getValue());
			} else if ("settlementStatus".equals(pair.getName())) {
				partySettlementInstruction.setSettlementStatus(SettlementStatus.fromValue(pair.getValue()));
			} else if ("internalAcctCd".equals(pair.getName())) {
				partySettlementInstruction.setInternalAcctCd(pair.getValue());
			}
		}

		contractProposal.setSettlement(Collections.singletonList(partySettlementInstruction));

		return new ResponseEntity<>(contractProposal, HttpStatus.OK);

	}

	@PostMapping(path = "/util/acceptform", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContractProposalApproval> postAcceptForm(AcceptContractForm acceptForm) {

		ContractProposalApproval contractProposalApproval = new ContractProposalApproval();

		SearchParty myParty = ledgerPartyRepository.getParty(acceptForm.getMyParty());
		SearchParty counterparty = ledgerPartyRepository.getParty(acceptForm.getCounterparty());

		if (myParty != null && counterparty != null) {

			PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
			partySettlementInstruction.setPartyRole(PartyRole.fromValue(acceptForm.getPartyRole()));
			partySettlementInstruction
					.setSettlementStatus(SettlementStatus.fromValue(acceptForm.getSettlementStatus()));
			partySettlementInstruction.setInternalAcctCd(acceptForm.getInternalAcctCd());

			contractProposalApproval.setInternalRefId(acceptForm.getInternalRefId());
			
			// only add rounding rules if proposer is the lender
			if (PartyRole.LENDER.toString().equals(acceptForm.getPartyRole())) {
				contractProposalApproval.setRoundingRule(10d);
				contractProposalApproval.setRoundingMode(RoundingMode.ALWAYSUP);
			}

			SettlementInstruction instruction = new SettlementInstruction();
			partySettlementInstruction.setInstruction(instruction);

			instruction.setSettlementBic(acceptForm.getSettlmentBic());
			instruction.setLocalAgentBic(acceptForm.getLocalAgentBic());
			instruction.setLocalAgentName(acceptForm.getLocalAgentName());
			instruction.setLocalAgentAcct(acceptForm.getLocalAgentAcct());
			instruction.setDtcParticipantNumber(acceptForm.getDtcParticipantNumber());
			instruction.setCdsCustomerUnitId(acceptForm.getCdsCustomerUnitId());
			
			partySettlementInstruction.setInternalAcctCd(acceptForm.getInternalAcctCd());

			contractProposalApproval.setSettlement(partySettlementInstruction);

			return new ResponseEntity<>(contractProposalApproval, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(path = "/util/reratecontractform", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RerateProposal> postRerateContractForm(RerateContractProposalForm rerateProposalForm) {

		RerateProposal rerateProposal = new RerateProposal();

		String contractId = rerateProposalForm.getContractId();
		SearchParty myParty = ledgerPartyRepository.getParty(rerateProposalForm.getMyParty());
		SearchParty counterparty = ledgerPartyRepository.getParty(rerateProposalForm.getCounterparty());

		if (contractId != null && myParty != null && counterparty != null) {

			Double r = 5d;
			if (rerateProposalForm.getRate() != null) {
				try {
					r = Double.parseDouble(rerateProposalForm.getRate());
				} catch (Exception p) {
					logger.warn("Bad rate: {}", rerateProposalForm.getRate());
				}
			}

			Double br = 5d;
			if (rerateProposalForm.getBaseRate() != null) {
				try {
					br = Double.parseDouble(rerateProposalForm.getBaseRate());
				} catch (Exception p) {
					logger.warn("Bad base rate: {}", rerateProposalForm.getBaseRate());
				}
			}

			LocalDate rerateDate = LocalDate.now();
			if ("RFL".equals(rerateProposalForm.getRateType())) {

				FloatingRateDef floatingRateDef = new FloatingRateDef();
				floatingRateDef.setSpread(r);
				floatingRateDef.setCutoffTime(defaultCutoffTime);
				floatingRateDef.setEffectiveDate(rerateDate);
				floatingRateDef.setEffectiveRate(null);
				floatingRateDef.setBenchmark(BenchmarkCd.fromValue(rerateProposalForm.getBenchmark()));
				floatingRateDef.setIsAutoRerate("YES".equals(rerateProposalForm.getAutoRerate()));

				if (!floatingRateDef.isIsAutoRerate()) {
					floatingRateDef.setBaseRate(br);
				}

				FloatingRate floatingRate = new FloatingRate();
				floatingRate.setFloating(floatingRateDef);

				RebateRate rebateRate = new RebateRate();
				rebateRate.setRebate(floatingRate);

				rerateProposal.setRate(rebateRate);

			} else if ("RFI".equals(rerateProposalForm.getRateType())) {

				FixedRateDef fixedRateDef = new FixedRateDef();
				fixedRateDef.setBaseRate(r);
				fixedRateDef.setCutoffTime(defaultCutoffTime);
				fixedRateDef.setEffectiveDate(rerateDate);
				fixedRateDef.setEffectiveRate(null);

				FixedRate fixedRate = new FixedRate();
				fixedRate.setFixed(fixedRateDef);

				RebateRate rebateRate = new RebateRate();
				rebateRate.setRebate(fixedRate);

				rerateProposal.setRate(rebateRate);

			} else if ("FEE".equals(rerateProposalForm.getRateType())) {
				FeeRate feeRate = new FeeRate();
				FixedRateDef fixedRateDef = new FixedRateDef();
				feeRate.setFee(fixedRateDef);
				fixedRateDef.setBaseRate(r);
				fixedRateDef.setCutoffTime(defaultCutoffTime);
				fixedRateDef.setEffectiveDate(rerateDate);
				fixedRateDef.setEffectiveRate(null);
				rerateProposal.setRate(feeRate);
			}

			return new ResponseEntity<>(rerateProposal, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	private TradeAgreement upgradeVenueTradeAgreement(VenueTradeAgreement venueTradeAgreement) {
		
		TradeAgreement tradeAgreement = new TradeAgreement();
		
		tradeAgreement.setBillingCurrency(venueTradeAgreement.getBillingCurrency());
		tradeAgreement.setCollateral(venueTradeAgreement.getCollateral());
		tradeAgreement.setDividendRatePct(venueTradeAgreement.getDividendRatePct());
		tradeAgreement.setInstrument(venueTradeAgreement.getInstrument());
		tradeAgreement.setQuantity(venueTradeAgreement.getQuantity());
		tradeAgreement.setRate(venueTradeAgreement.getRate() instanceof RebateRate ? (RebateRate)venueTradeAgreement.getRate() : (FeeRate)venueTradeAgreement.getRate());
		tradeAgreement.setSettlementDate(venueTradeAgreement.getSettlementDate());
		tradeAgreement.setSettlementType(venueTradeAgreement.getSettlementType());
		tradeAgreement.setTermDate(venueTradeAgreement.getTermDate());
		tradeAgreement.setTermType(venueTradeAgreement.getTermType());
		tradeAgreement.settlementDate(venueTradeAgreement.getSettlementDate());
		tradeAgreement.settlementType(venueTradeAgreement.getSettlementType());
		tradeAgreement.setTradeDate(venueTradeAgreement.getTradeDate());
		tradeAgreement.setTransactingParties(venueTradeAgreement.getTransactingParties());
		Venues venues = new Venues();
		venues.add(venueTradeAgreement.getExecutionVenue());
		tradeAgreement.setVenues(venues);

		return tradeAgreement;
	}
	
}