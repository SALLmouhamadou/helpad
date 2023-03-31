package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Ordonnance;
import fr.helpad.repository.OrdonnanceRepository;

@Service("ordonnanceBusiness")
public class OrdonnanceService  implements OrdonnanceServiceI {
	@Autowired
	OrdonnanceRepository repo;

	@Override
	public Ordonnance sauvegarder(Ordonnance entity) {
		return repo.save(entity);
	}

	@Override
	public List<Ordonnance> listerTout() {
		return (List<Ordonnance>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Ordonnance get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
}
