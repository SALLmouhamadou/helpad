package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Allergene;
import fr.helpad.repository.AllergeneRepository;

@Service
public class AllergeneServiceImpl implements AllergeneService {

	@Autowired
	AllergeneRepository allergeneRepository;
	
	@Override
	public Allergene sauvegarder(Allergene allergene) {
		return allergeneRepository.save(allergene);
	}

	@Override
	public List<Allergene> listerTout() {
		return (List<Allergene>) allergeneRepository.findAll();
	}

	@Override
	public Allergene get(Long id) throws NoSuchElementException {
		return allergeneRepository.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		allergeneRepository.deleteById(id);
	}

}
