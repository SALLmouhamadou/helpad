package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Fonction;
import fr.helpad.repository.FonctionRepository;

@Service("fonctionBusiness")
public class FonctionService implements BasicBusiness<Fonction> {

	@Autowired
	FonctionRepository repo;

	@Override
	public Fonction sauvegarder(Fonction entity) {
		return repo.save(entity);
	}

	@Override
	public List<Fonction> listerTout() {
		return (List<Fonction>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Fonction get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

}
