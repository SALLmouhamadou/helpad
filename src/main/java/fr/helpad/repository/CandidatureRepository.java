package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Candidature;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	public Candidature findByNumeroRef(String numeroRef);
}
