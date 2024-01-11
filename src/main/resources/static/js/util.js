var table;
var data;
//var apiserver = 'http://demoapi.1sourceeq.com';
//var apiserver = 'https://stageapi.equilend.com';
//var apiserver = 'http://localhost:8080';
var apiserver = '';

function ping() {

	$.ajax({
		type: 'GET',
		url: apiserver + '/util/ping',
		async: true,
		success: function(j) {
			setTimeout(ping, 1000 * 30);
		}
	});
}

function expandToWindow(element, margin) {
	if (element.height() < window.innerHeight) {
		element.height(window.innerHeight - (margin));
	}
}

function loadSummary() {

	localStorage.clear();

	$("#summaryOC").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});
	$("#summaryPC").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});
	$("#summaryPSC").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});
	$("#summaryPR").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});
	$("#summaryPER").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	loadContractStore().then((data) => {
		$('#summaryOC').text(localStorage.getItem('storeOpenSize'));
		$("#summaryOC").LoadingOverlay("hide", true);
		$('#summaryPC').text(localStorage.getItem('storeProposedSize'));
		$("#summaryPC").LoadingOverlay("hide", true);
		$('#summaryPSC').text(localStorage.getItem('storePendingSize'));
		$("#summaryPSC").LoadingOverlay("hide", true);

		$('#summaryOC6').text(localStorage.getItem('storeOpenSize6hr'));
		$('#summaryPC6').text(localStorage.getItem('storeProposedSize6hr'));
		$('#summaryPSC6').text(localStorage.getItem('storePendingSize6hr'));

		$('#summaryOC24').text(localStorage.getItem('storeOpenSize24hr'));
		$('#summaryPC24').text(localStorage.getItem('storeProposedSize24hr'));
		$('#summaryPSC24').text(localStorage.getItem('storePendingSize24hr'));

		$('#summaryOC1d').text(localStorage.getItem('storeOpenSize1d'));
		$('#summaryPC1d').text(localStorage.getItem('storeProposedSize1d'));
		$('#summaryPSC1d').text(localStorage.getItem('storePendingSize1d'));

		$('#summaryOC3d').text(localStorage.getItem('storeOpenSize3d'));
		$('#summaryPC3d').text(localStorage.getItem('storeProposedSize3d'));
		$('#summaryPSC3d').text(localStorage.getItem('storePendingSize3d'));

	});

	loadRerateStore().then((data) => {
		$('#summaryPR').text(localStorage.getItem('storeRerateProposedSize'));
		$("#summaryPR").LoadingOverlay("hide", true);
		$('#summaryPER').text(localStorage.getItem('storeReratePendingSize'));
		$("#summaryPER").LoadingOverlay("hide", true);

		$('#summaryPR6').text(localStorage.getItem('storeRerateProposedSize6hr'));
		$('#summaryPER6').text(localStorage.getItem('storeReratePendingSize6hr'));

		$('#summaryPR24').text(localStorage.getItem('storeRerateProposedSize24hr'));
		$('#summaryPER24').text(localStorage.getItem('storeReratePendingSize24hr'));

		$('#summaryPR1d').text(localStorage.getItem('storeRerateProposedSize1d'));
		$('#summaryPER1d').text(localStorage.getItem('storeReratePendingSize1d'));

		$('#summaryPR3d').text(localStorage.getItem('storeRerateProposedSize3d'));
		$('#summaryPER3d').text(localStorage.getItem('storeReratePendingSize3d'));

	});

}

function loadData(parties, uri, dFunction, pFunction) {

	table = new google.visualization.Table(document.getElementById('table_div'));
	google.visualization.events.addListener(table, 'page', pageTable);

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		data: pFunction(),
		async: true,
		success: function(j) {
			$("#table_div").LoadingOverlay("hide", true);
			if (j == null || j.length == 0) {
				setTimeout(function() {
					table.draw(noResultData(), { allowHtml: true, showRowNumber: false, width: '100%', height: '95%', page: 'disable' });
				}, 200);
			} else {
				data = dFunction(j, JSON.parse(parties));
				setTimeout(function() {
					successData(table, data);
				}, 200);
			}
		},
		error: function(xhr, ajaxOptions, thrownError) {
			$("#table_div").LoadingOverlay("hide", true);
			if (xhr.status == 404) {
				table.draw(noResultData(), { allowHtml: true, showRowNumber: false, width: '100%', height: '95%', page: 'disable' });
			} else {
				table.draw(errorData(), { allowHtml: true, showRowNumber: false, width: '100%', height: '95%', page: 'disable' });
			}
		}
	});
}

var lastEventId;

function successData(table, data) {
	table.draw(data, { allowHtml: true, showRowNumber: true, width: '100%', height: '95%', page: 'enable', pageSize: 10 });
}

function errorData() {
	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addRow(['An unexpected error occurred. Contact support or try again later.']);
	return d;
}

function noResultData() {
	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addRow(['No results found. Change query or try again later.']);
	return d;
}

function partyData(j, parties) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Party ID');
	d.addColumn('string', 'LEI');

	var rowIdx = 0;
	for (var i = 0; i < j.length; i++) {

		var canSub = false;

		for (var p = 0; p < parties.length; p++) {
			if (parties[p].partyId != j[i].partyId) {
				canSub = true;
				break;
			}
		}

		var btns = '';
		if (canSub) {
			btns += '<input type="button" value="Propose Contract" onclick="createContractShell(' + rowIdx + ', 1, \'/v1/ledger/agreements/\');return false;"/>';
		}
		d.addRow([{ v: 'ButtonName', f: btns }
			, j[i].partyId
			, j[i].gleifLei]);

		rowIdx++;
	}

	return d;
}

function partyParams() {

	var params = { 'noCache': new Date().getTime() };

	return params;
}

