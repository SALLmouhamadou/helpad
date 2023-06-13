function remplir() {
	var page = document.getElementById("pageNow").innerText;
	var nombrePage = document.getElementById("nombrePage").innerText;
	var liens = "";
	let lienBase = "http://localhost:8080/infirmerie/inventaire/";
	let lienPage = "page/"

	var pagePrec = parseInt(page) - 1;
	var pageSuiv = parseInt(page) + parseInt(1);

	liens += '<li class="page-item"><a class="page-link"  aria-label="Début" href="' + lienBase
		+ '">' + '<span aria-hidden="true">&laquo;</span>' + '<span class="sr-only">Début</span>' + '</a></li>'


	if (page > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Précédent" href="' + lienBase + lienPage +
			pagePrec + '">' + '<span aria-hidden="true">&lt;</span>' + '<span class="sr-only">Précédent</span>' + '</a></li>'

	liens += '<li class="page-item active"><a class="page-link" href="' + lienBase + page + '">' + page + '</a></li>'

	if (parseInt(page) < parseInt(nombrePage))
		liens += '<li class="page-item"><a class="page-link"  aria-label="Suivant" href="' + lienBase + lienPage +
			pageSuiv + '">' + '<span aria-hidden="true">&gt;</span>' + '<span class="sr-only">Suivant</span>' + '</a></li>'

	liens += '<li class="page-item"><a class="page-link"  aria-label="Fin" href="' + lienBase + lienPage +
		nombrePage + '">' + '<span aria-hidden="true">&raquo;</span>' + '<span class="sr-only">Fin</span>' + '</a></li>'

	console.log(liens);
	document.getElementById("pages").innerHTML = liens;
	document.getElementById("pages2").innerHTML = liens;
}