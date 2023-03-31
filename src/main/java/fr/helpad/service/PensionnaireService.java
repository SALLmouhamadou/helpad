package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Pensionnaire;
import fr.helpad.repository.PensionnaireRepository;


@Service("pensionnaireBusiness")
public class PensionnaireService implements PensionnaireServiceI {
	@Autowired
	PensionnaireRepository repo;

	@Override
	public Pensionnaire sauvegarder(Pensionnaire entity) {
		return repo.save(entity);
	}

	@Override
	public List<Pensionnaire> listerTout() {
		return (List<Pensionnaire>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Pensionnaire get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
}