function createContractShell(rowIndx, clickIndx, clickUriPrefix) {

	document.getElementById("modal02").style.display = "block";

	var partyObj = JSON.parse(parties);


	$('#sMyParty').empty();

	for (var p = 0; p < partyObj.length; p++) {
		$('#sMyParty').append(
			$('<option></option>')
				.val(partyObj[p].partyId)
				.html(partyObj[p].partyId));
	}

	$('#cCounterparty').text(data.getFormattedValue(rowIndx, clickIndx));
	$('#hCounterparty').val(data.getFormattedValue(rowIndx, clickIndx));

}

function validateProposal(frm) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	$.ajax({
		type: 'POST',
		url: "/util/contractform",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: frm.serialize(),
		dataType: "json",
		async: false,
		success: function(j) {
			proposeContract(j);
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});

}

function postAgreement(agreement) {
	var postUri = '/v1/ledger/agreements';

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		data: JSON.stringify(agreement),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		success: function(j) {
			$('#cDialogText').text('Agreement created!');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success'
			});
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});
}

function eventData(j, parties) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Event ID');
	d.addColumn('string', 'Event Type');
	d.addColumn('datetime', 'Timestamp');
	d.addColumn('string', 'URI');

	for (var i = 0; i < j.length; i++) {
		d.addRow([{ v: 'ButtonName', f: '<input type="button" value="Json" onclick="showJson(' + i + ', 4, \'\');return false;"/>' }
			, j[i].eventId
			, j[i].eventType
			, new Date(Date.parse(j[i].eventDateTime))
			, j[i].resourceUri]);
		lastEventId = j[i].eventId;
	}

	toggleLastEventId();

	return d;
}

function toggleLastEventId() {
	if ($('#cUseLastEventId').prop('checked')) {
		$('#tLastEventId').val(lastEventId);
	} else {
		$('#tLastEventId').val('');
	}
}

function eventParams() {

	var params = { 'noCache': new Date().getTime() };
	params['size'] = 1000;

	var eventType = $("#sEventType").val();
	if (eventType != '_') {
		params['eventType'] = eventType;
	}

	var minutesSince = $("#sTimeSince").val();

	if (minutesSince == '_') {
		var sinceDatetime = new Date();
		sinceDatetime.setUTCHours(0, 0, 0, 0);
		params['since'] = toIsoString(sinceDatetime);
	} else if (minutesSince && minutesSince > 0) {
		var sinceDatetime = new Date(Date.now() - (minutesSince * 60 * 1000));
		params['since'] = toIsoString(sinceDatetime);
	}

	if ($('#cUseLastEventId').prop('checked')) {
		if (lastEventId) {
			params['fromEventId'] = lastEventId + 1;
		}
	}

	return params;
}

function agreementData(j, parties) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Agreement ID');
	d.addColumn('string', 'Borrower');
	d.addColumn('string', 'Lender');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('string', 'Rate Type');
	d.addColumn('string', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	var counterpartyFilter = $("#sCounterparty").val();
	var rowIdx = 0;
	for (var i = 0; i < j.length; i++) {

		var matchesFilter = false;
		var canSub = false;

		var borrower;
		var lender;

		for (var t = 0; t < j[i].trade.transactingParties.length; t++) {

			if (counterpartyFilter == '_' || counterpartyFilter == j[i].trade.transactingParties[t].party.partyId) {
				matchesFilter = true;
			}

			if (j[i].trade.transactingParties[t].partyRole == 'BORROWER') {
				borrower = j[i].trade.transactingParties[t].party.partyId;
			} else if (j[i].trade.transactingParties[t].partyRole == 'LENDER') {
				lender = j[i].trade.transactingParties[t].party.partyId;
			}

			for (var p = 0; p < parties.length; p++) {
				if (parties[p].partyId == j[i].trade.transactingParties[t].party.partyId) {
					canSub = true;
					break;
				}
			}

		}

		if (!matchesFilter) {
			continue;
		}

		var btns = '<input type="button" value="Json" onclick="showJson(' + rowIdx + ', 1, \'/v1/ledger/agreements/\');return false;"/>';
		if (canSub) {
			btns += '<input type="button" value="Propose Contract" onclick="createContract(' + rowIdx + ', 1, \'/v1/ledger/agreements/\');return false;"/>';
		}

		var rateType = '--';
		if (j[i].trade.rate.rebate) {
			if (j[i].trade.rate.rebate.floating) {
				rateType = 'Rebate Floating';
			} else if (j[i].trade.rate.rebate.fixed) {
				rateType = 'Rebate Fixed';
			}
		} else if (j[i].trade.rate.fee) {
			rateType = 'Fee';
		}

		var rate = null;
		if (j[i].trade.rate.fee) {
			rate = j[i].trade.rate.fee.baseRate.toString();
		} else if (j[i].trade.rate.rebate) {
			if (j[i].trade.rate.rebate.fixed) {
				rate = j[i].trade.rate.rebate.fixed.baseRate.toString();
			} else if (j[i].trade.rate.rebate.floating) {
				rate = j[i].trade.rate.rebate.floating.benchmark + '+' + j[i].trade.rate.rebate.floating.spread.toString();
				if (j[i].trade.rate.rebate.floating.isAutoRerate) {
					rate += ' AUTO';
				}
			}
		}

		d.addRow([{ v: 'ButtonName', f: btns }
			, j[i].agreementId
			, borrower
			, lender
			, new Date(Date.parse(j[i].lastUpdateDateTime))
			, j[i].trade.instrument.ticker
			, j[i].trade.instrument.cusip
			, rateType
			, rate
			, j[i].trade.quantity
			, j[i].trade.collateral.collateralValue]);

		rowIdx++;
	}

	return d;
}

function agreementParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	var minutesSince = $("#sTimeSince").val();

	if (minutesSince == '_') {
		var sinceDatetime = new Date();
		sinceDatetime.setUTCHours(0, 0, 0, 0);
		params['since'] = toIsoString(sinceDatetime);
	} else if (minutesSince && minutesSince > 0) {
		var sinceDatetime = new Date(Date.now() - (minutesSince * 60 * 1000));
		params['since'] = toIsoString(sinceDatetime);
	}

	return params;
}

