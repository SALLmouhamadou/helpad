package fr.helpad.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.helpad.entity.WebGouvMedic;

public interface WebGouvMedicServiceI extends BasicBusiness<WebGouvMedic> {
	public String setMedicaments() throws MalformedURLException, IOException, ProtocolException;
	public List<WebGouvMedic> findByNameLimited(String nom, Pageable pageable);
	public long count();
	public long countByName(String nom);
	public Page<WebGouvMedic> findLimited(Pageable pageable);
	List<WebGouvMedic> findByNameExactLimited(String nom, Pageable pageable);
}
