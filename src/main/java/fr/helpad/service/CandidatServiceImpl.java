package fr.helpad.service;

import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.entity.Personne;
import fr.helpad.repository.CandidatRepository;
import fr.helpad.repository.CandidatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CandidatServiceImpl implements CandidatService {
	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	CandidatureRepository candidatureRepository;

	@Override
	public boolean sauveCandidat(Candidat candidat) {
		boolean isValide = true;
		List<Candidature> candidatures = candidatureRepository.findByCandidat(candidat);
		for (Candidature candidature : candidatures) {
			if (candidature.getStatus().getLibelle().equals("En cours de traitement")
					|| candidature.getStatus().getLibelle().equals("En instruction")) {
				return false;
			}
		}
		if (isValide) {
			candidatRepository.save(candidat);
		}
		return isValide;
	}

	@Override
	public Optional<Candidat> findByUsername(String user) {
		return candidatRepository.findByEmail(user);
	}

	@Override
	public List<Candidat> findAllCandidats() {
		return (List<Candidat>) candidatRepository.findAll();
	}

	@Override
	public Candidat get(Long id) {
		return candidatRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {

	}

}