function contractData(j, parties) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Contract ID');
	d.addColumn('string', 'Contract Status');
	d.addColumn('string', 'Borrower');
	d.addColumn('string', 'Borrower Settlement');
	d.addColumn('string', 'Lender');
	d.addColumn('string', 'Lender Settlement');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('string', 'Rate Type');
	d.addColumn('string', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	var counterpartyFilter = $("#sCounterparty").val();
	var rowIdx = 0;

	for (var i = 0; i < j.length; i++) {

		var matchesFilter = false;
		var canAcc = false;
		var canDec = false;
		var canCan = false;
		var canSettle = false;
		var canRerate = false;

		var borrower;
		var borrowerSettlement;
		var lender;
		var lenderSettlement;

		for (var t = 0; t < j[i].trade.transactingParties.length; t++) {

			if (counterpartyFilter == '_' || counterpartyFilter == j[i].trade.transactingParties[t].party.partyId) {
				matchesFilter = true;
			}

			if (j[i].trade.transactingParties[t].partyRole == 'BORROWER') {
				borrower = j[i].trade.transactingParties[t].party.partyId;
			} else if (j[i].trade.transactingParties[t].partyRole == 'LENDER') {
				lender = j[i].trade.transactingParties[t].party.partyId;
			}

			if (j[i].contractStatus == 'PROPOSED') {

				//				for (var p = 0; p < parties.length; p++) {
				//					if (parties[p].partyId == j[i].trade.transactingParties[t].party.partyId && j[i].trade.transactingParties[t].partyRole == 'BORROWER') {
				canAcc = true;
				canDec = true;
				//						break;
				//					}
				//				}

				//				for (var p = 0; p < parties.length; p++) {
				//					if (parties[p].partyId == j[i].trade.transactingParties[t].party.partyId && j[i].trade.transactingParties[t].partyRole == 'LENDER') {
				canCan = true;
				//						break;
				//					}
				//				}
			} else if (j[i].contractStatus == 'PENDING') {
				canSettle = true;
			} else if (j[i].contractStatus == 'OPEN') {
				canRerate = true;
			}
		}

		if (!matchesFilter) {
			continue;
		}

		for (var s = 0; s < j[i].settlement.length; s++) {
			if (j[i].settlement[s].partyRole == 'BORROWER') {
				borrowerSettlement = j[i].settlement[s].settlementStatus;
			} else if (j[i].settlement[s].partyRole == 'LENDER') {
				lenderSettlement = j[i].settlement[s].settlementStatus;
			}

		}


		var btns = '<input type="button" value="Json" onclick="showJson(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		if (canAcc) {
			btns += '<input type="button" value="Approve" onclick="approveContract(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canDec) {
			btns += '<input type="button" value="Decline" onclick="declineContract(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canCan) {
			btns += '<input type="button" value="Cancel" onclick="cancelContract(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canSettle) {
			btns += '<input type="button" value="Confirm Settlement" onclick="confirmSettlement(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canRerate) {
			btns += '<input type="button" value="Rerate" onclick="createRerate(' + rowIdx + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}

		var rateType = '--';
		if (j[i].trade.rate.rebate) {
			if (j[i].trade.rate.rebate.floating) {
				rateType = 'Rebate Floating';
			} else if (j[i].trade.rate.rebate.fixed) {
				rateType = 'Rebate Fixed';
			}
		} else if (j[i].trade.rate.fee) {
			rateType = 'Fee';
		}

		var rate = null;
		if (j[i].trade.rate.fee) {
			rate = j[i].trade.rate.fee.baseRate.toString();
		} else if (j[i].trade.rate.rebate) {
			if (j[i].trade.rate.rebate.fixed) {
				rate = j[i].trade.rate.rebate.fixed.baseRate.toString();
			} else if (j[i].trade.rate.rebate.floating) {
				rate = j[i].trade.rate.rebate.floating.benchmark + '+' + j[i].trade.rate.rebate.floating.spread.toString();
				if (j[i].trade.rate.rebate.floating.isAutoRerate) {
					rate += ' AUTO';
				}
			}
		}

		d.addRow([{ v: 'ButtonName', f: btns }
			, j[i].contractId
			, j[i].contractStatus
			, borrower
			, borrowerSettlement
			, lender
			, lenderSettlement
			, new Date(Date.parse(j[i].lastUpdateDateTime))
			, j[i].trade.instrument.ticker
			, j[i].trade.instrument.cusip
			, rateType
			, rate
			, j[i].trade.quantity
			, j[i].trade.collateral.collateralValue]);

		rowIdx++;
	}

	return d;
}

function contractParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	var minutesSince = $("#sTimeSince").val();

	if (minutesSince == '_') {
		var sinceDatetime = new Date();
		sinceDatetime.setUTCHours(0, 0, 0, 0);
		params['since'] = toIsoString(sinceDatetime);
	} else if (minutesSince && minutesSince > 0) {
		var sinceDatetime = new Date(Date.now() - (minutesSince * 60 * 1000));
		params['since'] = toIsoString(sinceDatetime);
	}

	var withStatus = $("#sStatus").val();
	if (withStatus != '_') {
		params['contractStatus'] = withStatus;
	}

	return params;
}

function showJson(rowIndx, clickIndx, clickUriPrefix) {
	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		statusCode: {
			404: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Resource could not be found.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		},
		success: function(j) {
			document.getElementById("modal01").style.display = "block";
			document.getElementById("jsonobj").innerHTML = syntaxHighlight(j);
		}
	});
}

