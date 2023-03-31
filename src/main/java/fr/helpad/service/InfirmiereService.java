package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Infirmiere;
import fr.helpad.repository.InfirmiereRepository;

@Service("infirmiereBusiness")
public class InfirmiereService implements InfirmiereServiceI {

	@Autowired
	InfirmiereRepository repo;

	@Override
	public Infirmiere sauvegarder(Infirmiere entity) {
		return repo.save(entity);
	}

	@Override
	public List<Infirmiere> listerTout() {
		return (List<Infirmiere>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Infirmiere get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

}
