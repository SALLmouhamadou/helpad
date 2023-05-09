package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.WebGouvSecurite;
import fr.helpad.repository.WebGouvSecuriteRepository;

public class WebGouvSecuriteService implements WebGouvSecuriteServiceI {
	@Autowired
	WebGouvSecuriteRepository repo;

	@Override
	public WebGouvSecurite sauvegarder(WebGouvSecurite entity) {
		return repo.save(entity);
	}

	@Override
	public List<WebGouvSecurite> listerTout() {
		return (List<WebGouvSecurite>) repo.findAll();
	}

	@Override
	public WebGouvSecurite get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);		
	}
}
