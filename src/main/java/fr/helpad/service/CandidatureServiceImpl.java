package fr.helpad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Candidature;
import fr.helpad.repository.CandidatureRepository;

@Service
public class CandidatureServiceImpl {
	@Autowired
	CandidatureRepository candidatureRepository;
	
	public Candidature getCandidaturesById(Long id){
		return candidatureRepository.getById(id);
	}
	
	public List<Candidature> findAllCandidatures(){
		return (List<Candidature>) candidatureRepository.findAll();
	}
	
	public Candidature findCandidature(String numeroRef){
		Candidature candidature = candidatureRepository.findByNumeroRef(numeroRef);
		return candidature;
	}

	public void saveCandidature(Candidature candidature) {
		candidatureRepository.save(candidature);
	}
	public void removeCandidatureById(Long id) {
		candidatureRepository.deleteById(id);
	}
 }
