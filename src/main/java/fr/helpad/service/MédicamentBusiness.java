package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Medicament;
import fr.helpad.repository.MédicamentRepository;

@Service("medicamentBusiness")
public class MédicamentBusiness implements MédicamentBusinessI {
	@Autowired
	MédicamentRepository repo;

	@Override
	public Medicament sauvegarder(Medicament entity) {
		return repo.save(entity);
	}

	@Override
	public List<Medicament> listerTout() {
		return (List<Medicament>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Medicament get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
}
