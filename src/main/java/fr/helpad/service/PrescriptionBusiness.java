package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Prescription;
import fr.helpad.repository.PrescriptionRepository;

@Service("prescrptionBusiness")
public class PrescriptionBusiness implements PrescriptionServiceI {

	@Autowired
	PrescriptionRepository repo;
	
	@Autowired
	MedicamentService medic;

	@Override
	public Prescription sauvegarder(Prescription entity) {
		return repo.save(entity);
	}

	@Override
	public List<Prescription> listerTout() {
		return (List<Prescription>) repo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException, NoSuchElementException {
		repo.deleteById(id);
	}

	@Override
	public Prescription get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}
	
	@Override
	public List<Prescription> chercherParPensionnaire(Long pensionnaire) {
		return repo.chercherParPensionnaire(pensionnaire);
	}

	@Override
	public List<Prescription> chercherParMedicament(Long medicament) {
		return repo.chercherParMedicament(medicament);
	}
}
