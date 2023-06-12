var table;
var data;
var authtoken;
var apiserver = 'http://demoapi.1sourceeq.com';

function loadData(token, uri, dFunction, pFunction) {

    authtoken = token;
	table = new google.visualization.Table(document.getElementById('table_div'));
	google.visualization.events.addListener(table, 'page', pageTable);

	table.clearChart();

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Authorization': 'Bearer ' + authtoken,
			'Content-Type': 'application/json'
		},
		data: pFunction(),
		async: true,
		success: function(j) {
			data = dFunction(j);
			table.draw(data, { allowHtml: true, showRowNumber: true, width: '100%', height: '90%', page: 'enable', pageSize: 10 });
		}
	});
}

var lastEventId;

function eventData(j) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('number', 'Event ID');
	d.addColumn('string', 'Event Type');
	d.addColumn('datetime', 'Timestamp');
	d.addColumn('string', 'URI');

	for (var i = 0; i < j.length; i++) {
		d.addRow([{v:'ButtonName', f:'<input type="button" value="Json" onclick="showJson(' + i + ', 4, \'\');return false;"/>'}
			, j[i].eventId
			, j[i].eventType
			, new Date(Date.parse(j[i].eventDatetime))
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
	if (minutesSince && minutesSince > 0) {
		var sinceDatetime = new Date(Date.now() - (minutesSince*60*1000));
		params['since'] = toIsoString(sinceDatetime);
	}
	
	if ($('#cUseLastEventId').prop('checked')) {
		if (lastEventId) {
	  	    params['fromEventId'] = lastEventId+1;
		}
	}

	return params;	
}

function agreementData(j) {

    var jwt = parseJwt(authtoken);
    
	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Agreement ID');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('number', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	for (var i = 0; i < j.length; i++) {
		
		var canSub = false;
		for (var t = 0; t<j[i].trade.transactingParties.length; t++) {
			if (j[i].trade.transactingParties[t].partyRole == 'LENDER' && j[i].trade.transactingParties[t].party.partyId == jwt.partyId) {
				canSub = true;
			}
		}
		
		var btns = '<input type="button" value="Json" onclick="showJson(' + i + ', 1, \'/v1/ledger/agreements/\');return false;"/>';
		if (canSub) {
			btns += '<input type="button" value="Submit" onclick="createContract(' + i + ', 1, \'/v1/ledger/agreements/\');return false;"/>';
		}
		d.addRow([{v:'ButtonName', f:btns}
			, j[i].agreementId
			, new Date(Date.parse(j[i].lastUpdateDatetime))
			, j[i].trade.instrument.ticker
			, j[i].trade.instrument.cusip
			, j[i].trade.rate.rebateBps
			, j[i].trade.quantity
			, j[i].trade.collateral.collateralValue]);
	}

	return d;
}

function agreementParams() {

	var params = { 'noCache': new Date().getTime() };

	return params;	
}

function contractData(j) {
	
    var jwt = parseJwt(authtoken);

	var d = new google.visualization.DataTable();
	d.addColumn('string', '');
	d.addColumn('string', 'Contract ID');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Status');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('number', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	for (var i = 0; i < j.length; i++) {

		var canAcc = false;
		var canDec = false;
		var canCan = false;
		for (var t = 0; t<j[i].trade.transactingParties.length; t++) {
			if (j[i].contractStatus == 'PROPOSED') {
				
				if (j[i].trade.transactingParties[t].partyRole == 'BORROWER'
					&& j[i].trade.transactingParties[t].party.partyId == jwt.partyId) {
					canAcc = true;
					canDec = true;
				}

				if (j[i].trade.transactingParties[t].partyRole == 'LENDER'
					&& j[i].trade.transactingParties[t].party.partyId == jwt.partyId) {
					canCan = true;
				}
			}
		}

		var btns = '<input type="button" value="Json" onclick="showJson(' + i + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		if (canAcc) {
			btns += '<input type="button" value="Accept" onclick="acceptContract(' + i + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canDec) {
			btns += '<input type="button" value="Decline" onclick="declineContract(' + i + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}
		if (canCan) {
			btns += '<input type="button" value="Cancel" onclick="cancelContract(' + i + ', 1, \'/v1/ledger/contracts/\');return false;"/>';
		}

		d.addRow([{v:'ButtonName', f:btns}
		    , j[i].contractId
			, new Date(Date.parse(j[i].lastUpdateDatetime))
			, j[i].contractStatus
			, j[i].trade.instrument.ticker
			, j[i].trade.instrument.cusip
			, j[i].trade.rate.rebateBps
			, j[i].trade.quantity
			, j[i].trade.collateral.collateralValue]);
	}

    return d;
}

