function comp() {
	var xhttp = new XMLHttpRequest();
	var nom = document.getElementById('nomSearch').value;
	var base = document.getElementById("medikit").innerHTML;
	console.log("nom: " + nom);
	if (nom.length > 0) {
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("medikit").innerHTML = this.responseText;
			} else {
				console.log(this.readyState);
				console.log(this.responseText);
			}
		};
		xhttp.open("GET", "http://localhost:8080/infirmerie/search/" + nom, true);
		xhttp.send();
	} else {
		document.getElementById("medikit").innerHTML = base;
	}
}

function remplir() {
	var pages = document.getElementById("pages").innerHTML;
	var page = document.getElementById("pageNow").innerText;
	var nombrePage = document.getElementById("nombrePage").innerText;
	var liens = "";
	let lienBase = "http://localhost:8080/infirmerie/inventaire/";

	var pagePrec = parseInt(page) - 1;
	var pageSuiv = parseInt(page) + parseInt(1);

	liens += '<li class="page-item"><a class="page-link"  aria-label="Début" href="' + lienBase +
		nombrePage + '">' + '<span aria-hidden="true">&laquo;</span>' + '<span class="sr-only">Début</span>' + '</a></li>'


	if (page > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Précédent" href="' + lienBase +
			pagePrec + '">' + '<span aria-hidden="true">&lt;</span>' + '<span class="sr-only">Précédent</span>' + '</a></li>'

	liens += '<li class="page-item active"><a class="page-link" href="' + lienBase + page + '">' + page + '</a></li>'

	if (page < nombrePage)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Suivant" href="' + lienBase +
			pageSuiv + '">' + '<span aria-hidden="true">&gt;</span>' + '<span class="sr-only">Suivant</span>' + '</a></li>'

	if (page < nombrePage)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Fin" href="' + lienBase +
			nombrePage + '">' + '<span aria-hidden="true">&raquo;</span>' + '<span class="sr-only">Fin</span>' + '</a></li>'

	console.log(liens);
	document.getElementById("pages").innerHTML = liens;
}