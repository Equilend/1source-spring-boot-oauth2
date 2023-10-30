function loadContractStoreParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	return params;
}

function loadAgreementStoreParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	return params;
}

function loadRerateStoreParams() {

	var params = { 'noCache': new Date().getTime() };

	params['size'] = 1000;

	return params;
}

function loadContractStore() {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'GET',
			url: apiserver + '/v1/ledger/contracts',
			headers: {
				'Content-Type': 'application/json'
			},
			data: loadContractStoreParams(),
			async: true,
			success: function(j) {
                var openC = 0;
                var propC = 0;
                var apprC = 0;
				for (var i = 0; i < j.length; i++) {
					localStorage.setItem(j[i].contractId, JSON.stringify(j[i]));
					if (j[i].contractStatus == 'OPEN') {
						openC++;
					} else if (j[i].contractStatus == 'APPROVED') {
						apprC++;
					} else if (j[i].contractStatus == 'PROPOSED') {
						propC++;
					}
				}
				console.log("contractStore loaded: " + j.length);
				localStorage.setItem('storeLoaded', true);
				localStorage.setItem('storeSize', j.length);
				localStorage.setItem('storeOpenSize', openC);
				localStorage.setItem('storeProposedSize', propC);
				localStorage.setItem('storeApprovedSize', apprC);
				resolve();
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log("contractStore load error: " + thrownError);
				resolve();
			}
		})
	})
}

function loadAgreementStore() {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'GET',
			url: apiserver + '/v1/ledger/agreements',
			headers: {
				'Content-Type': 'application/json'
			},
			data: loadAgreementStoreParams(),
			async: true,
			success: function(j) {
                var confA = 0;
				for (var i = 0; i < j.length; i++) {
					if (j[i].status == 'CONFIRMED') {
						confA++;
					}
				}
				console.log("agreementStore loaded: " + j.length);
				localStorage.setItem('storeAgreementSize', confA);
				resolve();
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log("contractStore load error: " + thrownError);
				resolve();
			}
		})
	})
}

function loadRerateStore() {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'GET',
			url: apiserver + '/v1/ledger/rerates',
			headers: {
				'Content-Type': 'application/json'
			},
			data: loadRerateStoreParams(),
			async: true,
			success: function(j) {
                var propR = 0;
				for (var i = 0; i < j.length; i++) {
					if (j[i].status == 'PROPOSED') {
						propR++;
					}
				}
				console.log("rerateStore loaded: " + j.length);
				localStorage.setItem('storeRerateSize', propR);
				resolve();
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log("contractStore load error: " + thrownError);
				resolve();
			}
		})
	})
}

function storeLoaded() {
	return localStorage.getItem('storeLoaded');
}

function getContract(contractId) {
	return localStorage.getItem(contractId);
}