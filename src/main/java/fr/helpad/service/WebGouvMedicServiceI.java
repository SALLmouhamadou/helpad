package fr.helpad.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import fr.helpad.entity.WebGouvMedic;

public interface WebGouvMedicServiceI extends BasicBusiness<WebGouvMedic> {
	public String setMedicaments() throws MalformedURLException, IOException, ProtocolException;
}
