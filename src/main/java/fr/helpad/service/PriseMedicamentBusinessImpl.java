package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.PriseMedicament;
import fr.helpad.repository.PriseMedicamentRepository;

@Service("priseMedocBusiness")
public class PriseMedicamentBusinessImpl implements PriseMedicamentBusiness {
	@Autowired
	PriseMedicamentRepository repo;
	
	@Override
	public PriseMedicament sauvegarder(PriseMedicament entity) {
		return repo.save(entity);
	}

	@Override
	public List<PriseMedicament> listerTout() {
		return (List<PriseMedicament>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public PriseMedicament get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public List<PriseMedicament> chercherParPensionnaire(Long pensionnaire) {
		return repo.chercherParPensionnaire(pensionnaire);
	}

	@Override
	public List<PriseMedicament> chercherParMedicament(Long medicament) {
		return repo.chercherParMedicament(medicament);
	}

}
