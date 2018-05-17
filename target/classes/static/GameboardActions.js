function hideButton() {
	var x = document.getElementById("guesses-table");
	var y = document.getElementById("check-guess");
	var z = document.getElementById("guess-button");

	if (x.style.visibility === "hidden") {
		x.style.visibility = "visible";
	}
	if (y.style.visibility === "hidden") {
		y.style.visibility = "visible";
	}
	if (z.style.visibility === "hidden") {
		z.style.visibility = "visible";
	}
}