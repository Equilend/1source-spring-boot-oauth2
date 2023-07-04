package com.os.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.AgreementForm;
import com.os.api.SearchInstrument;
import com.os.util.LedgerInstrumentRepository;
import com.os.util.LedgerPartyRepository;
import com.os.api.model.*;

@RestController
public class UtilRestController {

	@Autowired
	private LedgerInstrumentRepository ledgerInstrumentRepository;

	@Autowired
	private LedgerPartyRepository ledgerPartyRepository;

	@GetMapping("/util/instruments")
	public ResponseEntity<List<SearchInstrument>> getInstruments(HttpServletRequest request) {

		List<SearchInstrument> instruments = new ArrayList<>();

		String query = request.getParameter("q");
		if (query != null && !"".equals(query.trim())) {
			instruments.addAll(ledgerInstrumentRepository.getInstrumentsMatching(query));
		}

		return new ResponseEntity<List<SearchInstrument>>(instruments, HttpStatus.OK);
	}

	@PostMapping(path = "/util/agreementgen", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TradeAgreement> postAgreementGen(AgreementForm agreementForm) {

		TradeAgreement trade = new TradeAgreement();

		Party myParty = ledgerPartyRepository.getParty(agreementForm.getMyParty());
		Party counterparty = ledgerPartyRepository.getParty(agreementForm.getCounterparty());
		PartyRole myPartyRole = PartyRole.fromValue(agreementForm.getDirection());

		if (myParty != null && counterparty != null && myPartyRole != null) {
			
			TransactingParties transactingParties = new TransactingParties();
			TransactingParty borrowerTransactingParty = new TransactingParty();
			borrowerTransactingParty.setPartyRole(PartyRole.BORROWER);
			borrowerTransactingParty.setParty(myPartyRole.equals(PartyRole.BORROWER) ? myParty : counterparty);
			transactingParties.add(borrowerTransactingParty);

			TransactingParty lenderTransactingParty = new TransactingParty();
			lenderTransactingParty.setPartyRole(PartyRole.LENDER);
			lenderTransactingParty.setParty(myPartyRole.equals(PartyRole.LENDER) ? myParty : counterparty);
			transactingParties.add(lenderTransactingParty);

			trade.setTransactingParties(transactingParties);

			Venue venue = new Venue();
			venue.setType(VenueType.OFFPLATFORM);

			trade.setExecutionVenue(venue);

			Instrument instrument = new Instrument();
			instrument.setTicker(agreementForm.getInstrument());

			Price price = new Price();
			price.setCurrency(CurrencyCd.USD);
			price.setUnit(PriceUnit.SHARE);
			price.setValue(100.00f);

			instrument.setPrice(price);

			trade.setInstrument(instrument);

			Rate rate = new Rate();
			rate.setRebateBps(Float.parseFloat(agreementForm.getRate()));
			rate.setRebateSreadBps(null);
			rate.setBenchmarkCd(null);
			rate.setFeeBps(null);

			trade.setRate(rate);

			trade.setQuantity(new BigDecimal(agreementForm.getQuantity()));
			trade.setBillingCurrency(CurrencyCd.USD);
			trade.setDividendRatePct(100f);
			trade.setTradeDate(LocalDate.now());
			trade.setTermType(TermType.OPEN);
			trade.setTermDate(null);
			trade.setSettlementDate(LocalDate.now().plusDays(2));
			trade.setSettlementType(SettlementType.DVP);

			Collateral collateral = new Collateral();
			BigDecimal contractPrice = new BigDecimal(trade.getInstrument().getPrice().getValue().doubleValue() * 1.02);
			contractPrice.setScale(2, java.math.RoundingMode.HALF_UP);
			BigDecimal contractValue = new BigDecimal(
					trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
			contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setContractValue(contractValue.doubleValue());
			BigDecimal collateralValue = new BigDecimal(
					trade.getQuantity().doubleValue() * contractPrice.doubleValue());
			collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
			collateral.setCollateralValue(collateralValue.doubleValue());
			collateral.setCurrency(CurrencyCd.USD);
			collateral.setType(CollateralType.CASH);
			collateral.setMargin(102);

			trade.setCollateral(collateral);

			return new ResponseEntity<TradeAgreement>(trade, HttpStatus.OK);
		}

		return new ResponseEntity<TradeAgreement>(HttpStatus.BAD_REQUEST);

	}

}