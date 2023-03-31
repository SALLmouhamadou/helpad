package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Etage;
import fr.helpad.repository.EtageRepository;

@Service("etageBusiness")
public class EtageService implements BasicBusiness<Etage> {

	@Autowired
	EtageRepository repo;

	@Override
	public Etage sauvegarder(Etage entity) {
		return repo.save(entity);
	}

	@Override
	public List<Etage> listerTout() {
		return (List<Etage>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Etage get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

}
