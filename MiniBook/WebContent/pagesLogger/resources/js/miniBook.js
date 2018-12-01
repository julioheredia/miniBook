function mask(o, f) {
	setTimeout(function() {
		var v = f(o.value);
		if (v != o.value) {
			o.value = v;
		}
	}, 1);
}

function mphone(v) {
	var r = v.replace(/\D/g, "");
	r = r.replace(/^0/, "");
	if (r.length > 9) {
		r = r.replace(/^(\d\d)(\d{4})(\d{4}).*/, "($1) $2-$3");
	} else if (r.length > 4) {
		r = r.replace(/^(\d\d)(\d{4})(\d{0,4}).*/, "($1) $2-$3");
	} else if (r.length > 2) {
		r = r.replace(/^(\d\d)(\d{0,4})/, "($1) $2");
	} else {
		r = r.replace(/^(\d*)/, "($1");
	}
	return r;
}

function mcep(v) {
	var r = v.replace(/\D/g, "");
	r = r.replace(/^0/, "");
	if (r.length > 9) {
		r = r.replace(/^(\d{5})(\d{3}).*/, "$1-$2");
	} else if (r.length > 4) {
		r = r.replace(/^(\d{5})(\d{0,3}).*/, "$1-$2");
	} else if (r.length > 2) {
		r = r.replace(/^(\d{0,3})/, "$1");
	} else {
		r = r.replace(/^(\d*)/, "$1");
	}
	return r;
}