function fetchContract(contractId) {

	var cObj;
	var localStorageContract = localStorage.getItem(contractId);
	if (localStorageContract) {
		cObj = JSON.parse(localStorageContract);
	}

	if (!cObj) {
		$.ajax({
			type: 'GET',
			url: apiserver + '/v1/ledger/contracts/' + contractId,
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			success: function(j) {
				cObj = j;
				localStorage.setItem(contractId, JSON.stringify(cObj));
			}
		});
	}
	return cObj;
}

function createContract(rowIndx, clickIndx, clickUriPrefix) {
	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {
			document.getElementById("modal02").style.display = "block";

			$('#cAgreementId').text(j.agreementId);
			for (var t = 0; t < j.trade.transactingParties.length; t++) {
				if (j.trade.transactingParties[t].partyRole == 'BORROWER') {
					$('#cBorrower').text(j.trade.transactingParties[t].party.partyName);
				} else if (j.trade.transactingParties[t].partyRole == 'LENDER') {
					$('#cLender').text(j.trade.transactingParties[t].party.partyName);
				}
			}
			$('#cFigi').text(j.trade.instrument.figi);
			$('#cTicker').text(j.trade.instrument.ticker);
			$('#cCusip').text(j.trade.instrument.cusip);
			$('#cSedol').text(j.trade.instrument.sedol);
			if (j.trade.rate.rebate) {
				if (j.trade.rate.rebate.floating) {
					$('#cRateType').text("Rebate Floating");
					$('#cRatePct').text(j.trade.rate.rebate.floating.spread);
					$('#cBenchmark').text(j.trade.rate.rebate.floating.benchmark);
				} else if (j.trade.rate.rebate.fixed) {
					$('#cRateType').text("Rebate Fixed");
					$('#cRatePct').text(j.trade.rate.rebate.fixed.baseRate);
				}
			} else if (j.trade.rate.fee) {
				$('#cRateType').text("Fee");
				$('#cRate').text(j.trade.rate.fee.baseRate);
			}
			$('#cQuantity').text(j.trade.quantity);
			$('#cCurrency').text(j.trade.billingCurrency);
			$('#cValue').text(j.trade.collateral.collateralValue);
			$('#cTerm').text(j.trade.termType);

			$('#fAgreementId').val(j.agreementId);
		}
	});
}

function approveContract(rowIndx, clickIndx, clickUriPrefix) {

	var partyObj = JSON.parse(parties);

	var contractId = data.getFormattedValue(rowIndx, clickIndx);

	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + contractId;

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {
			document.getElementById("modal02").style.display = "block";

			$('#hContractId').val(contractId);

			var borrower;
			var lender;

			for (var t = 0; t < j.trade.transactingParties.length; t++) {

				if (j.trade.transactingParties[t].partyRole == 'BORROWER') {
					borrower = j.trade.transactingParties[t].party.partyId;
				} else if (j.trade.transactingParties[t].partyRole == 'LENDER') {
					lender = j.trade.transactingParties[t].party.partyId;
				}
			}

			for (var p = 0; p < partyObj.length; p++) {
				if (partyObj[p].partyId == borrower) {
					$('#cMyParty').text(partyObj[p].partyId);
					$('#hMyParty').val(partyObj[p].partyId);
					$('#cPartyRole').text("Borrowing From");
					$('#hPartyRole').val("BORROWER");
					$('#cCounterparty').text(lender);
					$('#hCounterparty').val(lender);
					break;
				} else if (partyObj[p].partyId == lender) {
					$('#cMyParty').text(partyObj[p].partyId);
					$('#hMyParty').val(partyObj[p].partyId);
					$('#cPartyRole').text("Lending To");
					$('#hPartyRole').val("LENDER");
					$('#cCounterparty').text(borrower);
					$('#hCounterparty').val(borrower);
					break;
				}
			}

		}
	});
}

function declineContract(rowIndx, clickIndx, clickUriPrefix) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	setTimeout(function() {
		$.ajax({
			type: 'POST',
			url: apiserver + uri + '/decline',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			statusCode: {
				403: function(responseObject, textStatus, jqXHR) {
					$("#table_div").LoadingOverlay("hide", true);
					$('#cDialogText').text('You cannot Decline a contract you Proposed. Try to Cancel instead. Otherwise please contact support.');
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				},
				400: function(responseObject, textStatus, jqXHR) {
					$('#cDialogText').text('Could not Decline contract');
					if (responseObject.responseJSON && responseObject.responseJSON.message) {
						$('#cDialogText').text(responseObject.responseJSON.message);
					}
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				}
			},
			success: function(j) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Contract declined!');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Success',
					"close": function(event, ui) { loadContracts(); }
				});
			},
			error: function(x, s, e) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Something went wrong.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		});
	}, 200);


}

function cancelContract(rowIndx, clickIndx, clickUriPrefix) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	setTimeout(function() {

		$.ajax({
			type: 'POST',
			url: apiserver + uri + '/cancel',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			statusCode: {
				403: function(responseObject, textStatus, jqXHR) {
					$("#table_div").LoadingOverlay("hide", true);
					$('#cDialogText').text('You cannot Cancel a contract you received. Try to Decline instead. Otherwise please contact support.');
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				}
			},
			success: function(j) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Contract canceled!');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Success',
					"close": function(event, ui) { loadContracts(); }
				});
			},
			error: function(x, s, e) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Something went wrong.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		});
	}, 200);

}

function createContractFromAgreement(id) {
	var uri = '/v1/ledger/agreements/' + id;

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		success: function(j) {
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success'
			});
			postContract(j.trade);
		}
	});
}

function postContract(trade) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var postUri = '/util/contractproposalgen';

	var proposalObj = JSON.parse('{}');
	proposalObj.trade = trade;
	proposalObj.settlement = $("#createContractForm").serializeArray();

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(proposalObj),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		success: function(j) {
			proposeContract(j);
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});
}

