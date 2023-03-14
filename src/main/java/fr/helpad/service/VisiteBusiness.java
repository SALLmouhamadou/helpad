package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Visite;
import fr.helpad.repository.VisiteRepository;

@Service("visiteBusiness")
public class VisiteBusiness implements VisiteBusinessI {
	@Autowired
	VisiteRepository repo;

	@Override
	public Visite sauvegarder(Visite entity) {
		return repo.save(entity);
	}

	@Override
	public List<Visite> listerTout() {
		return (List<Visite>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Visite get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
}
