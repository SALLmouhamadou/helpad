var date_naissance_input = document.getElementById("dateNaissance");
date_naissance_input.addEventListener("blur", validateDateNaissanceForm);
var date_entree_input = document.getElementById("dateEntree");
date_entree_input.addEventListener("blur", validateDateEntreeForm);

function validateDateNaissanceForm() {
	// Validation de la date de naissance
	var date_naissance_input = document.getElementById("dateNaissance");
	console.log(date_naissance_input)
	var date_naissance_error = document.getElementById("dateNaissanceError");
	var date_naissance = new Date(date_naissance_input.value);
	if (isNaN(date_naissance.getTime())) {
		date_naissance_error.innerHTML = "Veuillez saisir une date de naissance valide.";
		document.getElementById('btnDateNaissance').disabled = true;
		return false;
	} else if (date_naissance.getFullYear() > 1959) {
		date_naissance_error.innerHTML = "La date de naissance doit être avant 1959.";
		document.getElementById('btnDateNaissance').disabled = true;
		return false;
	} else {
		date_naissance_error.innerHTML = "";
	}
	return true;
}
// Validation de la date d'entrée
function validateDateEntreeForm() {
	var date_entree_input = document.getElementById("dateEntree");
	var date_entree_error = document.getElementById("dateEntreeError");
	var date_entree = new Date(date_entree_input.value);
	var today = new Date();
	today.setHours(0, 0, 0, 0);
	if (isNaN(date_entree.getTime())) {
		date_entree_error.innerHTML = "Veuillez saisir une date d'entrée valide.";
		document.getElementById('btnDateEntree').disabled = true;
		return false;
	} else if (date_entree < today) {
		date_entree_error.innerHTML = "La date d'entrée doit être après aujourd'hui.";
		document.getElementById('btnDateEntree').disabled = true;
		return false;
	} else {
		date_entree_error.innerHTML = "";
	}

	// Si toutes les validations sont réussies
	return true;
}



var fileInput = document.getElementsByClassName('fileInput');
for(var i=0; i<fileInput.length; i++){
	var errorMessage = document.getElementById('errorMessage');
	console.log(fileInput[i])
	fileInput[i].addEventListener('change', function(event) {
	const file = event.target.files[0];
	if (file.type.startsWith('image/')) {
		// Fichier image
		if (file.size > 2 * 1024 * 1024) {
			errorMessage.textContent = 'La taille du fichier image doit être inférieure à 2 Mo.';
			fileInput.value = '';
		} else {
			errorMessage.textContent = '';
		}
	} else if (file.type === 'application/pdf') {
		// Fichier PDF
		if (file.size > 2 * 1024 * 1024) {
			errorMessage.textContent = 'La taille du fichier PDF doit être inférieure à 2 Mo.';
			fileInput.value = '';
		} else {
			errorMessage.textContent = '';
		}
	} else {
		// Type de fichier non pris en charge
		errorMessage.textContent = 'Le fichier sélectionné est ni une image ni un PDF.';
		fileInput.value = '';
	}
})	
}