function proposeContract(proposal) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var postUri = '/v1/ledger/contracts';

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(proposal),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		statusCode: {
			404: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Something went wrong with the request.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			403: function(responseObject, textStatus, jqXHR) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('You are not authorized to create a contract.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			400: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not Propose contract');
				if (responseObject.responseJSON && responseObject.responseJSON.message) {
					$('#cDialogText').text(responseObject.responseJSON.message);
				}
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		},
		success: function(j, t, x) {
			$('#cDialogText').text('Contract proposed. ' + j.resourceUri);
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success'
			});
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});
}

function validateAccept(frm) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	$.ajax({
		type: 'POST',
		url: "/util/acceptform",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: frm.serialize(),
		dataType: "json",
		async: false,
		success: function(j) {
			acceptContract(j, frm[0].hContractId.value);
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});

}

function acceptContract(accept, id) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var postUri = '/v1/ledger/contracts/' + id + '/approve';

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(accept),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		statusCode: {
			403: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('You cannot Approve a contract you Proposed');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			404: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not approve contract');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			400: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not approve contract');
				if (responseObject.responseJSON && responseObject.responseJSON.message) {
					$('#cDialogText').text(responseObject.responseJSON.message);
				}
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		},
		success: function(j, t, x) {
			$('#cDialogText').text('Contract approved. ' + j.resourceUri);
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success',
				"close": function(event, ui) { loadContracts(); }
			});
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});

}

function confirmSettlement(rowIndx, clickIndx, clickUriPrefix) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	setTimeout(function() {
		$.ajax({
			type: 'PATCH',
			url: apiserver + uri,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: '{"settlementStatus": "SETTLED"}',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			statusCode: {
				403: function(responseObject, textStatus, jqXHR) {
					$("#table_div").LoadingOverlay("hide", true);
					$('#cDialogText').text('You cannot Decline a contract you Proposed. Try to Cancel instead. Otherwise please contact support.');
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				},
				400: function(responseObject, textStatus, jqXHR) {
					$('#cDialogText').text('Could not Decline contract');
					if (responseObject.responseJSON && responseObject.responseJSON.message) {
						$('#cDialogText').text(responseObject.responseJSON.message);
					}
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				}
			},
			success: function(j) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Settlement confirmed!');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Success',
					"close": function(event, ui) { loadContracts(); }
				});
			},
			error: function(x, s, e) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Something went wrong.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		});
	}, 200);
}

function pageTable(p) {
	//      	alert(p.page);
	//      	alert(lastEventId);
}

// Script to open and close sidebar
function w3_open() {
	document.getElementById("mySidebar").style.display = "block";
	document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
	document.getElementById("mySidebar").style.display = "none";
	document.getElementById("myOverlay").style.display = "none";
}

// Modal
function onClick(element) {
	document.getElementById("img01").src = element.src;
	document.getElementById("modal01").style.display = "block";
}

function syntaxHighlight(json) {
	if (typeof json != 'string') {
		json = JSON.stringify(json, undefined, 2);
	}
	json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
	return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
		var cls = 'number';
		if (/^"/.test(match)) {
			if (/:$/.test(match)) {
				cls = 'key';
			} else {
				cls = 'string';
			}
		} else if (/true|false/.test(match)) {
			cls = 'boolean';
		} else if (/null/.test(match)) {
			cls = 'null';
		}
		return '<span class="' + cls + '">' + match + '</span>';
	});
}

function toIsoString(date) {
	var tzo = -date.getTimezoneOffset(),
		dif = tzo >= 0 ? '+' : '-',
		pad = function(num) {
			return (num < 10 ? '0' : '') + num;
		};

	return date.getFullYear() +
		'-' + pad(date.getMonth() + 1) +
		'-' + pad(date.getDate()) +
		'T' + pad(date.getHours()) +
		':' + pad(date.getMinutes()) +
		':' + pad(date.getSeconds()) +
		dif + pad(Math.floor(Math.abs(tzo) / 60)) +
		':' + pad(Math.abs(tzo) % 60);
}

function loadPartylist(selobj, parties) {

	$('#sCounterparty').empty();

	$('#sCounterparty').append(
		$('<option></option>')
			.val('_')
			.html('All'));

	var partyObj = JSON.parse(parties);

	$.ajax({
		type: 'GET',
		url: apiserver + '/v1/ledger/parties',
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {
			$.each(j, function(i, obj) {

				for (var p = 0; p < partyObj.length; p++) {
					if (partyObj[p].partyId != obj.partyId) {
						$('#sCounterparty').append(
							$('<option></option>')
								.val(obj.partyId)
								.html(obj.partyId + ' - ' + obj.gleifLei));
						break;
					}
				}
			});
			$('#sCounterparty').html($("#sCounterparty option").sort(function(a, b) {
				return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
			}))
			$('#sCounterparty').val('_');
		}
	});
}

function loadInstrumentlist() {

	$('#sInstrument').empty();

	$.ajax({
		type: 'GET',
		url: apiserver + '/util/instruments',
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {
			$.each(j, function(i, obj) {
				$('#sInstrument').append(
					$('<option></option>')
						.val(obj.id)
						.html(obj.value + ' - ' + obj.label));
			});
			$('#sInstrument').html($("#sInstrument option").sort(function(a, b) {
				return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
			}));
		}
	});
}

