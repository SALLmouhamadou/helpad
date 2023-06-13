package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.repository.StockMedicamentRepository;
import fr.helpad.repository.WebGouvMedicRepository;

@Service
public class StockMedicamentService implements StockMedicamentServiceI {

	@Autowired
	StockMedicamentRepository repo;
	
	@Autowired
	WebGouvMedicRepository repoMedic;
	
	@Override
	public StockMedicament sauvegarder(StockMedicament entity) {
		return repo.save(entity);
	}

	@Override
	public StockMedicament get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}
	
	@Override
	public WebGouvMedic getMedicament(Long id) throws NoSuchElementException {
		return repoMedic.findById(id).get();
	}

	@Override
	public List<StockMedicament> listerTout() {
		return (List<StockMedicament>) repo.findAll();
	}

	@Override
	public List<StockMedicament> findByQuantiteGreaterThan(short quantite, Pageable page) {
		return repo.findByQuantiteGreaterThan(quantite, page);
	}

}