function contractParams() {

	var params = { 'noCache': new Date().getTime() };

	return params;	
}

function showJson(rowIndx, clickIndx, clickUriPrefix) {
  var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);
  
  $.ajax({
  	  type: 'GET',
  	  url: apiserver + uri,
  	  headers: {
        'Authorization':'Bearer ' + authtoken,
	        'Content-Type':'application/json'
			},
  	  async: true,
  	  success: function(j) {
  		  document.getElementById("modal01").style.display = "block";
  		  document.getElementById("caption").innerHTML = uri;
  		  document.getElementById("jsonobj").innerHTML = syntaxHighlight(j);
  	  }
  	});
}

function createContract(rowIndx, clickIndx, clickUriPrefix) {
  var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);
  
  $.ajax({
  	  type: 'GET',
  	  url: apiserver + uri,
  	  headers: {
        'Authorization':'Bearer ' + authtoken,
	        'Content-Type':'application/json'
			},
  	  async: true,
  	  success: function(j) {
  		  document.getElementById("modal02").style.display = "block";
  		  document.getElementById("caption02").innerHTML = 'Create Contract from Agreement ' + data.getFormattedValue(rowIndx, clickIndx);
  		  
  		  $('#cAgreementId').text(j.agreementId);
  		  $('#cCounterparty').text(j.trade.instrument.ticker);
  		  $('#cFigi').text(j.trade.instrument.figi);
  		  $('#cTicker').text(j.trade.instrument.ticker);
  		  $('#cCusip').text(j.trade.instrument.cusip);
  		  $('#cSedol').text(j.trade.instrument.sedol);
  		  $('#cRate').text(j.trade.rate.rebateBps);
  		  $('#cQuantity').text(j.trade.quantity);
  		  $('#cCurrency').text(j.trade.billingCurrency);
  		  $('#cValue').text(j.trade.collateral.collateralValue);
  		  $('#cTerm').text(j.trade.termType);

  		  $('#fAgreementId').val(j.agreementId);
  	  }
  	});
}

function acceptContract(rowIndx, clickIndx, clickUriPrefix) {
  alert('todo');
}

function declineContract(rowIndx, clickIndx, clickUriPrefix) {
  alert('todo');
}

function cancelContract(rowIndx, clickIndx, clickUriPrefix) {

  var uri = (clickUriPrefix == null ? '' : clickUriPrefix) + data.getFormattedValue(rowIndx, clickIndx);
	
    $.ajax({
  	  type: 'POST',
  	  url: apiserver + uri + '/cancel',
      headers: {
        'Authorization':'Bearer ' + authtoken,
	        'Content-Type':'application/json'
	  },
  	  async: false,
  	  success: function(j) {
	    $('#cDialogText').text('Contract canceled!');
  	  },
  	  error: function(x, s, e) {
	    $('#cDialogText').text('Something went wrong.');
      }
  	});

}

function createContractFromAgreement(id) {
  var uri = '/v1/ledger/agreements/' + id;
  
  $.ajax({
  	  type: 'GET',
  	  url: apiserver + uri,
  	  headers: {
        'Authorization':'Bearer ' + authtoken,
	        'Content-Type':'application/json'
			},
  	  async: false,
  	  success: function(j) {
        $('#cDialog').dialog({
          "show": true,
          "modal": true
        });
		postContract(j);
  	  }
  	});
}

function postContract(trade) {
  var postUri = '/v1/ledger/contracts';
  
  $.ajax({
  	  type: 'POST',
  	  url: apiserver + postUri,
      data: JSON.stringify(trade),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      headers: {
        'Authorization':'Bearer ' + authtoken,
	        'Content-Type':'application/json'
	  },
  	  async: false,
  	  success: function(j) {
	    $('#cDialogText').text('Contract created!');
  	  },
  	  error: function(x, s, e) {
	    $('#cDialogText').text('Something went wrong.');
      }
  	});
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
	var captionText = document.getElementById("caption");
	captionText.innerHTML = element.alt;
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

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
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