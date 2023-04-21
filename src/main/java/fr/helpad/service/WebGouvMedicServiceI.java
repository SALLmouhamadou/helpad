package fr.helpad.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

import fr.helpad.entity.WebGouvMedic;

public interface WebGouvMedicServiceI extends BasicBusiness<WebGouvMedic> {
	public boolean setMedicaments() throws MalformedURLException, IOException, ProtocolException;
}
