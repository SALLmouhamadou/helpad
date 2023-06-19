package fr.helpad.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Repas;
import fr.helpad.repository.RepasRepository;

@Service
public class RepasServiceImpl implements RepasService {

	@Autowired
	RepasRepository repasRepo;
	
	@Override
	public Repas sauvegarder(Repas repas) {
		return repasRepo.save(repas);
	}

	@Override
	public List<Repas> listerTout() {
		return (List<Repas>) repasRepo.findAll();
	}

	@Override
	public Repas get(Long id) throws NoSuchElementException {
		return repasRepo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repasRepo.deleteById(id);		
	}

	@Override
	public List<Repas> findByDateRepas(LocalDate dr) {
		return repasRepo.findByDateRepas(dr);
	}
	


}
