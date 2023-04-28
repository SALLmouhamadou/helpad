package fr.helpad.repository;

import fr.helpad.entity.Candidat;
import fr.helpad.service.CandidatService;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CandidatRepository extends CrudRepository<Candidat, Long> {

	Optional<Candidat> findByEmail(String user);
	
}
