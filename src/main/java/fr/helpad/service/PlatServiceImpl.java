package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Plat;
import fr.helpad.repository.PlatRepository;

@Service
public class PlatServiceImpl implements PlatService {

	@Autowired
	PlatRepository platRepo;
	
	@Override
	public Plat sauvegarder(Plat plat) {
		return platRepo.save(plat);
	}

	@Override
	public List<Plat> listerTout() {
		return (List<Plat>) platRepo.findAll();
	}

	@Override
	public Plat get(Long id) throws NoSuchElementException {
		return platRepo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		platRepo.deleteById(id);
		
	}

}
