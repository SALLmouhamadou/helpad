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
		document.getElementById('errorfirstname').innerHTML = "Prénom non valide";
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
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez un adresse mail valide";
		email.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	} else {
		document.getElementById('erroremailaddress').innerHTML = "";
	}
	if (email.value.indexOf("@", 0) < 0) {
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez un adresse mail valide";
		email.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	if (email.value.indexOf(".", 0) < 0) {
		document.getElementById('erroremailaddress').innerHTML = "Veuillez entrez un adresse mail valide";
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
		document.getElementById('errorpassword').innerHTML = "Mot de passe non valide";
		password.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	if(password.value.match(pass)){
		document.getElementById('errorpassword').innerHTML = ""	
		return true;
	}else{
		document.getElementById('errorpassword').innerHTML = "Mot de passe doit contenir au minimum 8 caracteres dont 1 maj, 1 min, 1 chiffre et 1 caractere spécial(@$!%*?&)";
		password.focus();
		document.getElementById('btnInscription').disabled = true;
		return false;
	}
	return true;
}