function rerateData(j, parties) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Status');
	d.addColumn('string', 'Rerate ID');
	d.addColumn('string', 'Contract ID');
	d.addColumn('string', 'Borrower');
	d.addColumn('string', 'Lender');
	d.addColumn('string', 'Ticker');
	d.addColumn('number', 'Quantity');
	d.addColumn('string', 'Current Rate Type');
	d.addColumn('string', 'Current Rate');
	d.addColumn('string', 'Proposed Rate Type');
	d.addColumn('string', 'Proposed Rate');
	d.addColumn('datetime', 'Last Update');

	var counterpartyFilter = $("#sCounterparty").val();
	var rowIdx = 0;

	for (var i = 0; i < j.length; i++) {

		var matchesFilter = false;
		var canAcc = false;
		var canDec = false;
		var canCan = false;

		var borrower;
		var lender;
		var ticker;
		var quantity;

		var contractObj = fetchContract(j[i].contractId);

		if (contractObj) {

			ticker = contractObj.trade.instrument.ticker;
			quantity = contractObj.trade.quantity;

			for (var t = 0; t < contractObj.trade.transactingParties.length; t++) {

				if (counterpartyFilter == '_' || counterpartyFilter == contractObj.trade.transactingParties[t].party.partyId) {
					matchesFilter = true;
				}

				if (contractObj.trade.transactingParties[t].partyRole == 'BORROWER') {
					borrower = contractObj.trade.transactingParties[t].party.partyId;
				} else if (contractObj.trade.transactingParties[t].partyRole == 'LENDER') {
					lender = contractObj.trade.transactingParties[t].party.partyId;
				}

				if (j[i].status == 'PROPOSED') {

					//				for (var p = 0; p < parties.length; p++) {
					//					if (parties[p].partyId == contractObj.trade.transactingParties[t].party.partyId && contractObj.trade.transactingParties[t].partyRole == 'BORROWER') {
					canAcc = true;
					canDec = true;
					//						break;
					//					}
					//				}

					//				for (var p = 0; p < parties.length; p++) {
					//					if (parties[p].partyId == contractObj.trade.transactingParties[t].party.partyId && contractObj.trade.transactingParties[t].partyRole == 'LENDER') {
					canCan = true;
					//						break;
					//					}
					//				}
				}
			}
		}

		if (!matchesFilter) {
			continue;
		}

		var actUrl = '\'/v1/ledger/contracts/' + j[i].contractId + '/rerates/' + j[i].rerateId + '\'';

		var btns = '<input type="button" value="Json" onclick="showJson(' + rowIdx + ', 2, \'/v1/ledger/rerates/\');return false;"/>';
		if (canAcc) {
			btns += '<input type="button" value="Approve" onclick="approveRerate(\'' + j[i].contractId + '\', \'' + j[i].rerateId + '\', ' + actUrl + ');return false;"/>';
		}
		if (canDec) {
			btns += '<input type="button" value="Decline" onclick="declineRerate(' + actUrl + ');return false;"/>';
		}
		if (canCan) {
			btns += '<input type="button" value="Cancel" onclick="cancelRerate(' + actUrl + ');return false;"/>';
		}

		var currentRateType = '--';
		if (j[i].rate.rebate) {
			if (j[i].rate.rebate.floating) {
				currentRateType = 'Rebate Floating';
			} else if (j[i].rate.rebate.fixed) {
				currentRateType = 'Rebate Fixed';
			}
		} else if (j[i].rate.fee) {
			currentRateType = 'Fee';
		}

		var currentRate = null;
		if (j[i].rate.fee) {
			currentRate = j[i].rate.fee.baseRate.toString();
		} else if (j[i].rate.rebate) {
			if (j[i].rate.rebate.fixed) {
				currentRate = j[i].rate.rebate.fixed.baseRate.toString();
			} else if (j[i].rate.rebate.floating) {
				currentRate = j[i].rate.rebate.floating.benchmark + '+' + j[i].rate.rebate.floating.spread.toString();
				if (j[i].rate.rebate.floating.isAutoRerate) {
					currentRate += ' AUTO';
				}
			}
		}

		var proposedRateType = '--';
		if (j[i].rate.rebate) {
			if (j[i].rate.rebate.floating) {
				proposedRateType = 'Rebate Floating';
			} else if (j[i].rate.rebate.fixed) {
				proposedRateType = 'Rebate Fixed';
			}
		} else if (j[i].rate.fee) {
			proposedRateType = 'Fee';
		}

		var proposedRate = null;
		if (j[i].rerate.fee) {
			proposedRate = j[i].rerate.fee.baseRate.toString();
		} else if (j[i].rerate.rebate) {
			if (j[i].rerate.rebate.fixed) {
				proposedRate = j[i].rerate.rebate.fixed.baseRate.toString();
			} else if (j[i].rerate.rebate.floating) {
				proposedRate = j[i].rerate.rebate.floating.benchmark + '+' + j[i].rerate.rebate.floating.spread.toString();
				if (j[i].rerate.rebate.floating.isAutoRerate) {
					proposedRate += ' AUTO';
				}
			}
		}

		d.addRow([{ v: 'ButtonName', f: btns }
			, j[i].status
			, j[i].rerateId
			, j[i].contractId
			, borrower
			, lender
			, ticker
			, quantity
			, currentRateType
			, currentRate
			, proposedRateType
			, proposedRate
			, new Date(Date.parse(j[i].lastUpdateDatetime)) //<-- TODO should this be lastUpdateDateTime with capital T?
		]);

		rowIdx++;
	}

	return d;
}

function rerateParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	var minutesSince = $("#sTimeSince").val();

	if (minutesSince == '_') {
		var sinceDatetime = new Date();
		sinceDatetime.setUTCHours(0, 0, 0, 0);
		params['since'] = toIsoString(sinceDatetime);
	} else if (minutesSince && minutesSince > 0) {
		var sinceDatetime = new Date(Date.now() - (minutesSince * 60 * 1000));
		params['since'] = toIsoString(sinceDatetime);
	}

	var withStatus = $("#sStatus").val();
	if (withStatus != '_') {
		params['status'] = withStatus;
	}

	return params;
}

function declineRerate(clickUriPrefix) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	setTimeout(function() {
		$.ajax({
			type: 'POST',
			url: apiserver + clickUriPrefix + '/decline',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			statusCode: {
				403: function(responseObject, textStatus, jqXHR) {
					$("#table_div").LoadingOverlay("hide", true);
					$('#cDialogText').text('You cannot Decline a rerate you Proposed. Try to Cancel instead. Otherwise please contact support.');
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				}
			},
			success: function(j) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Rerate declined!');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Success',
					"close": function(event, ui) { loadRerates(); }
				});
			},
			error: function(x, s, e) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Something went wrong.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		});
	}, 200);


}

