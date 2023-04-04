package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Medecin;
import fr.helpad.repository.MédecinRepository;

@Service("medecinBusiness")
public class MedecinService implements MedecinServiceI {
	@Autowired
	MédecinRepository repo;

	@Override
	public Medecin sauvegarder(Medecin entity) {
		return repo.save(entity);
	}

	@Override
	public List<Medecin> listerTout() {
		return (List<Medecin>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Medecin get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
}
