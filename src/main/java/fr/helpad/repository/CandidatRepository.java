package fr.helpad.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Candidat;

public interface CandidatRepository extends CrudRepository<Candidat, Long> {

	Candidat findByEmail(String email);
	
}
