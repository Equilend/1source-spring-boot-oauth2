var table;
var data;
var authtoken;
var apiserver = 'http://demoapi.1sourceeq.com';
var clickIndx;
var clickUriPrefix;

function loadData(token, uri, dFunction, colUriIndx, colUriPrefix) {

    authtoken = token;
    clickIndx = colUriIndx;
    clickUriPrefix = colUriPrefix;
	table = new google.visualization.Table(document.getElementById('table_div'));
	google.visualization.events.addListener(table, 'page', pageTable);
	google.visualization.events.addListener(table, 'select', rowClick);

	table.clearChart();

	var params = { 'noCache': new Date().getTime() };
	var eventType = $("#sEventType").val();
	if (eventType != '_') {
		params['eventType'] = eventType;
	}
	params['size'] = 1000;
	//      	if (lastEventId) {
	//      		params['fromEventId'] = lastEventId;
	//      	}

	$.ajax({
		type: 'GET',
		url: apiserver + uri,
		headers: {
			'Authorization': 'Bearer ' + authtoken,
			'Content-Type': 'application/json'
		},
		data: params,
		async: true,
		success: function(j) {
			data = dFunction(j);
			table.draw(data, { showRowNumber: true, width: '100%', height: '90%', page: 'enable', pageSize: 10 });
		}
	});
}

function eventData(j) {

	var d = new google.visualization.DataTable();
	d.addColumn('number', 'Event ID');
	d.addColumn('string', 'Event Type');
	d.addColumn('datetime', 'Timestamp');
	d.addColumn('string', 'URI');

	for (var i = 0; i < j.length; i++) {
		d.addRow([j[i].eventId, j[i].eventType, new Date(Date.parse(j[i].eventDatetime)), j[i].resourceUri]);
		lastEventId = j[i].eventId;
	}

	return d;
}

function agreementData(j) {

	var d = new google.visualization.DataTable();
	d.addColumn('string', 'Agreement ID');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('number', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	for (var i = 0; i < j.length; i++) {
		d.addRow([j[i].agreementId
			, new Date(Date.parse(j[i].lastUpdateDatetime))
			, j[i].trade.instrument.ticker
			, j[i].trade.instrument.cusip
			, j[i].trade.rate.rebateBps
			, j[i].trade.quantity
			, j[i].trade.collateral.collateralValue]);
	}

	return d;
}

function contractData(j) {
	var d = new google.visualization.DataTable();
	d.addColumn('string', 'Contract ID');
	d.addColumn('datetime', 'Last Update');
	d.addColumn('string', 'Status');
	d.addColumn('string', 'Ticker');
	d.addColumn('string', 'Cusip');
	d.addColumn('number', 'Rate');
	d.addColumn('number', 'Quantity');
	d.addColumn('number', 'Value');

	for (var i = 0; i < j.length; i++) {
		d.addRow([j[i].contractId
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

function rowClick() {
  //document.getElementById("img01").src = element.src;
  var selection = table.getSelection();
  var item = selection[0];
  var uri = clickUriPrefix + data.getFormattedValue(item.row, clickIndx);
  
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