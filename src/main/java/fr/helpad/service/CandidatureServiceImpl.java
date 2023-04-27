package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Candidature;
import fr.helpad.repository.CandidatureRepository;

@Service
public class CandidatureServiceImpl {
	@Autowired
	CandidatureRepository candidatureRepository;
	
	public List<Candidature> getCandidaturesById(Long id){
		return (List<Candidature>)candidatureRepository.findAllCandidatureById(id);
	}
	
	public List<Candidature> FindAllCandidatures(){
		return (List<Candidature>) candidatureRepository.findAll();
	}
}
