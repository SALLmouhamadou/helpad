package fr.helpad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.repository.CandidatRepository;
import fr.helpad.repository.CandidatureRepository;

@Service
@Transactional
public class CandidatServiceImpl implements CandidatService {
	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	CandidatureRepository candidatureRepository;

	@Override
	public Candidat sauveCandidat(Candidat candidat) {

		List<Candidature> candidatures = candidatureRepository.findByCandidat(candidat);
		for (Candidature candidature : candidatures) {
			if (candidature.getStatus().getLibelle().equals("En cours de traitement")
					|| candidature.getStatus().getLibelle().equals("En instruction")) {
				return null;
			}
		}

		return candidatRepository.save(candidat);
	}

	@Override
	public Candidat findByUsername(String user) {
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