function cancelRerate(clickUriPrefix) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	$("#table_div").LoadingOverlay("show", {
		background: "rgba(255, 255, 255, 0.8)"
	});

	setTimeout(function() {

		$.ajax({
			type: 'POST',
			url: apiserver + clickUriPrefix + '/cancel',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			headers: {
				'Content-Type': 'application/json'
			},
			async: false,
			statusCode: {
				403: function(responseObject, textStatus, jqXHR) {
					$("#table_div").LoadingOverlay("hide", true);
					$('#cDialogText').text('You cannot Cancel a rerate you received. Try to Decline instead. Otherwise please contact support.');
					$('#cDialog').dialog({
						"show": true,
						"modal": true,
						"title": 'Error'
					});
				}
			},
			success: function(j) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Rerate canceled!');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Success',
					"close": function(event, ui) { loadRerates(); }
				});
			},
			error: function(x, s, e) {
				$("#table_div").LoadingOverlay("hide", true);
				$('#cDialogText').text('Something went wrong.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		});
	}, 200);

}

function approveRerate(contractId, rerateId, clickUriPrefix) {

	var partyObj = JSON.parse(parties);

	$.ajax({
		type: 'GET',
		url: apiserver + clickUriPrefix,
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {

			var contractObj = fetchContract(contractId);

			if (contractObj) {

				document.getElementById("modal02").style.display = "block";

				$('#hContractId').val(contractId);
				$('#hRerateId').val(rerateId);

				$('#cName').text(contractObj.trade.instrument.ticker);
				$('#cQuantity').text(contractObj.trade.quantity);

				var borrower;
				var lender;

				for (var t = 0; t < contractObj.trade.transactingParties.length; t++) {

					if (contractObj.trade.transactingParties[t].partyRole == 'BORROWER') {
						borrower = contractObj.trade.transactingParties[t].party.partyId;
					} else if (contractObj.trade.transactingParties[t].partyRole == 'LENDER') {
						lender = contractObj.trade.transactingParties[t].party.partyId;
					}
				}

				for (var p = 0; p < partyObj.length; p++) {
					if (partyObj[p].partyId == borrower) {
						$('#cMyParty').text(partyObj[p].partyId);
						$('#hMyParty').val(partyObj[p].partyId);
						$('#cPartyRole').text("Borrowing From");
						$('#hPartyRole').val("BORROWER");
						$('#cCounterparty').text(lender);
						$('#hCounterparty').val(lender);
						break;
					} else if (partyObj[p].partyId == lender) {
						$('#cMyParty').text(partyObj[p].partyId);
						$('#hMyParty').val(partyObj[p].partyId);
						$('#cPartyRole').text("Lending To");
						$('#hPartyRole').val("LENDER");
						$('#cCounterparty').text(borrower);
						$('#hCounterparty').val(borrower);
						break;
					}
				}

				var currentRateType = '--';
				var currentRate = '--';
				var currentBenchmark = '--';
				var currentAutoRerate = '--';
				var currentBenchmarkRate = '--';

				if (contractObj.trade.rate.rebate) {
					if (contractObj.trade.rate.rebate.floating) {
						currentRateType = 'Rebate Floating';
						currentRate = contractObj.trade.rate.rebate.floating.spread;
						currentBenchmark = contractObj.trade.rate.rebate.floating.benchmark;
						currentBenchmarkRate = contractObj.trade.rate.rebate.floating.baseRate;
						if (contractObj.trade.rate.rebate.floating.isAutoRerate) {
							currentAutoRerate = 'YES'
						} else {
							currentAutoRerate = 'NO'
						}
					} else if (contractObj.trade.rate.rebate.fixed) {
						currentRateType = 'Rebate Fixed';
						currentRate = contractObj.trade.rate.rebate.fixed.baseRate;
					}
				} else if (contractObj.trade.rate.fee) {
					currentRateType = 'Fee';
					currentRate = contractObj.trade.rate.fee.baseRate;
				}

				$('#cCurrentRateType').text(currentRateType);
				$('#cCurrentRatePct').text(currentRate);
				$('#cCurrentBenchmark').text(currentBenchmark);
				$('#cCurrentAutoRerate').text(currentAutoRerate);
				$('#cCurrentBaseRatePct').text(currentBenchmarkRate);

				var proposedRateType = '--';
				var proposedRate = '--';
				var proposedBenchmark = '--';
				var proposedAutoRerate = '--';
				var proposedBenchmarkRate = '--';

				if (j.rerate.rebate) {
					if (j.rerate.rebate.floating) {
						proposedRateType = 'Rebate Floating';
						proposedRate = j.rerate.rebate.floating.spread;
						proposedBenchmark = j.rerate.rebate.floating.benchmark;
						proposedBenchmarkRate = j.rerate.rebate.floating.baseRate;
						if (j.rerate.rebate.floating.isAutoRerate) {
							proposedAutoRerate = 'YES'
						} else {
							proposedAutoRerate = 'NO'
						}
					} else if (j.rerate.rebate.fixed) {
						proposedRateType = 'Rebate Fixed';
						proposedRate = j.rerate.rebate.fixed.baseRate;
					}
				} else if (j.rerate.fee) {
					proposedRateType = 'Fee';
					proposedRate = j.rerate.fee.baseRate;
				}

				$('#cProposedRateType').text(proposedRateType);
				$('#cProposedRatePct').text(proposedRate);
				$('#cProposedBenchmark').text(proposedBenchmark);
				$('#cProposedAutoRerate').text(proposedAutoRerate);
				$('#cProposedBaseRatePct').text(proposedBenchmarkRate);

			} else {
				$('#cDialogText').text('Could not link rerate to an active contract');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});

			}
		}
	});
}

