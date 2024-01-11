function loadContractStoreParams() {

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

				var dateNow = Date.now();
				
                var openC = 0;
                var propC = 0;
                var pendC = 0;

                var openC6hr = 0;
                var propC6hr = 0;
                var pendC6hr = 0;

                var openC24hr = 0;
                var propC24hr = 0;
                var pendC24hr = 0;

                var openC1d = 0;
                var propC1d = 0;
                var pendC1d = 0;

                var openC3d = 0;
                var propC3d = 0;
                var pendC3d = 0;

				for (var i = 0; i < j.length; i++) {

					var milliDif = calcMilliDif(dateNow, (new Date(Date.parse(j[i].lastUpdateDateTime))).getTime());

					localStorage.setItem(j[i].contractId, JSON.stringify(j[i]));
					if (j[i].contractStatus == 'OPEN') {
						openC++;
						if (milliDif < (6*60*60*1000)) {
							openC6hr++;
						} else if (milliDif >= (6*60*60*1000) && milliDif < (24*60*60*1000)) {
							openC24hr++;
						} else if (milliDif >= (24*60*60*1000) && milliDif < (3*24*60*60*1000)) {
							openC1d++;
						} else if (milliDif >= (3*24*60*60*1000)) {
							openC3d++;
						}
					} else if (j[i].contractStatus == 'PENDING') {
						pendC++;
						if (milliDif < (6*60*60*1000)) {
							pendC6hr++;
						} else if (milliDif >= (6*60*60*1000) && milliDif < (24*60*60*1000)) {
							pendC24hr++;
						} else if (milliDif >= (24*60*60*1000) && milliDif < (3*24*60*60*1000)) {
							pendC1d++;
						} else if (milliDif >= (3*24*60*60*1000)) {
							pendC3d++;
						}
					} else if (j[i].contractStatus == 'PROPOSED') {
						propC++;
						if (milliDif < (6*60*60*1000)) {
							propC6hr++;
						} else if (milliDif >= (6*60*60*1000) && milliDif < (24*60*60*1000)) {
							propC24hr++;
						} else if (milliDif >= (24*60*60*1000) && milliDif < (3*24*60*60*1000)) {
							propC1d++;
						} else if (milliDif >= (3*24*60*60*1000)) {
							propC3d++;
						}
					}
					
					
				}
				console.log("contractStore loaded: " + j.length);
				localStorage.setItem('storeLoaded', true);
				localStorage.setItem('storeSize', j.length);

                //All
				localStorage.setItem('storeOpenSize', openC);
				localStorage.setItem('storeProposedSize', propC);
				localStorage.setItem('storePendingSize', pendC);

                //between 1 hour and 6 hours since last update
				localStorage.setItem('storeOpenSize6hr', openC6hr);
				localStorage.setItem('storeProposedSize6hr', propC6hr);
				localStorage.setItem('storePendingSize6hr', pendC6hr);

                //between 6 hour and 24 hours since last update
				localStorage.setItem('storeOpenSize24hr', openC24hr);
				localStorage.setItem('storeProposedSize24hr', propC24hr);
				localStorage.setItem('storePendingSize24hr', pendC24hr);

                //between 1 and 3 days since last update
				localStorage.setItem('storeOpenSize1d', openC1d);
				localStorage.setItem('storeProposedSize1d', propC1d);
				localStorage.setItem('storePendingSize1d', pendC1d);

                //over 3 days since last update
				localStorage.setItem('storeOpenSize3d', openC3d);
				localStorage.setItem('storeProposedSize3d', propC3d);
				localStorage.setItem('storePendingSize3d', pendC3d);

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

				var dateNow = Date.now();
				
                var propR = 0;
                var pendR = 0;
                var propR6hr = 0;
                var pendR6hr = 0;
                var propR24hr = 0;
                var pendR24hr = 0;
                var propR1d = 0;
                var pendR1d = 0;
                var propR3d = 0;
                var pendR3d = 0;
                
				for (var i = 0; i < j.length; i++) {

					var milliDif = calcMilliDif(dateNow, (new Date(Date.parse(j[i].lastUpdateDatetime))).getTime());
					
					if (j[i].status == 'PROPOSED') {
						propR++;
						if (milliDif < (6*60*60*1000)) {
							propR6hr++;
						} else if (milliDif >= (6*60*60*1000) && milliDif < (24*60*60*1000)) {
							propR24hr++;
						} else if (milliDif >= (24*60*60*1000) && milliDif < (3*24*60*60*1000)) {
							propR1d++;
						} else if (milliDif >= (3*24*60*60*1000)) {
							propR3d++;
						}
					} else if (j[i].status == 'PENDING') {
						pendR++;
						if (milliDif < (6*60*60*1000)) {
							pendR6hr++;
						} else if (milliDif >= (6*60*60*1000) && milliDif < (24*60*60*1000)) {
							pendR24hr++;
						} else if (milliDif >= (24*60*60*1000) && milliDif < (3*24*60*60*1000)) {
							pendR1d++;
						} else if (milliDif >= (3*24*60*60*1000)) {
							pendR3d++;
						}
					}
				}
				
				console.log("rerateStore loaded: " + j.length);
				
				localStorage.setItem('storeRerateProposedSize', propR);
				localStorage.setItem('storeReratePendingSize', pendR);

				localStorage.setItem('storeRerateProposedSize6hr', propR6hr);
				localStorage.setItem('storeReratePendingSize6hr', pendR6hr);

				localStorage.setItem('storeRerateProposedSize24hr', propR24hr);
				localStorage.setItem('storeReratePendingSize24hr', pendR24hr);

				localStorage.setItem('storeRerateProposedSize1d', propR1d);
				localStorage.setItem('storeReratePendingSize1d', pendR1d);

				localStorage.setItem('storeRerateProposedSize3d', propR3d);
				localStorage.setItem('storeReratePendingSize3d', pendR3d);
				
				resolve();
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log("contractStore load error: " + thrownError);
				resolve();
			}
		})
	})
}

function calcMilliDif(dNow, dLast) {
	var dif =  dNow - dLast;
	return dif;
}

function storeLoaded() {
	return localStorage.getItem('storeLoaded');
}

function getContract(contractId) {
	return localStorage.getItem(contractId);
}