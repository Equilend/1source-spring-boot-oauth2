var canAuth = true;
function noAuth() {
	canAuth = false;
}

function checkAuth() {
	if (!canAuth) {
		$('#cDialog').dialog({
			"width": 400,
			"show": true,
			"modal": true
		});
	}
	return canAuth;
}