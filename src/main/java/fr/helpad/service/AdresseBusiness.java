package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Adresse;
import fr.helpad.repository.AdresseRepository;

@Service("adresseBusiness")
public class AdresseBusiness implements BasicBusiness<Adresse> {

	@Autowired
	AdresseRepository repo;

	@Override
	public Adresse sauvegarder(Adresse entity) {
		return repo.save(entity);
	}

	@Override
	public List<Adresse> listerTout() {
		return (List<Adresse>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Adresse get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

}
