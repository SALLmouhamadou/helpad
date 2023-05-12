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
	
	public Optional<Candidature> getCandidaturesById(Long id){
		return candidatureRepository.findById(id);
	}
	
	public List<Candidature> findAllCandidatures(){
		return (List<Candidature>) candidatureRepository.findAll();
	}
}
