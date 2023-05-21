package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Status;
import fr.helpad.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
	StatusRepository statusRepository;
	
	public void saveStatus(Status status) {
		statusRepository.save(status);
	}
	
	public Status findStatusByLibelle(String libelle){
		return statusRepository.findByLibelle(libelle);
	}
	
}