function acceptRerate(frm) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var postUri = '/v1/ledger/contracts/' + frm[0].hContractId.value + '/rerates/' + frm[0].hRerateId.value + '/approve';

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		statusCode: {
			403: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('You cannot Approve a rerate you Proposed');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			404: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not approve rerate');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			400: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not approve rerate');
				if (responseObject.responseJSON && responseObject.responseJSON.message) {
					$('#cDialogText').text(responseObject.responseJSON.message);
				}
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		},
		success: function(j, t, x) {
			$('#cDialogText').text('Rerate approved. ' + j.resourceUri);
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success',
				"close": function(event, ui) { loadRerates(); }
			});
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});
}

function createRerate(rowIndx, clickIndx, clickUriPrefix) {

	var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);

	var partyObj = JSON.parse(parties);

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Content-Type': 'application/json'
		},
		async: true,
		success: function(j) {
			document.getElementById("modal03").style.display = "block";

			$('#rhContractId').val(data.getFormattedValue(rowIndx, clickIndx));
			$('#rcName').text(j.trade.instrument.ticker);
			$('#rcQuantity').text(j.trade.quantity);

			var borrower;
			var lender;

			for (var t = 0; t < j.trade.transactingParties.length; t++) {

				if (j.trade.transactingParties[t].partyRole == 'BORROWER') {
					borrower = j.trade.transactingParties[t].party.partyId;
				} else if (j.trade.transactingParties[t].partyRole == 'LENDER') {
					lender = j.trade.transactingParties[t].party.partyId;
				}
			}

			for (var p = 0; p < partyObj.length; p++) {
				if (partyObj[p].partyId == borrower) {
					$('#rcMyParty').text(partyObj[p].partyId);
					$('#rhMyParty').val(partyObj[p].partyId);
					$('#rcPartyRole').text("Borrowing From");
					$('#rhPartyRole').val("BORROWER");
					$('#rcCounterparty').text(lender);
					$('#rhCounterparty').val(lender);
					break;
				} else if (partyObj[p].partyId == lender) {
					$('#rcMyParty').text(partyObj[p].partyId);
					$('#rhMyParty').val(partyObj[p].partyId);
					$('#rcPartyRole').text("Lending To");
					$('#rhPartyRole').val("LENDER");
					$('#rcCounterparty').text(borrower);
					$('#rhCounterparty').val(borrower);
					break;
				}
			}

			var currentRateType = '--';
			var currentRate = '--';
			var currentBenchmark = '--';
			var currentAutoRerate = '--';
			var currentBenchmarkRate = '--';

			if (j.trade.rate.rebate) {
				if (j.trade.rate.rebate.floating) {
					currentRateType = 'Rebate Floating';
					currentRate = j.trade.rate.rebate.floating.spread;
					currentBenchmark = j.trade.rate.rebate.floating.benchmark;
					currentBenchmarkRate = j.trade.rate.rebate.floating.baseRate;
					$("#rsProposedRateType option[value=RFL]").attr('selected', 'selected');
					$('#rtProposedRate').val(currentRate);
					$('#rsProposedBenchmark option[value=' + currentBenchmark + ']').attr('selected', 'selected');
					if (j.trade.rate.rebate.floating.isAutoRerate) {
						currentAutoRerate = 'YES'
						$("#rsProposedAutoRerate option[value=YES]").attr('selected', 'selected');
					} else {
						currentAutoRerate = 'NO'
						$("#rsProposedAutoRerate option[value=NO]").attr('selected', 'selected');
					}
					$('#rtProposedBenchmarkRate').val(currentBenchmarkRate);
				} else if (j.trade.rate.rebate.fixed) {
					currentRateType = 'Rebate Fixed';
					currentRate = j.trade.rate.rebate.fixed.baseRate;
					$("#rsProposedRateType option[value=RFI]").attr('selected', 'selected');
					$('#rtProposedRate').val(currentRate);
				}
			} else if (j.trade.rate.fee) {
				currentRateType = 'Fee';
				currentRate = j.trade.rate.fee.baseRate;
				$("#rsProposedRateType option[value=FEE]").attr('selected', 'selected');
				$('#rtProposedRate').val(currentRate);
			}

			toggleRSBenchmark();

			$('#rcCurrentRateType').text(currentRateType);
			$('#rcCurrentRate').text(currentRate);
			$('#rcCurrentRateBenchmark').text(currentBenchmark);
			$('#rcAutoRerate').text(currentAutoRerate);
			$('#rcCurrentRateBenchmarkVal').text(currentBenchmarkRate);

		}
	});
}

function validateRerateProposal(frm) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');
	var contractId = frm.find("[name=contractId]").val();

	$.ajax({
		type: 'POST',
		url: "/util/reratecontractform",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: frm.serialize(),
		dataType: "json",
		async: false,
		success: function(j) {
			proposeRerate(contractId, j);
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});

}

function proposeRerate(contractId, proposal) {

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var postUri = '/v1/ledger/contracts/' + contractId + '/rerates';

	$.ajax({
		type: 'POST',
		url: apiserver + postUri,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(proposal),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		headers: {
			'Content-Type': 'application/json'
		},
		async: false,
		statusCode: {
			404: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Something went wrong with the request.');
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			},
			400: function(responseObject, textStatus, jqXHR) {
				$('#cDialogText').text('Could not create rerate');
				if (responseObject.responseJSON && responseObject.responseJSON.message) {
					$('#cDialogText').text(responseObject.responseJSON.message);
				}
				$('#cDialog').dialog({
					"show": true,
					"modal": true,
					"title": 'Error'
				});
			}
		},
		success: function(j, t, x) {
			$('#cDialogText').text('Rerate proposed. ' + j.resourceUri);
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Success'
			});
		},
		error: function(x, s, e) {
			$('#cDialogText').text('Something went wrong.');
			$('#cDialog').dialog({
				"show": true,
				"modal": true,
				"title": 'Error'
			});
		}
	});
}
