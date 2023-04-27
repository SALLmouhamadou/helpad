function validateInscriptionForm(){
	var firstName= document.forms["inscriptionForm"]["firstName"];
	var lastName= document.forms["inscriptionForm"]["lastName"];
	var email= document.forms["inscriptionForm"]["emailAddress"];
	var password= document.forms["inscriptionForm"]["password"];
	
	if(firstName.value ==""){
		document.getElementById('errorfirstname').innerHTML="Veuillez entrez un prénom valide";
		firstName.focus();
		return false;
	}else{
		document.getElementById('errorfirstname').innerHTML="";
	}
	
	if(lastName.value ==""){
		document.getElementById('errorlastname').innerHTML="Veuillez entrez un nom valide";
		lastName.focus();
		return false;
	}else{
		document.getElementById('errorlastname').innerHTML="";
	}
	
	if(email.value ==""){
		document.getElementById('erroremailaddress').innerHTML="Veuillez entrez un adresse mail valide";
		email.focus();
		return false;
	}else{
		document.getElementById('erroremailaddress').innerHTML="";
	}
	if(email.value.indexOf("@",0)<0){
		document.getElementById('erroremailaddress').innerHTML="Veuillez entrez un adresse mail valide";
		email.focus();
		return false;
	}
	if(email.value.indexOf(".",0)<0){
		document.getElementById('erroremailaddress').innerHTML="Veuillez entrez un adresse mail valide";
		email.focus();
		return false;
	}
	if (password.value == "")                        
    { 
        document.getElementById('erroremailaddress').innerHTML="Veuillez entrez un mot de passe valide";
        password.focus(); 
        return false; 
    }  
    
    return true;  
}