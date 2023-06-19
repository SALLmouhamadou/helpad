function remplir() {
	let page = document.getElementById("pageNow").innerText;
	let nombrePage = document.getElementById("nombrePage").innerText;
	let chkBox = document.getElementById('isStock');
	let liens = "";
	let lienBase = "http://localhost:8080/infirmerie/inventaire/";
	let lienPage = "page/";
	let mode = document.getElementById("mode").innerText;
	let recherche = document.getElementById("rech").innerText;
	let pagePrec = parseInt(page) - 1;
	let pageSuiv = parseInt(page) + parseInt(1);
	
	console.log(recherche);
	
	if (chkBox.checked)
		lienPage = "stock/";
		
	if (mode === "rechercher") {
		lienPage = "rechercher";
		if (parseInt(page) > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Début" href="' + lienBase + lienPage + '?nom='
		+ recherche + '&page=' + 0 + '">' + '<span aria-hidden="true">&laquo;</span>' + '<span class="sr-only">Début</span>' + '</a></li>'


	if (parseInt(page) > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Précédent" href="' + lienBase + lienPage + '?nom='
		+ recherche + '&page=' + pagePrec + '">' + '<span aria-hidden="true">&lt;</span>' + '<span class="sr-only">Précédent</span>' + '</a></li>'

	liens += '<li class="page-item active"><a class="page-link" href="' + lienBase + lienPage + '?nom='
		+ recherche + '&page=' + page + '">' + page + '</a></li>'

	if (parseInt(page) < parseInt(nombrePage))
		liens += '<li class="page-item"><a class="page-link"  aria-label="Suivant" href="' + lienBase + lienPage + '?nom='
		+ recherche + '&page=' + pageSuiv + '">' + '<span aria-hidden="true">&gt;</span>' + '<span class="sr-only">Suivant</span>' + '</a></li>'

	liens += '<li class="page-item"><a class="page-link"  aria-label="Fin" href="' + lienBase + lienPage + '?nom='
		+ recherche + '&page=' + nombrePage + '">' + '<span aria-hidden="true">&raquo;</span>' + '<span class="sr-only">Fin</span>' + '</a></li>'
		
	} else {
		if (parseInt(page) > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Début" href="' + lienBase + lienPage +
		'0">' + '<span aria-hidden="true">&laquo;</span>' + '<span class="sr-only">Début</span>' + '</a></li>'


	if (parseInt(page) > 0)
		liens += '<li class="page-item"><a class="page-link"  aria-label="Précédent" href="' + lienBase + lienPage +
			pagePrec + '">' + '<span aria-hidden="true">&lt;</span>' + '<span class="sr-only">Précédent</span>' + '</a></li>'

	liens += '<li class="page-item active"><a class="page-link" href="' + lienBase + lienPage + page + '">' + page + '</a></li>'

	if (parseInt(page) < parseInt(nombrePage))
		liens += '<li class="page-item"><a class="page-link"  aria-label="Suivant" href="' + lienBase + lienPage +
			pageSuiv + '">' + '<span aria-hidden="true">&gt;</span>' + '<span class="sr-only">Suivant</span>' + '</a></li>'

	liens += '<li class="page-item"><a class="page-link"  aria-label="Fin" href="' + lienBase + lienPage +
		nombrePage + '">' + '<span aria-hidden="true">&raquo;</span>' + '<span class="sr-only">Fin</span>' + '</a></li>'
	}

	console.log(liens);
	document.getElementById("pages").innerHTML = liens;
	document.getElementById("pages2").innerHTML = liens;
}