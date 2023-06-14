var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");
var email = document.getElementById("emailAddress");
var password = document.getElementById("password");

firstName.addEventListener("blur", validateFirstForm)
lastName.addEventListener("blur", validateLastNameForme)
email.addEventListener("blur", validateEmailForm)
password.addEventListener("blur", validatePasswordFom)


function validateFirstForm() {
	var firstName = document.getElementById("firstName");
	if (firstName.value == "") {
		document.getElementById('errorfirstname').innerHTML = "Prénom invalide";
		firstName.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	} else {
		document.getElementById('errorfirstname').innerHTML = "";
	}
	return true;
}
function validateLastNameForme() {
	var lastName = document.getElementById("lastName");
	if (lastName.value == "") {
		document.getElementById('errorlastname').innerHTML = "Veuillez entrez un nom valide";
		lastName.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	} else {
		document.getElementById('errorlastname').innerHTML = "";
	}
	return true;
}
function validateEmailForm() {
	var email = document.getElementById("emailAddress");
	if (email.value == "") {
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez une adresse mail valide";
		email.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	} else {
		document.getElementById('erroremailaddress').innerHTML = "";
	}
	if (email.value.indexOf("@", 0) < 0) {
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez une adresse mail valide";
		email.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	if (email.value.indexOf(".", 0) < 0) {
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez une adresse mail valide";
		email.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	return true;
}
function validatePasswordFom() {
	var password = document.getElementById("password");
	var pass =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	if (password.value == "") {
		document.getElementById('errorpassword').innerHTML = "Mot de passe invalide";
		password.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	if(password.value.match(pass)){
		document.getElementById('errorpassword').innerHTML = ""	
		return true;
	}else{
		document.getElementById('errorpassword').innerHTML = "Le mot de passe doit contenir au minimum 8 caractères dont 1 majuscule, 1 minuscule, 1 chiffre et 1 caractère spécial(@$!%*?&)";
		password.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	return true;
}