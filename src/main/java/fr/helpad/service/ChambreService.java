package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Chambre;
import fr.helpad.repository.ChambreRepository;

@Service("chambreBusiness")
public class ChambreService implements BasicBusiness<Chambre> {

	@Autowired
	ChambreRepository repo;

	@Override
	public Chambre sauvegarder(Chambre entity) {
		return repo.save(entity);
	}

	@Override
	public List<Chambre> listerTout() {
		return (List<Chambre>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public Chambre get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

}
