package fr.helpad.service;

import fr.helpad.entity.Candidat;
import fr.helpad.entity.Personne;
import fr.helpad.repository.CandidatRepository;

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

    @Override
    public void sauveCandidat(Candidat candidat) {
    	candidatRepository.save(candidat);
    }
    @Override
   	public Optional<Candidat> findByUsername(String user) {
   		return candidatRepository.findByEmail(user);
   	}


    @Override
    public List<Candidat> findAll() {
        return (List<Candidat>) candidatRepository.findAll() ;
    }

    @Override
    public Candidat get(Long id) {
        return candidatRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
    	
    }
    
}
