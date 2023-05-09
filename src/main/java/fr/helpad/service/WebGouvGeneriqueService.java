package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.WebGouvGenerique;
import fr.helpad.repository.WebGouvGeneriqueRepository;

public class WebGouvGeneriqueService implements WebGoubGeneriqueServiceI {
	
	@Autowired
	WebGouvGeneriqueRepository repo;

	@Override
	public WebGouvGenerique sauvegarder(WebGouvGenerique entity) {
		return repo.save(entity);
	}

	@Override
	public List<WebGouvGenerique> listerTout() {
		return (List<WebGouvGenerique>) repo.findAll();
	}

	@Override
	public WebGouvGenerique get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);		
	}

}
