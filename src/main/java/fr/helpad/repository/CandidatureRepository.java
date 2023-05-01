package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Candidature;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	
}
