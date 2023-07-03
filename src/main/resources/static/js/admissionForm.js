var date_naissance_input = document.getElementById("dateNaissance");
date_naissance_input.addEventListener("blur", validateDateNaissanceForm);
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
		date_naissance_error.innerHTML = "La date de naissance doit précéder 1959.";
		document.getElementById('btnDateNaissance').disabled = true;
		return false;
	} else {
		date_naissance_error.innerHTML = "";
		document.getElementById('btnDateNaissance').disabled = false;
	}
	return true;
}
//Validation de l'adresse
// Validation de numéro de rue 
var numberAdresse = document.getElementById("number");
numberAdresse.addEventListener("blur", validateNumeroForm)
function validateNumeroForm(){
	var numberAdresse = document.getElementById("number");
	if (numberAdresse.value == "") {
		document.getElementById('numberError').innerHTML = "Numéro invalide. Merci de renseigner";
		document.getElementById('btnAdresse').disabled = true;
		return false;
	} else {
		document.getElementById('numberError').innerHTML = "";
		document.getElementById('btnAdresse').disabled = false;
	}
	return true;
}
// Validation de nom de rue 
var rueAdresse = document.getElementById("rue");
rueAdresse.addEventListener("change", validateRueForm)
function validateRueForm(){
	var rueAdresse = document.getElementById("rue");
	if (rueAdresse.value == "") {
		document.getElementById('rueError').innerHTML = "Rue invalide. Merci de renseigner";
		document.getElementById('btnAdresse').disabled = true;
		return false;
	} else {
		document.getElementById('rueError').innerHTML = "";
		document.getElementById('btnAdresse').disabled = false;
	}
	return true;
}
//validation de nom de la ville
var villeAdresse = document.getElementById("ville");
villeAdresse.addEventListener("blur", validateVilleForm)
function validateVilleForm(){
	var villeAdresse = document.getElementById("ville");
	if (villeAdresse.value == "") {
		document.getElementById('villeError').innerHTML = "Le nom de la ville est  invalide. Merci de renseigner";
		document.getElementById('btnAdresse').disabled = true;
		return false;
	} else {
		document.getElementById('villeError').innerHTML = "";
		document.getElementById('btnAdresse').disabled = false;
	}
	return true;
}
// validation de code Postale
var codePostalAdresse = document.getElementById("codePostal");
codePostalAdresse.addEventListener("blur", validateCodePostalForm)
function validateCodePostalForm(){
	var codePostalAdresse = document.getElementById("codePostal");
	if (codePostalAdresse.value != "") {
		document.getElementById('codePostalError').innerHTML = "";
		document.getElementById('btnAdresse').disabled = false;
	} else {
		document.getElementById('codePostalError').innerHTML = "Le code postale  est  invalide. Merci de renseigner";
		document.getElementById('btnAdresse').disabled = true;
		return false;
	}
	return true;
}
//Validation de pays 
var paysAdresse = document.getElementById("pays");
paysAdresse.addEventListener("change", validatePaysForm)
function validatePaysForm(){
	var paysAdresse = document.getElementById("pays");
	if (paysAdresse.value != "") {
		document.getElementById('paysError').innerHTML = "";
		document.getElementById('btnAdresse').disabled = false;
	} else {
		document.getElementById('paysError').innerHTML = "Pays invalide. Merci de renseigner";
		document.getElementById('btnAdresse').disabled = true;
		return false;
	}
	return true;
}
//Validation de numéro sécurité social
var numero_securite_social = document.getElementById("numSecu")
numero_securite_social.addEventListener("blur", valideNumeroSecuriteSocial)
function valideNumeroSecuriteSocial(){
	var numero_securite_social = document.getElementById("numSecu");
	if(numero_securite_social.value.length > 15 || numero_securite_social.value.length < 15){
		document.getElementById("numeroSecuError").innerHTML = "Le numéro sécurité social doit 15 caracteres";
		document.getElementById('btnDateEntree').disabled = true;
		return false;
	}
	else{
		document.getElementById("numeroSecuError").innerHTML ="";
		document.getElementById('btnDateEntree').disabled = false;
		return true;
	}
	return true;
}

//Validation de numéro de caf
var numero_caf = document.getElementById("numcaf")
numero_caf.addEventListener("change", valideNumerocaf)
function valideNumerocaf(){
	var numero_caf = document.getElementById("numcaf");
	if(numero_caf.value.length > 7 || numero_caf.value.length < 7){
		document.getElementById("numerocafError").innerHTML = "Le numéro de CAF doit avoir 7 caracteres";
		document.getElementById('btnDateEntree').disabled = true;
		return false;
	}
	else{
		document.getElementById("numerocafError").innerHTML ="";
		document.getElementById('btnDateEntree').disabled = false;
		return true;
	}
	return true;
}

var date_entree_input = document.getElementById("dateEntree");
date_entree_input.addEventListener("blur", validateDateEntreeForm);
//Validation candidature
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
		date_entree_error.innerHTML = "La date d'entrée doit être ultérieur à aujourd'hui.";
		document.getElementById('btnDateEntree').disabled = true;
		return false;
	} else {
		date_entree_error.innerHTML = "";
		document.getElementById('btnDateEntree').disabled = false;
	}

	// Si toutes les validations sont réussies
	return true;
}



var fileInput = document.getElementsByClassName('fileInput');
for(var i=0; i<fileInput.length; i++){
	var errorMessage = document.getElementById('errorMessage');
	fileInput[i].addEventListener('change', function(event) {
	const file = event.target.files[0];
	if (file.type.startsWith('image/')) {
		// Fichier image
		if (file.size > 2 * 1024 * 1024) {
			errorMessage.textContent = 'La taille du fichier image doit être inférieur à 2 Mo.';
			fileInput.value = '';
		} else {
			errorMessage.textContent = '';
		}
	} else if (file.type === 'application/pdf') {
		// Fichier PDF
		if (file.size > 2 * 1024 * 1024) {
			errorMessage.textContent = 'La taille du fichier PDF doit être inférieur à 2 Mo.';
			fileInput.value = '';
		} else {
			errorMessage.textContent = '';
		}
	} else {
		// Type de fichier non pris en charge
		errorMessage.textContent = "Le fichier sélectionné n'est ni une image ni un PDF.";
		fileInput.value = '';
	}
})	
}


